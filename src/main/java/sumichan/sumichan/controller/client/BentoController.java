package sumichan.sumichan.controller.client;

import com.fasterxml.jackson.databind.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sumichan.sumichan.config.MessageComponent;
import sumichan.sumichan.dto.client.BentoResDto;
import sumichan.sumichan.dto.client.MessageCodeAndResDto;
import sumichan.sumichan.service.client.BentoService;
import java.util.HashMap;
import java.util.List;
@RestController
@Slf4j
public class BentoController {
    @Autowired
    private BentoService bentoService;
    @Autowired
    private MessageComponent messageComponent;
    @Autowired
    private MessageCodeAndResDto response;

    @GetMapping("/bento")
    public MessageCodeAndResDto bentoList()
    {
        try {
            response.setMessage(messageComponent.getSUCCESS());
            response.setData(bentoService.findBentoList());
            return response;
        } catch (Exception e) {
            response.setMessage(messageComponent.getFAIL());
            response.setData(null);
            return response;
        }
    }


    @GetMapping("/bento/{bentoId}")
    public MessageCodeAndResDto bentoDetail(@PathVariable("bentoId") String bentoId)
    {
        try {
            response.setMessage(messageComponent.getSUCCESS());
            response.setData(bentoService.findBentoDetail(bentoId));
            return response;
        } catch (Exception e) {
            response.setMessage(messageComponent.getFAIL());
            response.setData(null);
            return response;
        }
    }



}
