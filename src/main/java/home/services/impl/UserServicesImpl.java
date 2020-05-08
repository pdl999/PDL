package home.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import home.dao.UserMapper;
import home.pojo.User;
import home.services.userServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserServicesImpl implements userServices {
    @Resource
    private UserMapper userMapper;
    public User user;
    QueryWrapper<User> wrapper;
    Map<String, Object> map = Maps.newHashMap();



    @Override
    public User loginId(String userId, String password) {
        wrapper = new QueryWrapper<>();
        wrapper.eq("username",userId);
        wrapper.eq("pwd",password);
        wrapper.eq("isowner","房客");//为了防止房东房客重名，要检查身份
        User user = userMapper.selectOne(wrapper);
        return userMapper.selectOne(wrapper);
}

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public User findUserById(String userId) {
        wrapper = new QueryWrapper<>();
        wrapper.eq("username",userId);
        user = userMapper.selectOne(wrapper);
        return  user;
    }

    @Override
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getUserId().toString())
                .sign(Algorithm.HMAC256(user.getPwd()));
        return token;
    }
}
