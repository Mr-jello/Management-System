package top.mrjello;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mrjello.utils.JwtUtils;


import java.nio.charset.StandardCharsets;
import java.util.UUID;

@SpringBootTest
class ManagementSystemApplicationTests {

    /**
     * 测试UUID
     */
    @Test
    public void testUuid() {
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }


    /**
     * 测试生成jwt
     */
    @Test
    public void testGenJwt() {
        String token = JwtUtils.generateJwtToken(1, "jason", "杰森");
        System.out.println(token);
    }


    @Test
    public void testParseJwt() {
        String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHOsdadasdasfdssfeweee";
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJqYXNvbiIsImV4cCI6MTY4ODUxNjUyMX0.0cm7ZM0qrL9bnqQizV7wFCunh7_XEsXxzRVj42cpmzM");
        System.out.println(claimsJws);
    }

    @Test
    public void testParse(){
        String token = JwtUtils.generateJwtToken(23, "jason", "杰森");
        Claims claims = JwtUtils.parseJwtToken(token);
        System.out.println(claims.get("username"));
    }

    @Autowired
    private SAXReader saxReader;
    @Test
    public void testThirdBean() throws DocumentException {
        Document document = saxReader.read(this.getClass().getClassLoader().getResourceAsStream("book.xml"));
        Element rootElement = document.getRootElement();
        String name = rootElement.element("name").getText();
        String age = rootElement.element("age").getText();
        System.out.println(name + " " + age);
    }
}
