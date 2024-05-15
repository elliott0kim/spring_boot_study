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

import java.util.HashMap;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SumichanUserDetailsService {

    private UserInfoMapper userInfoMapper;

    public boolean loadUserById(String id)
    {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("UserID", id);
        UserInfoDto userInfoDto = userInfoMapper.findUserInfoById(paramMap);
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