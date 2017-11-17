package com.pfq.deal.trans_listing.service;

import com.pfq.deal.trans_listing.bean.input.stylecooking.InCreateVo;
import com.pfq.deal.trans_listing.bean.output.stylecooking.StyleCookingInfo;
import com.pfq.deal.trans_listing.dao.IStyleCooking;
import com.pfq.deal.trans_listing.dto.StyleCookingDto;
import com.pfq.deal.trans_listing.util.DateUtils;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by steven on 2017/11/17.
 */
@Service
public class StyleCookingService {

    @Autowired
    IStyleCooking styleCooking;


    public void save(InCreateVo inCreateVo) {

        styleCooking.save(toDto(inCreateVo));

    }

    private StyleCookingDto toDto(InCreateVo inCreateVo){
        return StyleCookingDto.builder().styleName(inCreateVo.getStyleName()).build();
    }

    public StyleCookingInfo select(Integer id) {

        return toStyleCookingInfo(styleCooking.select(id));
    }

    private StyleCookingInfo toStyleCookingInfo(StyleCookingDto dto) {
        dto=Optional.ofNullable(dto).orElse(StyleCookingDto.builder().build());
        return StyleCookingInfo.builder()
                        .id(dto.getId())
                        .inserttime(DateUtils.getDateString(dto.getInserttime()))
                        .styleName(dto.getStyleName())
                        .updatetime(DateUtils.getDateString(dto.getUpdatetime())).build();
    }

    public void delete(Integer id) {

        styleCooking.delete(id);
    }

    public void update(Integer id, InCreateVo inputVo) {
        StyleCookingDto dto=toDto(inputVo);
        dto.setId(id);
        styleCooking.update(dto);
    }

    public List<StyleCookingInfo> selectList() {
        var retList = new ArrayList<StyleCookingInfo>();
        Optional.ofNullable(styleCooking.selectList()).ifPresent(list->{
            list.parallelStream().forEach(dto -> {
                retList.add(toStyleCookingInfo(dto));
            });
        });
        return retList;
    }
}
