package home.services;

import home.pojo.User;

public interface UserServices {
    /**
     * 租客登录
     * @param uname
     * @param upassword
     * @return
     */
     User renterLogin(String uname, String upassword);

    /**
     * 租客注册
     * @param user
     * @return
     */
    int renterReg(User user);

    /**
     * 房东登录
     * @param uname
     * @param upassword
     * @return
     */
    User hostLogin(String uname, String upassword);

    /**
     * 房东注册
     * @param user
     * @return
     */
    int hostReg(User user);

    /**
     * 主要用于jwt查询此id用户是否与token中的用户一致
     * @param userId
     * @return
     */
    User findUserById(Integer userId);

    /**
     * 获得token
     * @param user
     * @return
     */
    String getToken(User user);

}
