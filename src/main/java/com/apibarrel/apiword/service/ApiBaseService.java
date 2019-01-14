package com.apibarrel.apiword.service;

import com.apibarrel.apiword.mapper.ApiBaseMapper;
import com.apibarrel.apiword.model.ApiBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiBaseService {

    @Autowired
    private ApiBaseMapper dao;

    public boolean insert(ApiBase apiBase) {
        return dao.insert(apiBase) > 0;
    }

    public ApiBase select(String id) {
        return dao.query(id);
    }

    public boolean updateValue(ApiBase apiBase) {
        return dao.updateValue(apiBase) > 0;
    }

    public boolean delete(String id) {
        return dao.delete(id) > 0;
    }
}
