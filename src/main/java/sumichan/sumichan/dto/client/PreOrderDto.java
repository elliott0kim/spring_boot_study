package sumichan.sumichan.dto.client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//public class PreOrderDto {
//    String orderId;
//    int amount;
//}

public class PreOrderDto {
    private int amount;
    private String orderId;
    private String orderName;
    private String selectedOption;
    private String subOption;
    private int qty;
    private String phone;
    private String address;
    private String deliveryDateTime;
}