package sumichan.sumichan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sumichan.sumichan.dto.*;
import sumichan.sumichan.service.TestService;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/dummy")
    public String dummy() {
        log.info("dummy");
        return "{}";
    }

    @GetMapping("/dummy2")
    public String dummy2() {
        log.info("dummy2");
        return "dummy2";
    }

    @GetMapping("/member")
    public String getMember(@RequestParam(value="empNo", defaultValue = "111") String empNo
            , @RequestParam("year") int year) {
        log.info("empNo: {}", empNo);
        log.info("year: {}", year);
        return "ok";
    }

    @GetMapping("/company/{id}")
    public String getCompany(@PathVariable("id") String id) {
        log.info("id: {}", id);
        return "ok";
    }

    @PostMapping("/item")
    public ResponseDto registerItem(@RequestBody ItemDto item) {
        log.info("item: {}", item);

        boolean b = testService.registerItem(item);
        if (b == true) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("ok");
            return responseDto;
        }

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("fail");
        return responseDto;
    }

    @GetMapping("/item")
    public ItemDto getItem(@RequestParam("id") String id) {
        ItemDto res = testService.getItemById(id);
        return res;
    }

}
