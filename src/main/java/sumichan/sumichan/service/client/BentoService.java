package sumichan.sumichan.service.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sumichan.sumichan.dto.client.BentoResDto;
import sumichan.sumichan.mapper.client.BentoMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class BentoService {
    @Autowired
    private BentoMapper bentoMapper;

    public List<BentoResDto> findBentoList()
    {
//        List<BentoResDto> bentoResDtoList = new ArrayList<>();
//        bentoMapper.findBentoList().forEach(item ->
//        {
//            BentoResDto bentoResDto = new BentoResDto();
//
//            bentoResDto.setBento((String) item.get("Bento"));
//            bentoResDto.setPathURL((String) item.get("PathURL"));
//            bentoResDto.setDescription((String) item.get("Description"));
//            bentoResDto.setPrice((int) item.get("Price"));
//            bentoResDtoList.add(bentoResDto);
//        });
        return bentoMapper.findBentoList();
    }

    public BentoResDto findBentoDetail(String bentoId)
    {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("bentoId", bentoId);

//        BentoResDto bentoResDto = new BentoResDto();
//        bentoResDto.setBento((String) bentoMapper.findBentoDetail(paramMap).get("Bento"));
//        bentoResDto.setPathURL((String) bentoMapper.findBentoDetail(paramMap).get("PathURL"));
//        bentoResDto.setDescription((String) bentoMapper.findBentoDetail(paramMap).get("Description"));
//        bentoResDto.setPrice((int) bentoMapper.findBentoDetail(paramMap).get("Price"));
        return bentoMapper.findBentoDetail(paramMap);
    }

}
