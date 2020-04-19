package com.example.gcy.config;

import com.example.gcy.interceptor.StudentInterceptor;
import com.example.gcy.interceptor.TeacherInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class gcyConfig implements WebMvcConfigurer {

    @Autowired
    private TeacherInterceptor teacherInterceptor;
    @Autowired
    private StudentInterceptor studentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(teacherInterceptor).addPathPatterns("/teacher/**").excludePathPatterns("/teacher/login").excludePathPatterns("/teacher/register").excludePathPatterns("/teacher/logout");
        registry.addInterceptor(studentInterceptor).addPathPatterns("/student/**").excludePathPatterns("/student/login").excludePathPatterns("/student/register").excludePathPatterns("/student/logout");
    }
}
