package sumichan.sumichan.dto.client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderHistoryDto {
    private String orderId;
    private String orderName;
    private int qty;
    private int totalAmount;
    private String status;
    private String method;
    private String requestedAt;
    private String approvedAt;
    private String receiptUrl;
}

