package sumichan.sumichan.controller.client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sumichan.sumichan.dto.client.LoginReqDto;
import sumichan.sumichan.service.client.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApiController {

    private final AuthService authService;


    // 여기 return type 지정해줄것.
    @PostMapping("/login")
    public ResponseEntity<String> getMemberProfile(@Valid @RequestBody LoginReqDto loginReqDto)
    {
        String token = this.authService.login(loginReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}