package com.apibarrel.apiword.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ApiAsset {
    private String id;

    private String apiId;

    private String type;

    private String content;

    private String name;

}
