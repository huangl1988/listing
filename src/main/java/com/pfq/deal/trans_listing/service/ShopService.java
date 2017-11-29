package com.pfq.deal.trans_listing.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.pfq.deal.trans_listing.bean.input.InCreateShopCommodyVo;
import com.pfq.deal.trans_listing.bean.output.commody.CommodyInfoVo;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyList;
import com.pfq.deal.trans_listing.bean.output.commody.RetCommodyVo;
import com.pfq.deal.trans_listing.dao.ICommodyDao;
import com.pfq.deal.trans_listing.dto.ShopCommodyDto;
import com.pfq.deal.trans_listing.dto.TagDto;
import com.pfq.deal.trans_listing.exception.BusinessException;
import lombok.experimental.var;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfq.deal.trans_listing.bean.input.shop.InCreateVo;
import com.pfq.deal.trans_listing.bean.output.shop.ShopInfo;
import com.pfq.deal.trans_listing.dao.IShopDao;
import com.pfq.deal.trans_listing.dto.ShopDto;
import com.pfq.deal.trans_listing.util.DateUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by steven on 2017/11/16.
 */
@Service
public class ShopService extends IBaseService{

    @Autowired
    IShopDao shopDao;

    @Autowired
    CommodyService commodyService;
    @Autowired
    TagService tagService;

    public void save(InCreateVo inputVo) {
        shopDao.save(toShopDto(inputVo));
    }

    public ShopInfo selectOne(Integer shopId) {

        return toShopInfo(Optional.ofNullable(shopDao.select(shopId)).orElse(ShopDto.builder().build()));
    }

    private ShopInfo toShopInfo(ShopDto shop) {
        return ShopInfo.builder().endTime(DateUtils.getDateString(shop.getEndTime()))
                .id(shop.getId())
                .point(shop.getPoint())
                .regionId(shop.getRegionId())
                .shopAddres(shop.getShopAddres())
                .shopCode(shop.getShopCode())
                .shopName(shop.getShopName())
                .startTime(DateUtils.getDateString(shop.getStartTime())).build();
    }

    private ShopDto toShopDto(InCreateVo inputVo) {


        return ShopDto.builder().point(inputVo.getPoint())
                .endTime(DateUtils.getTimeDate(inputVo.getEndTime()))
                .regionId(inputVo.getRegionId())
                .shopAddres(inputVo.getShopAddres())
                .shopCode(inputVo.getShopCode())
                .shopName(inputVo.getShopName())
                .startTime(DateUtils.getTimeDate(inputVo.getStartTime())).build();
    }

    public void delete(Integer id) {
        shopDao.delete(id);
    }

    public void update(InCreateVo inputVo, Integer id) {
        ShopDto dto = toShopDto(inputVo);
        dto.setId(id);
        shopDao.update(dto);
    }

    public List<ShopInfo> selectList(Integer regionId) {
        var retList = new ArrayList<ShopInfo>();
        Optional.ofNullable(shopDao.selectList(regionId)).ifPresent(lists -> {
            lists.forEach(dto -> {
                retList.add(toShopInfo(dto));
            });
        });

        return retList;
    }
    @Transactional(propagation=Propagation.REQUIRED)
    public void saveCommodyRelation(Integer styleId, List<Long> commodyIdList, Integer shopId) {

        Optional.ofNullable(commodyIdList).ifPresent(list -> {
            list.parallelStream().filter(new Predicate<Long>() {
                @Override
                public boolean test(Long id) {
                    return commodyService.findById(id).getCommodyInfo() == null;
                }
            }).collect(Collectors.toList());
            shopDao.saveStyleCommodyRelation(styleId, list, shopId);
            //shopDao.saveStyleCommodyRelation(0, list, shopId);
        });
        if(shopDao.selectStyleExist(styleId,shopId)>0){
            return;
        }else
            if(1!=shopDao.saveStyle(styleId,shopId)){
                throw new BusinessException("add style to shop fail");
            };
    }

    public int deleteStyleCommody(Integer shopId, Integer styleId, Long commodyId) {

        return shopDao.deleteStyleCommody(shopId,styleId,commodyId);

    }

    public void deleteStyle(Integer shopId, Integer styleId) {

        if(styleId<1){
            throw new BusinessException("style not exist!");
        }

        if(deleteStyleCommody(shopId,styleId,null)>1){
            throw new BusinessException(" style cooking is not empty!");
        }

        shopDao.deleteStyle(shopId,styleId);

    }

    public void saveShopCommody(InCreateShopCommodyVo inputVo) {

        if(shopDao.existCommodyInShop(inputVo.getCommody_id(),inputVo.getShop_id())>1){

            return;
        }
        shopDao.saveShopCommody(toShopCommodyDto(inputVo));
    }

    private ShopCommodyDto toShopCommodyDto(InCreateShopCommodyVo inputVo) {
        return ShopCommodyDto.builder()
                .commody_id(inputVo.getCommody_id())
                .picture_url(inputVo.getPicture_url())
                .shop_id(inputVo.getShop_id())
                .shop_price(inputVo.getShop_price())
                .showTime(DateUtils.getTimeDate(inputVo.getShowTime()))
                .show_flag(inputVo.getShow_flag()).build();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveTags(Integer shopId, Long commodyId, String tags) {
        var tagDtoList = new ArrayList<TagDto>();
        toTagList(tags,tagDtoList,shopId);
        tagDtoList.forEach(dto->{
            tagService.saveTag(dto);
        });
        shopDao.saveTagCommodyRef(shopId,commodyId,tagDtoList);
    }

    private void toTagList(String tags,List<TagDto> tagDtoList,Integer shopId) {
          Optional.ofNullable(tagDtoList).ifPresent(list->{
             Arrays.stream(tags.split(",")).filter(new Predicate<String>() {
                 @Override
                 public boolean test(String s) {

                     return StringUtils.isEmpty(s);
                 }
             }).collect(Collectors.toList()).parallelStream().forEach(tagName->{
                list.add(TagDto.builder().tagName(tagName).shopId(shopId).build());
             });
         });
    }

    public void deleteTag(Integer shopId, Long commodyId, Long tagId) {

        shopDao.deleteTagRef(shopId,commodyId,tagId);
    }

    public void updateCommodyInfo(Integer shopId, Long commodyId, InCreateShopCommodyVo inputVo) {
        inputVo.setShop_id(shopId);
        inputVo.setCommody_id(commodyId);
        shopDao.updateCommodyInfo(toShopCommodyDto(inputVo));
    }
    @Transactional
    public void deleteCommodyInfo(Integer shopId, Long commodyId) {
        shopDao.deleteStyleCommody(shopId,null,commodyId);
        shopDao.deleteTagRef(shopId,commodyId,null);
        shopDao.deleteCommody(shopId,commodyId);
    }

    public RetCommodyList getAll(Integer shopId) {
        return commodyService.selectList(shopId);
    }

    public ShopCommodyDto getShopCommodyByCommodyId(Long commodyId) {
        return shopDao.getShopCommodyByCommodyId(commodyId);
    }

    public List<TagDto> getTagList(Long commodyId) {

         List<Long> tagIds = shopDao.getTagRef(commodyId,null);
         List<TagDto> tagDtoList=new ArrayList<>();
         Optional.ofNullable(tagIds).ifPresent(list->{
             list.parallelStream().forEach(tagId->{
                 tagDtoList.add(tagService.findTagById(tagId));
             });
         });

         return tagDtoList;
    }

    public RetCommodyVo findCommody(Long commodyId) {
        return commodyService.findById(commodyId);
    }

    public RetCommodyList getStyleCommodyList(Integer shopId, Integer styleId) {

        List<Long> commodys=shopDao.getStyleCommodyRef(shopId,styleId);

        RetCommodyList retVo=this.getAll(shopId);
        retVo.getRetList().parallelStream().filter(new Predicate<CommodyInfoVo>() {
            @Override
            public boolean test(CommodyInfoVo infoVo) {
                return commodys.contains(infoVo.getCommody_id());
            }
        }).collect(Collectors.toList());
        return retVo;
    }

    public List<Integer> getStyle(Integer shopId) {
        return shopDao.getStyle(shopId);
    }

    @Override
    public boolean isExist(String id) {
        return this.selectOne(Integer.parseInt(id)).getShopName()!=null;
    }

	public List<Integer> getCommodyStylesByCommodyListList(Long id) {
		
		return shopDao.getCommodyStylesByCommodyListList(id);
	}

	public void commodyUp(Long commodyId, Integer shopId) {
		shopDao.commodyUp(commodyId);
	}

	public void commodyDown(Long commodyId, Integer shopId) {
		shopDao.commodyDown(commodyId,null);
	}
	
	public void up(Integer shopId){
		shopDao.up(shopId);
	}
	@Transactional
	public void down(Integer shopId) {
		shopDao.commodyDown(null,shopId);
		shopDao.down(shopId);
	}
}
