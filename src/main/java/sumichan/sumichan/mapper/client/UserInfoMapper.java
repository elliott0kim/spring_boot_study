package sumichan.sumichan.mapper.client;

import org.apache.ibatis.annotations.Mapper;
import sumichan.sumichan.dto.client.user.UserInfoDto;

import java.util.HashMap;
@Mapper
public interface UserInfoMapper {
    UserInfoDto findUserInfoByPhone(HashMap<String, Object> paramMap);
    UserInfoDto findUserInfoById(HashMap<String, Object> paramMap);
    void registerUserInfo(HashMap<String, Object> paramMap);
    void modifyUserInfo(HashMap<String, Object> paramMap);
}
