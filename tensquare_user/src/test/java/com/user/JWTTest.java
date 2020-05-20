package com.user;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import sun.rmi.runtime.Log;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tyler.chen
 * @date 2020/4/25 14:40
 */
public class JWTTest {
    /**
     * 加密token
     */

    @Test
    public void fun1() {

        //builder  jwtToken的构建
        String toke = Jwts
                .builder ()             //jwtToken的构建
                .setId ( "888" )          //设置载荷部分的键值队
                .setSubject ( "tom" )      //设置载荷部分的键值队
                .setIssuedAt ( new Date () )  //设置token创建时间
                .signWith ( SignatureAlgorithm.HS256, "itcast" ) //设置秘钥的签名方式以及秘钥的值
                .compact ();//返回token
        System.out.println ( toke );
    }

    /**
     * 自定定义加密token
     */

    @Test
    public void fun01() {
        //将自定义的放入map
        Map<String, Object> map = new HashMap<> ();
        map.put ( "anc", "bcd" );
        map.put ( "heheh", "heihei" );

        //获取当前系统时间毫秒值
       long currentTime= System.currentTimeMillis ();
       long expirationTome=currentTime+1000*30;

        //builder  jwtToken的构建
        String toke = Jwts.builder ()             //jwtToken的构建
                .setId ( "888" )          //设置载荷部分的键值队
                .setSubject ( "tom" )      //设置载荷部分的键值队
                .setClaims ( map ) //设置自定义载荷
                .setIssuedAt ( new Date (currentTime) )  //设置token创建时间
                .setExpiration ( new Date ( expirationTome ) ) //设置30s后失效
                .signWith ( SignatureAlgorithm.HS256, "itcast" ) //设置秘钥的签名方式以及秘钥的值
                .compact ();//返回token
        System.out.println ( toke );
    }


    /**
     * 解析token
     */
    @Test
    public void fun2() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJ0b20iLCJpYXQiOjE1ODc3OTc0ODV9.Zo0pclVLXQLQC7I6-V9oxr59fwHKSjEXOgn3dzkuf0I";
        Claims body = Jwts.parser () //获取token解析器
                .setSigningKey ( "itcast" )  //指定解析秘钥
                .parseClaimsJws ( token )   //解析token的载荷部分
                .getBody ();//获得载荷部分
        //从载荷中提取信息

        String id = body.getId ();
        String name = body.getSubject ();
        Date data = body.getIssuedAt ();

        System.out.println ( body );
        System.out.println ( id + "=>" + name + "=>" + data );


    }

    /**
     * 解析自定义token
     */
    @Test
    public void fun02() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhbmMiOiJiY2QiLCJoZWhlaCI6ImhlaWhlaSIsImV4cCI6MTU4NzgwMDY2NywiaWF0IjoxNTg3ODAwNjM3fQ.EhafZv7nmhq9nAhpKxTCUV8I-CkoHSXqJfDxPy8fpUU";


        Claims body = Jwts.parser () //获取token解析器
                .setSigningKey ( "itcast" )  //指定解析秘钥
                .parseClaimsJws ( token )   //解析token的载荷部分
                .getBody ();//获得载荷部分
        //从载荷中提取信息

        String id = body.getId ();
        String name = body.getSubject ();
        Date data = body.getIssuedAt ();
        System.out.println ( body.get ( "anc" ));
        System.out.println ( body.get ( "heheh" ));
        System.out.println ("创建时间"+body.getIssuedAt ());
        System.out.println ("过期时间"+body.getExpiration ());

        System.out.println ("body:" +body );



    }
}
