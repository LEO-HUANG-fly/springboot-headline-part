package com.atguigu.mapper;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.vo.PortalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author LeoHuanf
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-07-07 14:47:47
* @Entity com.atguigu.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage selectMyPage(IPage page, @Param("portalVo")PortalVo portalVo);

    Map queryDetailMap(Integer hid);
}




