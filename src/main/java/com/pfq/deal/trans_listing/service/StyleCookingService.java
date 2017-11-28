package com.pfq.deal.trans_listing.service;

import com.pfq.deal.trans_listing.bean.input.stylecooking.InCreateVo;
import com.pfq.deal.trans_listing.bean.output.commody.CommodyInfoVo;
import com.pfq.deal.trans_listing.bean.output.stylecooking.StyleCookingInfo;
import com.pfq.deal.trans_listing.dao.ICommodyDao;
import com.pfq.deal.trans_listing.dao.IStyleCookingDao;
import com.pfq.deal.trans_listing.dto.StyleCookingDto;
import com.pfq.deal.trans_listing.util.DateUtils;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by steven on 2017/11/17.
 */
@Service
public class StyleCookingService implements IBaseService{

    @Autowired
    IStyleCookingDao styleCooking;

    @Autowired
    ShopService shopService;

    public void save(InCreateVo inCreateVo) {

        styleCooking.save(toDto(inCreateVo));

    }

    private StyleCookingDto toDto(InCreateVo inCreateVo) {
        return StyleCookingDto.builder().styleName(inCreateVo.getStyleName()).build();
    }

    public StyleCookingInfo select(Integer id) {

        return toStyleCookingInfo(styleCooking.select(id));
    }

    private StyleCookingInfo toStyleCookingInfo(StyleCookingDto dto) {
        dto = Optional.ofNullable(dto).orElse(StyleCookingDto.builder().build());
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
        StyleCookingDto dto = toDto(inputVo);
        dto.setId(id);
        styleCooking.update(dto);
    }

    public List<StyleCookingInfo> selectList(Integer shopId) {

        var retList = new ArrayList<StyleCookingInfo>();
        Optional.ofNullable(styleCooking.selectList()).ifPresent(list -> {
            list.parallelStream().forEach(dto -> {
                retList.add(toStyleCookingInfo(dto));
            });
        });
        if(shopId!=null){
            List<Integer> styleIds=shopService.getStyle(shopId);
            return retList.parallelStream().filter(new Predicate<StyleCookingInfo>() {
                @Override
                public boolean test(StyleCookingInfo styleCookingInfo) {
                    return styleIds.contains(styleCookingInfo.getId());
                }
            }).collect(Collectors.toList());
        }
        return retList;
    }

    @Override
    public boolean isExist(String id) {
        return this.select(Integer.parseInt(id)).getStyleName()!=null;
    }
}
