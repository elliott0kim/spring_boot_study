package sumichan.sumichan.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface TestMapper {

    HashMap<String, Object> findById(HashMap<String, Object> paramMap);

    void registerItem(HashMap<String, Object> paramMap);
}
