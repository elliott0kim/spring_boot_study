package sumichan.sumichan.dto.client.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDto {
    private String name;
    private String phone;
    private String address;
}
