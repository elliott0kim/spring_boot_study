package sumichan.sumichan.dto.client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FinalOrderDto {
    private String paymentKey;
    private int amount;
    private String orderId;
    private String orderName;
    private int qty;
    private String phone;
    private String address;
    private String deliveryDateTime;
}
