package com.pfq.deal.trans_listing.controller.admin;

import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by steven on 2017/11/20.
 */
@RestController
@RequestMapping("/sys/admin/tags")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(value = "/{tagId}",method = RequestMethod.DELETE)
    public ResponseEntity<IBaseOutput> delete(@PathVariable Long tagId){
        tagService.delete(tagId);
        return ResponseEntity.ok(new BaseOutput());
    }

}
