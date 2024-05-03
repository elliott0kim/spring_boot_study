package sumichan.sumichan.controller.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sumichan.sumichan.dto.client.FinalOrderDto;
import sumichan.sumichan.dto.client.OrderFailDto;
import sumichan.sumichan.dto.client.PreOrderDto;
import sumichan.sumichan.service.client.TosspayService;

@RestController
@Slf4j
public class TosspayController {
    @Autowired
    private TosspayService tosspayService;

    @PostMapping("/preOrder")
    public String preOrder(@RequestBody PreOrderDto preOrder)
    {
        boolean retService = tosspayService.preOrderService(preOrder);
        if (retService)
        {
            return "{\"preOrderResult\" : \"success\"}";
        }
        else
        {
            return "{\"preOrderResult\" : \"fail\"}";
        }
    }

    @PatchMapping("/finalOrder")
    public String finalOrder(@RequestBody FinalOrderDto finalOrderDto) throws Exception {
        boolean retService = tosspayService.finalOrderService(finalOrderDto);
        if (retService)
        {
            return "{\"finalOrder\" : \"success\"}";
        }
        else
        {
            return "{\"finalOrder\" : \"fail\"}";
        }
    }

    @DeleteMapping("orderFail")
    public String orderFail(@RequestBody OrderFailDto orderFailDto)
    {
        boolean retService = tosspayService.orderFailService(orderFailDto);
        if (retService)
        {
            return "{\"orderFail\" : \"success\"}";
        }
        else
        {
            return "{\"orderFail\" : \"fail\"}";
        }
    }
}