package com.pfq.deal.trans_listing.dao;

import com.pfq.deal.trans_listing.dto.OrderDetailsInfoDTO;
import com.pfq.deal.trans_listing.dto.OrderTotalDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by steven on 2017/12/4.
 */
public interface IOrderDao {
    void save(OrderTotalDTO totalDTO);

    void saveDetails(OrderDetailsInfoDTO detailsInfoDTO);

    String findOrderNoBySiteNo(String siteNo);

    List<OrderDetailsInfoDTO> getOrderList(String orderNo);

    void updateDetails(String orderNo, List<OrderDetailsInfoDTO> list);

    void updateTotalInfo(List<Long> ids);

    List<Long> getTotalIdsByDetailsId(List<OrderDetailsInfoDTO> list);

    void deleteOrder(String orderNo, Long totalId);

    void confirmOrder(String orderNo);

    void createPaymentOrder(Map<String, Object> stringObjectMap);
}
