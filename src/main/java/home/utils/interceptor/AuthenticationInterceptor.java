package home.utils.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import home.pojo.User;
import home.services.impl.UserServicesImpl;
import home.utils.annotation.LevelAuthentication.AdminAuthentication;
import home.utils.annotation.LevelAuthentication.HostAuthentication;
import home.utils.annotation.LevelAuthentication.RentAuthentication;
import home.utils.annotation.PassToken;
import home.utils.annotation.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器，拦截fang访问，验证token（jwt）
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserServicesImpl userService;
    static User user;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                Integer userId;
                try {//这里判断是否是正确的token编码
                    userId =  Integer.valueOf(JWT.decode(token).getAudience().get(0)); //JWT.decode(token):解码
                    //getAudience():Get the value of the "aud" claim, or null if it's not available.
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("token值为null了！！！");
                }
                user = userService.findUserById(userId);
                if (user == null) {//这里判断此token编码有对应的userId是否有对应的user
                    throw new RuntimeException("token编码对了，但我们没有这个人");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPwd())).build();
                try {
                    jwtVerifier.verify(token); //这个不一致的话，会引发JWTVerificationException
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("token不一致，请不要乱改哦");
                }
            }

            //创建四个权限等级的注解判断
            //检查有没有需要用户权限的注解
            if(method.isAnnotationPresent(RentAuthentication.class)){
                if(user.getIsOwner().equals("房客")) return true;
                throw new RuntimeException("您无权限访问");
            }

            if(method.isAnnotationPresent(HostAuthentication.class)){
                if(user.getIsOwner().equals("房东")) return true;
                throw new RuntimeException("您无权限访问");
            }




        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

    /**
     *自定义属性值，供其获取
     */
    public static User getUser(){
        return user;
    }
}