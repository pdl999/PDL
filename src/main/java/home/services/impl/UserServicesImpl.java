package home.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import home.dao.UserMapper;
import home.pojo.User;
import home.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserMapper userMapper;
    QueryWrapper<User> wrapper;
    User user;

    //    用户登录，匹配用户名和密码
    @Override
    public User renterLogin(String uname, String upassword) {
        wrapper = new QueryWrapper<>();
        wrapper.eq("username",uname);
        wrapper.eq("pwd",upassword);
        wrapper.eq("isowner","房客");//为了防止房东房客重名，要检查身份
        User user = userMapper.selectOne(wrapper);
        return userMapper.selectOne(wrapper);
    }

    //    租户注册
    @Override
    public int renterReg(User user) {
        int i = userMapper.insert(user);
        return i;
    }



    //    房东登录
    @Override
    public User hostLogin(String uname, String upassword) {
        wrapper = new QueryWrapper<>();
        wrapper.eq("username",uname);
        wrapper.eq("pwd",upassword);
        wrapper.eq("isowner","房东");//为了防止房东房客重名，要检查身份
        user = userMapper.selectOne(wrapper);
        return userMapper.selectOne(wrapper);
    }

    //    房东注册
    @Override
    public int hostReg(User user) {
        int i = userMapper.insert(user);
        return i;
    }


    @Override
    public User findUserById(Integer userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("houseId",userId);
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
