package com.pfq.deal.trans_listing.service;

import com.pfq.deal.trans_listing.bean.input.order.CeculatorOrderInfo;
import com.pfq.deal.trans_listing.bean.input.order.InputOrder;
import com.pfq.deal.trans_listing.bean.input.order.OrderInfo;
import com.pfq.deal.trans_listing.bean.input.order.UpdateOrder;
import com.pfq.deal.trans_listing.bean.output.Order.OrderDetailsInfo;
import com.pfq.deal.trans_listing.bean.output.Order.OrderTotalInfo;
import com.pfq.deal.trans_listing.bean.output.Order.PayOrderRes;
import com.pfq.deal.trans_listing.bean.output.Order.RetOrderInfo;
import com.pfq.deal.trans_listing.dao.ICommodyDao;
import com.pfq.deal.trans_listing.dao.IOrderDao;
import com.pfq.deal.trans_listing.dao.IShopDao;
import com.pfq.deal.trans_listing.dto.OrderDetailsInfoDTO;
import com.pfq.deal.trans_listing.dto.OrderTotalDTO;
import com.pfq.deal.trans_listing.dto.ShopCommodyDto;
import com.pfq.deal.trans_listing.util.DateUtils;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by steven on 2017/12/4.
 */
@Service
public class OrderService extends IBaseService{

    @Autowired
    IOrderDao orderDao;

    @Autowired
    IShopDao shopDao;


    @Override
    public boolean isExist(String id) {
        return false;
    }


    private void create(InputOrder inputVo,String orderNo) {
        OrderTotalDTO totalDTO=toTotalDTO(inputVo);
        if(orderNo!=null)
            totalDTO.setOrderNo(orderNo);
        orderDao.save(totalDTO);
        inputVo.getOrderList().stream().forEach(detail->{
            OrderDetailsInfoDTO detailsInfoDTO=toDetailsDTO(detail,totalDTO.getOrderNo(),totalDTO.getSiteNo(),totalDTO.getId())
            orderDao.saveDetails(detailsInfoDTO);
        });
    }

    private OrderDetailsInfoDTO toDetailsDTO(OrderInfo orderInfo,String orderNo,String siteNo,Long totalId){
        return OrderDetailsInfoDTO.builder()
                .commodyId(orderInfo.getCommdyId())
                .commodyPrice(orderInfo.getCommodyPrice())
                .totalId(totalId)
                .tagsName(orderInfo.getTagsName())
                .number(orderInfo.getNum())
                .orderNo(orderNo)
                .siteNo(siteNo)
                .build();
    }

    private OrderTotalDTO toTotalDTO(InputOrder inputVo){

       var totalDto= OrderTotalDTO.builder()
               .address(inputVo.getAddress())
               .onLineFlag((byte)0)
               .orderNo(getOrderNo())
               .orderTime(DateUtils.getDateString(new Date()))
               .siteNo(inputVo.getSiteNo())
               .point(inputVo.getPoint());

        Optional.ofNullable(inputVo.getOrderList()).ifPresent(list->{
            //var totalPrice=new BigDecimal("0");

            list.stream().forEach(orderDetails->{
                ShopCommodyDto dto=shopDao.getShopCommodyByCommodyId(orderDetails.getCommdyId());
                orderDetails.setCommodyPrice(dto.getShop_price());
            });
            Long totalPrice=list.stream().collect(Collectors.summingLong(OrderInfo::getPriceLong));
            totalDto.totalPrice(new BigDecimal(totalPrice).divide(new BigDecimal(100l)));
            totalDto.orderNum(list.stream().collect(Collectors.summingInt(OrderInfo::getNum)));
        });

        return totalDto.build();

    }


    private String getOrderNo(){
        Random random = new Random();
        IntStream intStream = random.ints(1000,9999);
        int randomInt = intStream.limit(1).findFirst().getAsInt();
        return DateUtils.getDateStringYYYYMMDDHHSSSSS(new Date())+randomInt;
    }

    @Transactional
    public void createOrder(InputOrder inputVo) {
        this.create(inputVo,null);
    }
    @Transactional
    public void orderExtends(InputOrder inputVo, String orderNo) {
        this.create(inputVo,orderNo);
    }

    public OrderTotalInfo orderInfo(String orderNo) {

        List<OrderDetailsInfoDTO> dtoList = orderDao.getOrderList(orderNo);

        Collections.sort(dtoList, new Comparator<OrderDetailsInfoDTO>() {
            @Override
            public int compare(OrderDetailsInfoDTO dto1, OrderDetailsInfoDTO dto2) {
                return dto1.getTotalId()>dto2.getTotalId()?1:0;
            }
        });

        int totalNum=dtoList.stream().collect(Collectors.summingInt(OrderDetailsInfoDTO::getNumber));

        List<RetOrderInfo> infoList=toListRetOrderInfo(dtoList);

        OrderTotalInfo orderTotalInfo=OrderTotalInfo.builder()
                .orderNo(orderNo)
                .totalNum(totalNum)
                .siteNo(dtoList.get(0).getSiteNo())
                .list(infoList)
                .build();
       infoList.stream().forEach(info->{
          BigDecimal totalPrice= orderTotalInfo.getTotalPrice()==null?new BigDecimal("0"):orderTotalInfo.getTotalPrice();
           orderTotalInfo.setTotalPrice(info.getTotalPrice().add(totalPrice));
       });
       return orderTotalInfo;
    }

    private List<RetOrderInfo> toListRetOrderInfo(List<OrderDetailsInfoDTO> dtoList){
        List<RetOrderInfo> infoList=new ArrayList<>();
        OrderDetailsInfoDTO currentDetails=OrderDetailsInfoDTO.builder().build();
        currentDetails.setTotalId(0l);
        dtoList.stream().forEach(dto->{

            if(currentDetails.getTotalId()!=dto.getTotalId()){
                RetOrderInfo retOrderInfo=RetOrderInfo.builder()
                        .inserttime(DateUtils.getDateString(dto.getInserttime()))
                        .list(new ArrayList<>())
                        .build();
                infoList.add(retOrderInfo);
                currentDetails.setTotalId(dto.getTotalId());
            }
            RetOrderInfo retOrderInfo=infoList.get(infoList.size()-1);
            retOrderInfo.getList().add(toOrderDetailsInfo(dto));
            retOrderInfo.setTotalNum(retOrderInfo.getTotalNum()+dto.getNumber());
            retOrderInfo.setTotalPrice(retOrderInfo.getTotalPrice()
                    .add(dto.getCommodyPrice().multiply(new BigDecimal(dto.getNumber()))));

        });
        return infoList;
    }

    private OrderDetailsInfo toOrderDetailsInfo(OrderDetailsInfoDTO dto){
        return OrderDetailsInfo.builder()
                .cancelFlag(dto.getCancelFlag())
                .commodyId(dto.getCommodyId())
                .commodyPrice(dto.getCommodyPrice())
                .id(dto.getId())
                .tagsName(dto.getTagsName())
                .inserttime(DateUtils.getDateString(dto.getInserttime()))
                .isCooking(dto.getIsCooking())
                .num(dto.getNumber())
                .siteNo(dto.getSiteNo())
                .totalPrice(dto.getCommodyPrice().multiply(new BigDecimal(dto.getNumber())))
                .build();
    }

    @Transactional
    public void reduceOrder(UpdateOrder orderInfo) {

        List<OrderInfo> orderInfoList=orderInfo.getOrderInfoList();
        List<OrderDetailsInfoDTO> list = new ArrayList<>();
        orderInfoList.forEach(vo->{
            OrderDetailsInfoDTO dto=toDetailsDTO(vo,orderInfo.getOrderNo(),orderInfo.getSiteNo(),null);
            list.add(dto);
        });

        orderDao.updateDetails(orderInfo.getOrderNo(),list);

        List<Long> ids=orderDao.getTotalIdsByDetailsId(list);

        orderDao.updateTotalInfo(ids);

    }

    public void deleteOrder(String orderNo, Long totalId) {
        orderDao.deleteOrder(orderNo,totalId);
    }

    public void confirmOrder(String orderNo) {
        orderDao.confirmOrder(orderNo);
    }

    public CeculatorOrderInfo ceculatorOrder(String orderNo) {


        List<OrderDetailsInfoDTO> dtoList = orderDao.getOrderList(orderNo);
        Long totalPrice=dtoList.stream().collect(Collectors.summingLong(OrderDetailsInfoDTO::getPriceLong));
        BigDecimal ceculator=new BigDecimal(totalPrice).divide(new BigDecimal("100"));
        CeculatorOrderInfo ceculatorOrderInfo=CeculatorOrderInfo
                .builder()
                .totalPrice(ceculator)
                .orderNo(orderNo)
                .build();
        return ceculatorOrderInfo;
    }

    public PayOrderRes payOrder(String orderNo) {

        OrderTotalInfo totalInfo=this.orderInfo(orderNo);
        var map=buildPayRequest(totalInfo);
        orderDao.createPaymentOrder(map);

        return PayOrderRes.builder().retMap(map).build();
    }

    private Map<String,Object> buildPayRequest(OrderTotalInfo totalInfo){
        Map<String,Object> map = new HashMap<>();
        map.put("orderNo",totalInfo.getOrderNo());
        map.put("totalPrice",totalInfo.getTotalPrice());
        return map;
    }


    public void confirmPayment(String orderNo) {

    }
}
