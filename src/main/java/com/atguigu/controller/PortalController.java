package com.atguigu.controller;

import com.atguigu.pojo.vo.PortalVo;
import com.atguigu.service.HeadlineService;
import com.atguigu.service.TypeService;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: PortalController
 * Package: com.atguigu.controller
 * Description：
 *
 * @Author LeoHuang
 * @Create 2024/7/8 10:41
 * @Version 1.0
 */
@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController{
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result  findAllTypes(){
        Result result=typeService.findAllTYpes();
        return  result;
    }
   @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo ){
       Result resullt=headlineService.findNewsPage(portalVo);
       return resullt;
   }


   @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result=headlineService.showHeadlineDetail(hid);
        return result;
   }




}
