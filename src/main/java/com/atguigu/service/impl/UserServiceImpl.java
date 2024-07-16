package com.atguigu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author LeoHuanf
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-07-07 14:47:47
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**登录业务
       1:根据账户，查询用户对象 -loginUser
       2：如果用户 对象为null,查询失败，账号错误！501
       3：对比，密码失败，返回503
       4：根据用户id生成一个token，token->result 返回
    */
    @Override
    public Result login(User user) {
        //根据账号查询数据
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);

        if(loginUser==null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        //对比密码
        if(!StringUtils.isEmpty(user.getUserPwd())&& MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
            //登录成功
            //根据用户id生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map data = new HashMap();
            data.put("token",token);
            return Result.ok(data);
        }
        //密码错误
        return Result.build(null,ResultCodeEnum.USERNAME_ERROR);
    }

    /**
        根据token获取用户数据
        1：token是否在有效期
        2：根据token解析userID
        3:根据用户id查询数据
        4：去掉密码，封装result结果返回即可
     */
    @Override
    public Result getUserInfo(String token) {
        boolean expiration = jwtHelper.isExpiration(token);
        if(expiration){
            //失效，未登录看待
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        Long userId = jwtHelper.getUserId(token);
        User user = userMapper.selectById(userId);
        user.setUserPwd("");
        Map data = new HashMap();
        data.put("loginUser",user);
        return Result.ok(data);
    }

    /**
     *检查账号是否可用
     * 1：根据账号进行count查询
     * 2：count==0可用
     * 3：count>0不可用
     */

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,username);
        Long count = userMapper.selectCount(lambdaQueryWrapper);
        if(count==0){
            return Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    /**
     * 1:检查账号是否被注册
     * 2：密码加密处理
     * 3：账号数据保存
     * 4：返回结果
     */
    @Override
    public Result regist(User user) {

        LambdaQueryWrapper<User> queryWrapper
                = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        userMapper.insert(user);

        return Result.ok(null);
    }



}




