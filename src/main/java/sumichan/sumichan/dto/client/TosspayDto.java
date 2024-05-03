package sumichan.sumichan.dto.client;


import lombok.NonNull;

public class TosspayDto {
    @NonNull
    private String payType;

    @NonNull
    private Long amount;

    @NonNull
    private String orderName;

    private String successUrl;

    private String failUrl;

}
