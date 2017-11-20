package com.pfq.deal.trans_listing.controller.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pfq.deal.trans_listing.bean.input.stylecooking.InStyleCommodyRelationVo;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.IBaseOutput;
import com.pfq.deal.trans_listing.service.ShopService;
import com.pfq.deal.trans_listing.service.StyleCookingService;
import com.pfq.deal.trans_listing.service.intfc.ICommody;
import com.pfq.deal.trans_listing.service.intfc.IShop;
import com.pfq.deal.trans_listing.service.intfc.IStyleCooking;
import lombok.experimental.var;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<IBaseOutput> create(@PathVariable @IStyleCooking Integer styleId, @PathVariable @IShop Integer shopId, InStyleCommodyRelationVo inputVo){
        List<Long> commodyIdList=null;
        if(StringUtils.isEmpty(inputVo.getCommodyListJson()))
            commodyIdList=null;
        else
            commodyIdList= gson.fromJson(inputVo.getCommodyListJson(),new TypeToken<List<Long>>(){}.getType());
        shopService.saveCommodyRelation(styleId,commodyIdList,shopId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }

    @RequestMapping(value = "/{commodyId}",method = RequestMethod.DELETE)
    public ResponseEntity<IBaseOutput> delete(@PathVariable @IStyleCooking Integer styleId,@PathVariable @IShop Integer shopId,@PathVariable @ICommody Long commodyId){

        shopService.deleteStyleCommody(shopId,styleId,commodyId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    public ResponseEntity<IBaseOutput> delete(@PathVariable @IStyleCooking Integer styleId,@PathVariable @IShop Integer shopId){

        shopService.deleteStyle(shopId,styleId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<IBaseOutput> getAll(@PathVariable @IStyleCooking Integer styleId,@PathVariable @IShop Integer shopId){

        var RetCommodyListVo=shopService.getStyleCommodyList(shopId,styleId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

}
