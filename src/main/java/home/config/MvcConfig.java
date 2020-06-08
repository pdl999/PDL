package home.config;

import home.Interceptor.AdminLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //   过滤静态资源，第一个方法设置访问路径前缀，第二个方法设置资源路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    //    使用了重定向跳转使得视图解析失效，需要自己定义
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/static/adm/admin").setViewName("/adm/admin");
    }
    //    添加拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("拦截器2工作");

        registry.addInterceptor(new AdminLoginInterceptor())
                .addPathPatterns("/admin/**")//这里代表要拦截的请求，**代表全部请求
                .excludePathPatterns("/admin/login");//这里代表要放行的请求
    }
}
