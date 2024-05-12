package sumichan.sumichan.dto.client;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponseDto {
    public ErrorResponseDto (int httpStatus , String errorMessage, LocalDateTime dateTime)
    {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.dateTime = dateTime;
    }
    private int httpStatus;
    private String errorMessage;
    private LocalDateTime dateTime;
}


