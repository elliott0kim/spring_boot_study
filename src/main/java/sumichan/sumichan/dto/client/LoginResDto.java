package sumichan.sumichan.dto.client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResDto {
    private String name;
    private String phoneNumber;
    private String accessToken;
    private String refreshToken;
}
