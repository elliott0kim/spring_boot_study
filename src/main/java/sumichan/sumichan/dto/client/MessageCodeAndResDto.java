package sumichan.sumichan.dto.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sumichan.sumichan.config.MessageComponent;

@Component
@Getter
@Setter
public class MessageCodeAndResDto<ResponseDto> {
    private String message;
    private ResponseDto data;
}
