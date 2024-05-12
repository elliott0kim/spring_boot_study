package sumichan.sumichan.mapper.client;

import sumichan.sumichan.dto.client.UserInfoDto;

public interface UserInfoMapper {
    UserInfoDto findUserInfoByPhone(String PhoneNum);
    UserInfoDto findUserInfoById(String UserId);
}
