package com.atguigu.controller;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.atguigu.controller
 * Description：
 *
 * @Author LeoHuang
 * @Create 2024/7/7 17:32
 * @Version 1.0
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper jwtHelper;

    //登录方法
    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result=userService.login(user);
        return result;
    }


    //根据token获取用户数据
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result result=userService.getUserInfo(token);
        return result;
    }


    //检查用户名是否可用
    @GetMapping ("checkUserName")
    public Result checkUserName(String username){
        Result result=userService.checkUserName(username);
        return result;
    }

    //用户注册
    @PostMapping ("regist")
    public Result regist(@RequestBody  User user){
        Result result = userService.regist(user);
        return result;
    }

    //用户登录检查
    public Result checkLogin(@RequestHeader String token){
        boolean expiration = jwtHelper.isExpiration(token);
        if(expiration){
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }


}
