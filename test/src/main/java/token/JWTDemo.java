package token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.jsoup.Connection;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @author:awei
 * @date:2019/7/29
 * @ver:1.0
 **/
public class JWTDemo {
    public static void main(String[] args) {
        String builder = JWT.create().withAudience("wwq","32").withClaim("sd",12).sign(Algorithm.HMAC256("msda"));
        System.out.println(builder);
        Claim sd = JWT.decode(builder).getClaims().get("sd");
        Claim decode = sd;
        System.out.println(decode.asInt());
        System.out.println(JWT.decode(builder).getSignature());

        DecodedJWT msda = JWT.require(Algorithm.HMAC256("msda")).build().verify(builder);
        System.out.println(msda.getClaims().get("sd").asInt());
        System.out.println(msda.getSignature());
        System.out.println(msda);
        System.out.println("---------------------");
        Date expiresAt = new Date(System.currentTimeMillis() + 24L * 60L * 3600L * 1000L);
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("isVip", false)
                .withClaim("username", "lina")
                .withClaim("name", "aimi")
                .withExpiresAt(expiresAt)
                // 使用了HMAC256加密算法。
                // mysecret是用来加密数字签名的密钥。
                .sign(Algorithm.HMAC256("mysecret"));
        System.out.println(token);
        System.out.println(JWT.decode(token).getClaims().get("isVip").asBoolean());
        System.out.println(JWT.decode(token).getExpiresAt().getTime());

        System.out.println("-----------");
        Object hello = "hello";
        System.out.println(hello instanceof Object);
        System.out.println("----------------------");
        int x = -4566;
        char[] chars=null;
        String news = "";
        int in =0;
        String s = String.valueOf(Math.abs(x));
        chars = s.toCharArray();

        for(int i =chars.length-1 ;i>=0;i--){
            news +=chars[i];
        }

        try{
            in = Integer.parseInt(news);
            System.out.println(in);
            if(x<0){
                in -= 2*in;
                System.out.println(in);
            }
        }catch(Exception e){
            System.out.println(0);
        }
        
    }
}
