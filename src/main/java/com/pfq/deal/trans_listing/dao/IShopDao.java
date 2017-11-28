package com.pfq.deal.trans_listing.dao;

import com.pfq.deal.trans_listing.bean.input.InCreateShopCommodyVo;
import com.pfq.deal.trans_listing.dto.ShopCommodyDto;
import com.pfq.deal.trans_listing.dto.ShopDto;
import com.pfq.deal.trans_listing.dto.TagDto;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steven on 2017/11/16.
 */
public interface IShopDao {


    int save(ShopDto shopDto);

    ShopDto select(@Param("shopId")Integer shopId);

    int delete(@Param("shopId") Integer shopId);

    int update(ShopDto dto);

    List<ShopDto> selectList(@Param("regionId") Integer regionId);
//-----
    int saveStyleCommodyRelation(@Param("styleId") Integer styleId, @Param("commodyList") List<Long> commodyIdList,@Param("shopId") Integer shopId);

    int saveStyle(@Param("styleId") Integer styleId, @Param("shopId")Integer shopId);

    int selectStyleExist(@Param("styleId") Integer styleId, @Param("shopId")Integer shopId);

    int deleteStyle(Integer shopId, Integer styleId);

    int deleteStyleCommody(@Param("shopId")Integer shopId, @Param("styleId")Integer styleId, @Param("commodyId")Long commodyId);

    int existCommodyInShop(@Param("commodyId") Long commodyId,@Param("shopId") Integer shopId);

    int saveShopCommody(ShopCommodyDto shopCommodyDto);

    int saveTagCommodyRef(@Param("shopId") Integer shopId,@Param("commodyId") Long commodyId,@Param("list") List<TagDto> tagIds);

    void deleteTagRef(@Param("shopId") Integer shopId,@Param("commodyId") Long commodyId,@Param("tagId") Long tagId);

    void updateCommodyInfo(ShopCommodyDto inputVo);

    int deleteCommody(@Param("shopId")Integer shopId, @Param("commodyId")Long commodyId);

    List<Long> getTagRef(@Param("commodyId") Long commodyId, @Param("shopId")Long shopId);

    ShopCommodyDto getShopCommodyByCommodyId(@Param("commodyId")Long commodyId);

    List<Long> getStyleCommodyRef(@Param("shopId")Integer shopId, @Param("styleId") Integer styleId);

    List<Integer> getStyle(Integer shopId);

	List<Integer> getCommodyStylesByCommodyListList(@Param("id")Long shopId);
}
