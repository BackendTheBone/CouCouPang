package p.khj745700.coucoupang.application.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * 쿠키 기반 인증 방식
     * <ul>
     *     <li>
     *          클라이언트가 인증(Authentication, 로그인)에 성공하면, 서버가 HTTP 응답 MSG의 헤더에 Set-Cookie: JSESSIONID=값 을 담아서 보냄.
     *     </li>
     *     <li>
     *          클라이언트는 서버가 보낸 JSESSIONID 값을 쿠키에 저장함.
     *     </li>
     *     <li>
     *         클라이언트가 HTTP 요청 MSG를 보낼 때, 헤더 부분에 Cookie: JESSIONID=값 을 함께 보냄.
     *     </li>
     *     <li>
     *         그리고 서버가 해당 JSESSIONID 값을 확인하여, 인증된 사용자인지 확인함.
     *     </li>
     *     <li>
     *         위와 같은 방식을 APIKEY 방식이라고 함.
     *         <a href="https://swagger.io/docs/specification/authentication/api-keys/">링크 참조</a>
     *     </li>
     *     <li><code>.type(SecurityScheme.Type.APIKEY</code>우리는 현재 쿠키 기반 인가 방식을 사용하므로, APIKEY 타입으로 지정해야 함.</li>
     *     <li><code>.in(SecurityScheme.In.COOKIE</code> APIKEY 값이 쿠키에 담겨있으므로, COOKIE로 지정함.</li>
     *     <li><code>.name("JESSIONID")</code> 인가에 사용되는 키가 JSESSIONID 이므로, name을 JESSIONID로 지정함.</li>
     * </ul>
     */
    @Bean
    public OpenAPI openAPI() {
        SecurityScheme auth = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.COOKIE).name("JSESSIONID");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("basicAuth");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicAuth", auth))
                .addSecurityItem(securityRequirement)
                .info(apiInfo());
    }


    private Info apiInfo() {
        return new Info()
                .title("쿠쿠팡 API")
                .description("쿠쿠팡 API 문서")
                .version("0.0.1");
    }
}
