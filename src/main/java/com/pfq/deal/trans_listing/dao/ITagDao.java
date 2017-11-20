package com.pfq.deal.trans_listing.dao;

import com.pfq.deal.trans_listing.dto.TagDto;
import org.apache.ibatis.annotations.Param;

/**
 * Created by steven on 2017/11/20.
 */
public interface ITagDao {

    void saveTag(TagDto dto);

    TagDto findTagDto(@Param("id") Long tagId);

    void delete(@Param("id") Long tagId);
}
