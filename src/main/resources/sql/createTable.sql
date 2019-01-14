CREATE TABLE IF NOT EXISTS T_API_BASE (
                               id         STRING (64)    PRIMARY KEY
                                                         UNIQUE
                                                         NOT NULL,
                               name       STRING (0, 64),
                               version    STRING (0, 64),
                               namespace  STRING (0, 64),
                               protocol   STRING (1, 10),
                               createTime STRING,
                               creator    STRING,
                               tag        STRING
                           );


CREATE TABLE IF NOT EXISTS T_API_ASSET (
                               id      STRING PRIMARY KEY
                                              UNIQUE,
                               apiId   STRING,
                               type    STRING,
                               content TEXT,
                               name    STRING
                           );

