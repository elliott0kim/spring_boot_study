package sumichan.sumichan.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sumichan.sumichan.config.JwtUtil;
import sumichan.sumichan.dto.client.LoginReqDto;
import sumichan.sumichan.dto.client.UserInfoDto;
import sumichan.sumichan.mapper.client.UserInfoMapper;
import sumichan.sumichan.mapper.client.UserInfoMapper;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class AuthService {

    private final JwtUtil jwtUtil;
    //private final PasswordEncoder encoder;

    // 얘 매퍼로 엮어서 db조회로 데려가야함. 그래서 내가 회원관리해줄 Dto던 뭐던 따로 만들어야 할 듯?
    private UserInfoMapper userInfoMapper;

    // model mapper를 안쓸꺼면 필요가 굳이 있나 싶네.... 이거 지금 타입 변경해주면서 매핑할 때 필요한건데 이게 굳이 지금..?
    //private final ModelMapper modelMapper;
    //@Override
    @Transactional
    public String login(LoginReqDto loginReqDto) {
        String phone = loginReqDto.getPhone();
        String code = loginReqDto.getCode();

        // 여기서 인증번호 code 확인하는 절차 있어야함........ 아직 코드를 못짜서..............

        // 얘 매퍼로 엮어서 db조회로 데려가야함. 그래서 내가 회원관리해줄 Dto던 뭐던 따로 만들어야 할 듯?
        UserInfoDto userInfoDto = userInfoMapper.findUserInfoByPhone(phone);
        if(userInfoDto == null) {
            throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");
        }

        String accessToken = jwtUtil.createAccessToken(userInfoDto);
        return accessToken;
    }
}