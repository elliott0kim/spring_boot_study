package sumichan.sumichan.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import sumichan.sumichan.service.client.SumichanUserDetailsService;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter { // OncePerRequestFilter -> 한 번 실행 보장

    private final SumichanUserDetailsService sumichanUserDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    /**
     * JWT 토큰 검증 필터 수행
     */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String authorizationHeader = request.getHeader("Authorization");

        //JWT가 헤더에 있는 경우
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
        {
            String token = authorizationHeader.substring(7);
            //JWT 유효성 검증
            if (jwtUtil.validateToken(token))
            {
                String userId = jwtUtil.getUserId(token);

                //유저와 토큰 일치 시 userDetails 생성
                boolean findUser = sumichanUserDetailsService.loadUserById(userId);

                if (findUser == true)
                {
                    //UserDetsils, Password, Role -> 접근권한 인증 Token 생성
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//                    //현재 Request의 Security Context에 접근권한 설정
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
                else
                {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "회원정보 미존재");
                }
            }
            else
            {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
            }
        }
        else
        {
            // JWT 헤더가 없는 경우 예외처리. 내가 Message.json 에다가 넣어주는게 좋을 듯?
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization Header가 필요합니다.");
        }

        filterChain.doFilter(request, response); // 다음 필터로 넘기기
    }
}