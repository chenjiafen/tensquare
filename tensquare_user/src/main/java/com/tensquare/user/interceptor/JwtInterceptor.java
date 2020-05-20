package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器，抽取冲request中获取的token
 *
 * @author tyler.chen
 * @date 2020/4/25 18:54
 */

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
   @Autowired
   private JwtUtil jwtUtil;

   @Override
   //在controller方法之前执行
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      //1、Authorization获得token
      String header = request.getHeader ( "Authorization" );
      //判断请求头是否存在（存在）
      if (!StringUtils.isEmpty ( header ) && header.startsWith ( "Bearer " )) {
         //3、截去Bearer部分
         String token = header.substring ( 7 );
         Claims calims = jwtUtil.parserToken ( token );//解析
         if (calims != null) {//解析出用户信息
            //将用户信息放入request域
            String role = (String) calims.get ( "role" );


            //calims_rol 管理员
            // calims_user 普通用户
            request.setAttribute ( "calims_" + role, calims );
            System.out.println ( "将jwt的载荷信息放入request域：calims_" + role );
         }
      }

      //放行
      return true;
   }
}
