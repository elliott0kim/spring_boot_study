package sumichan.sumichan.dto.client;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoDto {
    private String UserID;
    private String Name;
    private String PhoneNum;
}
