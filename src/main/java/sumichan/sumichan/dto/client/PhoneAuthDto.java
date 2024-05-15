package sumichan.sumichan.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
//@Schema(title = "AUTH_REQ_01 : 로그인 요청 DTO")
public class PhoneAuthDto {
    private String phone;
    private String code;
}