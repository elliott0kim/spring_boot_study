package sumichan.sumichan.controller.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sumichan.sumichan.config.message.MessageComponent;
import sumichan.sumichan.dto.client.MessageCodeAndResDto;
import sumichan.sumichan.dto.client.user.UserInfoDto;
import sumichan.sumichan.dto.client.user.UserInfoModifyDto;
import sumichan.sumichan.service.client.AuthService;
import sumichan.sumichan.config.jwt.JwtUtil;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthService authService;
    @Autowired
    private MessageComponent messageComponent;
    @Autowired
    private MessageCodeAndResDto response;


    @GetMapping("/user")
    public MessageCodeAndResDto userInfo(@RequestHeader("Authorization") String Authorization)
    {
        try
        {
            String token = Authorization.substring(7);
            UserInfoDto userInfoDto = this.authService.checkExistUserById(jwtUtil.getUserId(token));
            response.setMessage(messageComponent.getSUCCESS());
            response.setData(userInfoDto);
            return response;
        }
        catch (Exception e)
        {
            if (e.getMessage() != null)
            {
                response.setMessage(e.getMessage());
            }
            else
            {
                response.setMessage(messageComponent.getINVAILD_ACCESS());
            }
            response.setData(null);
            return response;
        }
    }

    @PostMapping("/user")
    public MessageCodeAndResDto userInfoModify(@RequestHeader("Authorization") String Authorization,
                                               @RequestBody UserInfoModifyDto userInfoModifyDto)
    {
        try
        {
            String token = Authorization.substring(7);
            UserInfoDto userInfoDto = this.authService.checkExistUserById(jwtUtil.getUserId(token));
            userInfoDto.setPhoneNum(userInfoModifyDto.getPhone());
            userInfoDto.setAddress(userInfoModifyDto.getAddress());
            authService.modifyUserInfo(userInfoDto);
            response.setMessage(messageComponent.getSUCCESS());
            response.setData(null);
            return response;
        }
        catch (Exception e)
        {
            if (e.getMessage() != null)
            {
                response.setMessage(e.getMessage());
            }
            else
            {
                response.setMessage(messageComponent.getINVAILD_ACCESS());
            }
            response.setData(null);
            return response;
        }
    }
}
