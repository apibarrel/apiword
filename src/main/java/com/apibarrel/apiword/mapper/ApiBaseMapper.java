package com.apibarrel.apiword.mapper;

import com.apibarrel.apiword.model.ApiBase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ApiBaseMapper {
    // 插入 并查询id 赋给传入的对象
    int insert(ApiBase apiBase);

    // 根据 ID 查询
    ApiBase query(String id);

    // 更新 value
    int updateValue(ApiBase apiBase);

    // 根据 ID 删除
    int delete(String id);

}
