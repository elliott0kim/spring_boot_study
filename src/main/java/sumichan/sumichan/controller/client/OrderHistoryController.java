package sumichan.sumichan.controller.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sumichan.sumichan.dto.client.OrderHistoryDto;
import sumichan.sumichan.service.client.OrderHistoryService;

import java.util.HashMap;

@RestController
@Slf4j
public class OrderHistoryController {
//    @Autowired
//    OrderHistoryService orderHistoryService;
//    @GetMapping("/orderHistory")
//    public HashMap<String, OrderHistoryDto orderHistory()
//    {
//        return orderHistoryService;
//    }
}
