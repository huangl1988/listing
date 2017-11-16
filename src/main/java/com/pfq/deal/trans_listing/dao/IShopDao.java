package com.pfq.deal.trans_listing.dao;

import com.pfq.deal.trans_listing.dto.ShopDto;

/**
 * Created by steven on 2017/11/16.
 */
public interface IShopDao {


    int save(ShopDto shopDto);

    ShopDto select(Integer shopId);
}
