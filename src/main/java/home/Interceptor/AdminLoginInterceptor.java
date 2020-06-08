package home.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//自定义拦截器，记得要添加到配置文件中
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("拦截器1工作");
        Object admin = request.getSession().getAttribute("admin");
        if (admin == null){
            request.setAttribute("adminLogTip","请登录后访问管理界面");
            request.getRequestDispatcher("/admin/login").forward(request,response);
            return false;//false代表不放行
        }else{
            return true;//true代表放行
        }
    }
}
