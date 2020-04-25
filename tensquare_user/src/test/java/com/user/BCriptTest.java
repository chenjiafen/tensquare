package com.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author tyler.chen
 * @date 2020/4/23 22:25
 */
public class BCriptTest {

//    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder (  );


    @Test
    public void fun1() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder ();
        String s = encoder.encode ( "3213213" );
        System.out.println ( s );
    }

    @Test
    public void fun2() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder ();
        Boolean flag = encoder.matches ( "123456", "$2a$10$OfV38bJsPx09DmbBqzX4qemBkB28pFkvL4YOr8M9X0BSAoj89S48a" );
        System.out.println ( "flag：" + flag );
    }
}
