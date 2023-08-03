package top.mrjello.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author jason@mrjello.top
 * @date 2023/7/3 17:44
 */
public class JwtUtils {
    // token时效：24小时
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    // 签名哈希的密钥，对于不同的加密算法来说含义不同
    public static final String APP_SECRET = "zxs123123zxs123123zxs123123zxs123123zxs123123zxs123123zxs123123";
    /**
     * 根据用户id和昵称生成token
     * @param id  用户id
     * @param username 用户昵称
     * @return JWT规则生成的token
     */
    public static String generateJwtToken(Integer id, String username, String name){
        String jwtToken = Jwts.builder()
                .setSubject("mrjello")
                .setIssuedAt(new Date())
                .claim("id", id)
                .claim("username", username)
                .claim("name", name)
                // 传入Key对象
                .signWith(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .compact();
        return jwtToken;
    }


    /**
     * 判断token是否存在与有效
     * @param jwtToken token字符串
     * @return 如果token有效返回true，否则返回false
     */
    public static boolean checkToken(String jwtToken) {
        if (jwtToken == null) {
            return false;
        }
        try {
            Jwts.parserBuilder()
                    .setSigningKey(APP_SECRET.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Claims parseJwtToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(APP_SECRET.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
