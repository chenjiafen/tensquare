package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tyler.chen
 * @date 2020/4/25 15:50
 */
@Component
@ConfigurationProperties(prefix = "jwt.config")
public class JwtUtil {

   //超时时间30分钟
//    @Value ( "${jwt.config.ttl}" )
   private long ttl;

   //秘钥
   private String key;

   public long getTtl() {
      return ttl;
   }

   public void setTtl(long ttl) {
      this.ttl = ttl;
   }

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }

   /**
    * 生成token
    *
    * @param id    登录id
    * @param name  登录用户名称
    * @param roles 登录用户角色
    * @return
    */
   public String generateToken(String id, String name, String roles) {
      //将自定义的放入map
      Map<String, Object> map = new HashMap<> ();
      map.put ( "anc", "bcd" );
      map.put ( "heheh", "heihei" );

      //获取当前系统时间毫秒值
      long currentTime = System.currentTimeMillis ();

      //失效时间30分钟
      long expirationTome = currentTime + 1000 * 60 * ttl;

      //builder  jwtToken的构建
      String toke = Jwts
              .builder ()             //jwtToken的构建
              .setId ( id )          //设置载荷部分的键值队
              .setSubject ( name )      //设置载荷部分的键值队
              .claim ( "role", roles ) //设置自定义载荷
              .setIssuedAt ( new Date ( currentTime ) )  //设置token创建时间
              .setExpiration ( new Date ( expirationTome ) ) //设置30s后失效
              .signWith ( SignatureAlgorithm.HS256, key ) //设置秘钥的签名方式以及秘钥的值
              .compact ();//返回token

      return toke;
   }

   //解析token

   public Claims parserToken(String token) {


      try {
         Claims body = Jwts.parser () //获取token解析器
                 .setSigningKey ( key )  //指定解析秘钥
                 .parseClaimsJws ( token )   //解析token的载荷部分
                 .getBody ();//获得载荷部分
         return body;
      } catch (Exception e) {
         e.printStackTrace ();
         return null;
      }
   }
}
