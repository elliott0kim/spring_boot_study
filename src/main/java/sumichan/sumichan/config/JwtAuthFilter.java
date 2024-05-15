package sumichan.sumichan.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sumichan.sumichan.dto.client.MessageCodeAndResDto;
import sumichan.sumichan.service.client.AuthService;
import sumichan.sumichan.service.client.SumichanUserDetailsService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter { // OncePerRequestFilter -> 한 번 실행 보장

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @Override
    /**
     * JWT 토큰 검증 필터 수행
     */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String authorizationHeader = request.getHeader("Authorization");

        response.setContentType("applcation/json");
        response.setCharacterEncoding("UTF-8");
        MessageCodeAndResDto messageCodeAndResDto = new MessageCodeAndResDto();
        ObjectMapper mapper = new ObjectMapper();
        String jsonRes;

        //JWT가 헤더에 있는 경우
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
        {
            String token = authorizationHeader.substring(7);
            //JWT 유효성 검증
            try
            {
                jwtUtil.validateToken(token);
            }
            catch (Exception e)
            {
                messageCodeAndResDto.setMessage(e.getMessage());
                messageCodeAndResDto.setData(null);
                jsonRes = mapper.writeValueAsString(messageCodeAndResDto);
                response.setStatus(419);
                response.getWriter().write(jsonRes);
                return;
            }


            String userId = jwtUtil.getUserId(token);
            try
            {
                authService.checkExistUserById(userId);
            }
            catch (Exception e)
            {
                messageCodeAndResDto.setMessage(e.getMessage());
                messageCodeAndResDto.setData(null);
                jsonRes = mapper.writeValueAsString(messageCodeAndResDto);
                response.setStatus(400);
                response.getWriter().write(jsonRes);
                return;
            }
        }
        else
        {
            String a = request.getRequestURI();

            if (!request.getRequestURI().matches("^/auth/.*")
                    && !request.getRequestURI().matches("^/swagger.*")
                    && !request.getRequestURI().matches("/v3.*")
                    && !request.getRequestURI().matches("^/api.*"))
            {
                return;
            }
        }

        filterChain.doFilter(request, response); // 다음 필터로 넘기기
    }
}