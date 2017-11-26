package com.pfq.deal.trans_listing.controller.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pfq.deal.trans_listing.bean.input.stylecooking.InStyleCommodyRelationVo;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.service.CommodyService;
import com.pfq.deal.trans_listing.service.ShopService;
import com.pfq.deal.trans_listing.service.StyleCookingService;
import com.pfq.deal.trans_listing.service.intfc.MyPathavalibe;
import lombok.experimental.var;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by steven on 2017/11/18.
 */
@RestController
@RequestMapping("/sys/admin/shops/{shopId}/style-commody/{styleId}")
public class ShopStyleCommodyController {

    @Autowired
    ShopService shopService;

    Gson gson = new Gson();
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<IBaseOutput> create(@MyPathavalibe(clz = StyleCookingService.class) Integer styleId, @MyPathavalibe(clz = ShopService.class) Integer shopId, InStyleCommodyRelationVo inputVo){
        List<Long> commodyIdList=null;
        if(StringUtils.isEmpty(inputVo.getCommodyListJson()))
            commodyIdList=null;
        else
            commodyIdList= gson.fromJson(inputVo.getCommodyListJson(),new TypeToken<List<Long>>(){}.getType());
        shopService.saveCommodyRelation(styleId,commodyIdList,shopId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }

    @RequestMapping(value = "/{commodyId}",method = RequestMethod.DELETE)
    public ResponseEntity<IBaseOutput> delete(@MyPathavalibe(clz = StyleCookingService.class) Integer styleId, @MyPathavalibe(clz = ShopService.class) Integer shopId, @MyPathavalibe(clz = CommodyService.class) Long commodyId){

        shopService.deleteStyleCommody(shopId,styleId,commodyId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    public ResponseEntity<IBaseOutput> delete(@MyPathavalibe(clz = StyleCookingService.class) Integer styleId, @MyPathavalibe(clz = ShopService.class) Integer shopId){

        shopService.deleteStyle(shopId,styleId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<IBaseOutput> getAll(@MyPathavalibe(clz = StyleCookingService.class) Integer styleId, @MyPathavalibe(clz = ShopService.class) Integer shopId){

        var RetCommodyListVo=shopService.getStyleCommodyList(shopId,styleId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

}
