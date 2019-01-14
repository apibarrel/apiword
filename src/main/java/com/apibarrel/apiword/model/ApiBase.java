package com.apibarrel.apiword.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ApiBase {
    private String name;

    private String version;

    private String namespace;

    private String protocol;

    private String id;

    private String createTime;

    private String creator;

    private String tag;
}
