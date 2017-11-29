package com.pfq.deal.trans_listing.controller.admin;

import com.pfq.deal.trans_listing.bean.input.InCreateShopCommodyVo;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.service.CommodyService;
import com.pfq.deal.trans_listing.service.ShopService;
import com.pfq.deal.trans_listing.service.intfc.MyPathavalibe;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * Created by steven on 2017/11/18.
 */
@RestController
@RequestMapping("/sys/admin/shops/{shopId}/Commodys")
public class ShopCommodyController {

    @Autowired
    ShopService shopService;

    @RequestMapping(value = "/{commodyId}", method = RequestMethod.POST)
    public ResponseEntity<BaseOutput> create(@MyPathavalibe(clz = ShopService.class) Integer shopId, @MyPathavalibe(clz= CommodyService.class) Long commodyId, InCreateShopCommodyVo inputVo) {

        inputVo.setCommody_id(commodyId);
        inputVo.setShop_id(shopId);
        shopService.saveShopCommody(inputVo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }
    @RequestMapping(value = "/{commodyId}/tags",method = RequestMethod.POST)
    public ResponseEntity<BaseOutput> createTag(@MyPathavalibe(clz = ShopService.class) Integer shopId, @MyPathavalibe(clz = CommodyService.class) Long commodyId, @NotNull(message = "tags is empty") String tags){

        shopService.saveTags(shopId,commodyId,tags);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseOutput());
    }

    @RequestMapping(value = "/{commodyId}/tags/{tagId}",method = RequestMethod.DELETE)
    public ResponseEntity<BaseOutput> deleteTag(@MyPathavalibe(clz = ShopService.class) Integer shopId,@MyPathavalibe(clz = CommodyService.class) Long commodyId, @PathVariable Long tagId){

        shopService.deleteTag(shopId,commodyId,tagId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

    @RequestMapping(value = "/{commodyId}",method = RequestMethod.PUT)
    public ResponseEntity<BaseOutput> update(@MyPathavalibe(clz = ShopService.class)Integer shopId, @MyPathavalibe(clz = CommodyService.class) Long commodyId, InCreateShopCommodyVo inputVo){

        shopService.updateCommodyInfo(shopId,commodyId,inputVo);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

    @RequestMapping(value = "/{commodyId}",method = RequestMethod.DELETE)
    public ResponseEntity<BaseOutput> delete(@MyPathavalibe(clz = ShopService.class) Integer shopId, @MyPathavalibe(clz = CommodyService.class) Long commodyId){

        shopService.deleteCommodyInfo(shopId,commodyId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<BaseOutput> getAll(@MyPathavalibe(clz = ShopService.class) Integer shopId){

        var outPutInfo=shopService.getAll(shopId);
        return ResponseEntity.status(HttpStatus.OK).body(outPutInfo);
    }

    @RequestMapping(value = "/{commodyId}",method = RequestMethod.GET)
    public ResponseEntity<BaseOutput> get(@MyPathavalibe(clz = ShopService.class) Integer shopId, @MyPathavalibe(clz = CommodyService.class) Long commodyId){

        var outPutInfo=shopService.findCommody(commodyId);
        return ResponseEntity.status(HttpStatus.OK).body(outPutInfo);
    }
    
    @RequestMapping(value = "/{commodyId}/up",method = RequestMethod.POST)
    public ResponseEntity<BaseOutput> showTo(@MyPathavalibe(clz = ShopService.class) Integer shopId, @MyPathavalibe(clz = CommodyService.class) Long commodyId){

        shopService.commodyUp(commodyId,shopId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }
    
    @RequestMapping(value = "/{commodyId}/down",method = RequestMethod.POST)
    public ResponseEntity<BaseOutput> down(@MyPathavalibe(clz = ShopService.class) Integer shopId, @MyPathavalibe(clz = CommodyService.class) Long commodyId){

        shopService.commodyDown(commodyId,shopId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseOutput());
    }
    
}
