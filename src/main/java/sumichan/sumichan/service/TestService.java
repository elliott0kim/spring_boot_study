package sumichan.sumichan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sumichan.sumichan.dto.*;
import sumichan.sumichan.mapper.TestMapper;

import java.util.HashMap;

@Service
@Slf4j
public class TestService {
    @Autowired
    private TestMapper testMapper;

    public boolean registerItem(ItemDto itemDto) {
        // TODO: DB insert

        HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("id", itemDto.getId());
        paramMap.put("name", itemDto.getName());

        testMapper.registerItem(paramMap);

        log.info("service ...");

//        ItemEntity itemEntity = new ItemEntity();
//        itemEntity.setId(itemDto.getId());
//        itemEntity.setName(itemDto.getName());
//
//        itemRepository.save(itemEntity);

        return true;
    }

    public ItemDto getItemById(String id) {

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        HashMap<String, Object> res = testMapper.findById(paramMap);

        ItemDto itemDto = new ItemDto();
        itemDto.setId((String)res.get("ID"));
        itemDto.setName((String)res.get("NAME"));

        return itemDto;

    }
}
