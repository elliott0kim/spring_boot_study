package sumichan.sumichan.controller.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sumichan.sumichan.dto.client.BentoResDto;
import sumichan.sumichan.service.client.BentoService;

import java.util.HashMap;
import java.util.List;
@RestController
@Slf4j
public class BentoController {
    @Autowired
    private BentoService bentoService;
    @GetMapping("/bento")
    public List<BentoResDto> bentoList()
    {
        return bentoService.findBentoList();
    }

    @GetMapping("/bento/{bentoId}")
    public BentoResDto bentoDetail(@PathVariable("bentoId") String bentoId)
    {
        return bentoService.findBentoDetail(bentoId);
    }


}
