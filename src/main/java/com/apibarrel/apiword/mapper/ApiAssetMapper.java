package com.apibarrel.apiword.mapper;

import com.apibarrel.apiword.model.ApiAsset;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ApiAssetMapper {
    // 插入 并查询id 赋给传入的对象
    int insert(ApiAsset apiAsset);

    // 根据 ID 查询
    ApiAsset query(String id);

    List<ApiAsset> queryByApiId(String id);

    // 更新 value
    int updateValue(ApiAsset apiAsset);

    // 根据 ID 删除
    int delete(String id);
}
