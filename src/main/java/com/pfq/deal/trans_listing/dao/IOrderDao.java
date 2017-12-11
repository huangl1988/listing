package com.pfq.deal.trans_listing.dao;

import com.pfq.deal.trans_listing.dto.OrderDetailsInfoDTO;
import com.pfq.deal.trans_listing.dto.OrderTotalDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by steven on 2017/12/4.
 */
public interface IOrderDao {
    void save(OrderTotalDTO totalDTO);

    void saveDetails(OrderDetailsInfoDTO detailsInfoDTO);

    String findOrderNoBySiteNo(String siteNo);

    List<OrderDetailsInfoDTO> getOrderList(@Param("orderNo") String orderNo);

    void updateDetails(@Param("orderNo") String orderNo,@Param("dto") OrderDetailsInfoDTO dto);

    void updateTotalInfo(@Param("orderNo") String orderNo);

    void deleteOrder(@Param("orderNo") String orderNo,@Param("totalId") Long totalId);

    void confirmOrder(@Param("orderNo") String orderNo,@Param("totalId") Long totalId);

    void createPaymentOrder(Map<String, Object> stringObjectMap);

    void confirmPayOrder(@Param("orderNo") String orderNo);

    void deleteOrderDetails(@Param("orderNo") String orderNo,@Param("totalId") Long totalId);

    List<OrderDetailsInfoDTO> getUncookingOrder(Long shopId);

    int confirmCooking(@Param("id") Long id,@Param("num") int num);
}
