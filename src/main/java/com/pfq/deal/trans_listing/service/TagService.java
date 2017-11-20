package com.pfq.deal.trans_listing.service;

import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.dao.ITagDao;
import com.pfq.deal.trans_listing.dto.TagDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by steven on 2017/11/20.
 */
@Service
@Slf4j
public class TagService {

    @Autowired
    ITagDao tagDao;
    @Autowired
    ShopService shopService;


    public TagDto findTagById(Long tagId) {
        return tagDao.findTagDto(tagId);
    }

    public void saveTag(TagDto tag){
     tagDao.saveTag(tag);
    }

    public void delete(Long tagId) {

        shopService.deleteTag(null,null,tagId);
        tagDao.delete(tagId);
    }
}
