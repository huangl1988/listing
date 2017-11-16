package com.pfq.deal.trans_listing.service;

import com.pfq.deal.trans_listing.bean.input.shop.InCreateVo;
import com.pfq.deal.trans_listing.bean.output.Shop.ShopInfo;
import com.pfq.deal.trans_listing.dao.IShopDao;
import com.pfq.deal.trans_listing.dto.ShopDto;
import com.pfq.deal.trans_listing.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by steven on 2017/11/16.
 */
@Service
public class ShopService {

    @Autowired
    IShopDao shopDao;

    public void save(InCreateVo inputVo) {
        shopDao.save(toShopDto(inputVo));
    }

    public ShopInfo selectOne(Integer shopId) {

        return toShopInfo(Optional.ofNullable(shopDao.select(shopId)).orElse(ShopDto.builder().build()));
    }

    private ShopInfo toShopInfo(ShopDto shop){
        return ShopInfo.builder().endTime(DateUtils.getDateString(shop.getEndTime()))
                .id(shop.getId())
                .point(shop.getPoint())
                .regionId(shop.getRegionId())
                .shopAddres(shop.getShopAddres())
                .shopCode(shop.getShopCode())
                .shopName(shop.getShopName())
                .startTime(DateUtils.getDateString(shop.getStartTime())).build();
    }

    private ShopDto toShopDto(InCreateVo inputVo){


        return ShopDto.builder().point(inputVo.getPoint())
                .endTime(DateUtils.getTimeDate(inputVo.getEndTime()))
                .regionId(inputVo.getRegionId())
                .shopAddres(inputVo.getShopAddres())
                .shopCode(inputVo.getShopCode())
                .shopName(inputVo.getShopName())
                .startTime(DateUtils.getTimeDate(inputVo.getStartTime())).build();
    }

}
