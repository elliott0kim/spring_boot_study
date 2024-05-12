package sumichan.sumichan.service.client;


import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sumichan.sumichan.dto.client.UserInfoDto;
import sumichan.sumichan.mapper.client.UserInfoMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SumichanUserDetailsService {

    private UserInfoMapper userInfoMapper;

    public boolean loadUserById(String id)
    {
        UserInfoDto userInfoDto = userInfoMapper.findUserInfoByPhone(id);
        if (userInfoDto == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}