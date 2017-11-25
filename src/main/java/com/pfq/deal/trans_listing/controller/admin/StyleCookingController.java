package com.pfq.deal.trans_listing.controller.admin;

import com.pfq.deal.trans_listing.bean.input.stylecooking.InCreateVo;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.bean.output.stylecooking.RetStyleCookingVo;
import com.pfq.deal.trans_listing.bean.output.stylecooking.RetStyleCookingsVo;
import com.pfq.deal.trans_listing.service.StyleCookingService;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * Created by steven on 2017/11/17.
 */
@RestController
@RequestMapping("/sys/admin/style")
public class StyleCookingController {

    @Autowired
    StyleCookingService styleCookingService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<BaseOutput> create(InCreateVo inCreateVo) {
        styleCookingService.save(inCreateVo);
        return ResponseEntity.ok(new BaseOutput());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<BaseOutput> select(@PathVariable Integer id) {
        RetStyleCookingVo styleCookingVo=RetStyleCookingVo.builder().styleCookingInfo(styleCookingService.select(id)).build();
        styleCookingVo.doSucc();
        return ResponseEntity.ok(styleCookingVo);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<BaseOutput> delete(@PathVariable Integer id) {
        styleCookingService.delete(id);
        return ResponseEntity.ok(new BaseOutput());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public ResponseEntity<BaseOutput> update(@PathVariable Integer id,InCreateVo inputVo) {
        styleCookingService.update(id,inputVo);
        return ResponseEntity.ok(new BaseOutput());
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<BaseOutput> selectList(){
        var retStyleCookingsVo=RetStyleCookingsVo.builder().retList(styleCookingService.selectList(null)).build();
        retStyleCookingsVo.doSucc();

        return ResponseEntity.ok(new BaseOutput());
    }
    /**
     * 返回商店所有菜系
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/{shopId}",method = RequestMethod.GET)
    public ResponseEntity<BaseOutput> selectList(@PathVariable Integer shopId){
        var retStyleCookingsVo=RetStyleCookingsVo.builder().retList(styleCookingService.selectList(shopId)).build();
        retStyleCookingsVo.doSucc();

        return ResponseEntity.ok(new BaseOutput());
    }

}

