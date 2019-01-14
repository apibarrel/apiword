package com.apibarrel.apiword.service;

import com.apibarrel.apiword.mapper.ApiAssetMapper;
import com.apibarrel.apiword.model.ApiAsset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiAssetService {

    @Autowired
    private ApiAssetMapper dao;

    public boolean insert(ApiAsset model) {
        return dao.insert(model) > 0;
    }

    public ApiAsset query(String id) {
        return dao.query(id);
    }

    public List<ApiAsset> queryApiAssets(String id) {
        return dao.queryByApiId(id);
    }

    public boolean updateValue(ApiAsset model) {
        return dao.updateValue(model) > 0;
    }

    public boolean delete(String id) {
        return dao.delete(id) > 0;
    }
}
