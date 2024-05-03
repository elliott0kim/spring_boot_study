package sumichan.sumichan.mapper.client;

import org.apache.ibatis.annotations.Mapper;
import sumichan.sumichan.dto.client.BentoResDto;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BentoMapper {
    List<BentoResDto> findBentoList();
    BentoResDto findBentoDetail(HashMap<String, Object> paramMap);
}
