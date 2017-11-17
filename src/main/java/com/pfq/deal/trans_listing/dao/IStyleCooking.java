package com.pfq.deal.trans_listing.dao;

import com.pfq.deal.trans_listing.dto.StyleCookingDto;

import java.util.List;

/**
 * Created by steven on 2017/11/17.
 */
public interface IStyleCooking {


    int save(StyleCookingDto dto);

    StyleCookingDto select(Integer id);

    int delete(Integer id);

    int update(StyleCookingDto dto);

    List<StyleCookingDto> selectList();
}
