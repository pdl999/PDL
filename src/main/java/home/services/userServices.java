package home.services;

import home.pojo.User;
import java.util.Map;

public interface userServices {


    /**
     * 登陆验证
     * @param userId 用户id
     * @param password  密码
     * @return result
     * 其中0代表失败，1代表成功.-1代表故障
     */
    User loginId(String userId, String password);

    /**
     * 根据之前登陆时获取到的User,返回给页面
     * @return User对象+判断
     */
    User getUser();



    /**
     * 主要用于jwt查询此id用户是否与token中的用户一致
     * @param userId
     * @return
     */
    User findUserById(String userId);

    /**
     * 获得token
     * @param user
     * @return
     */
    String getToken(User user);




}
