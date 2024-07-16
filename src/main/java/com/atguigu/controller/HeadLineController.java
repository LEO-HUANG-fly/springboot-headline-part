package com.atguigu.controller;

import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.service.impl.HeadlineServiceImpl;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: HeadLineController
 * Package: com.atguigu.controller
 * Description：
 *
 * @Author LeoHuang
 * @Create 2024/7/8 18:38
 * @Version 1.0
 */
@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadLineController {
    @Autowired
    private HeadlineService headlineService;
    //登录以后才可以访问
    @PostMapping("publish")
    public Result publish(@RequestBody  Headline headline, @RequestHeader String token){
        Result result = headlineService.publish(headline,token);
        return result;
    }
    @PostMapping("findHeadlineByHid")
    public Result findeHeadlineByHid(Integer hid){
        Headline headline=headlineService.getById(hid);
        Map data=new HashMap();
        data.put("headline",headline);
        return Result.ok(data);
    }

    //修改头条数据
    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        Result result=headlineService.updateData(headline);
        return result;
    }

    //删除头条数据
    @PostMapping("removeByHid")
    public Result removeByHid(Integer hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }

}
