# apiword

swagger生成word

### 项目介绍：

采用spring boot + freemarker ,下载即可用

### 使用说明：

- 1.创建sqlite数据库文件，配置在项目resource/application.properties中
- 2.初始化表，获取项目resource/sql下的建表sql，在sqlite数据库中创建对应的表
- 3.启动项目  run application.java
- 4.调用创建API接口,创建API成功后返回API定义的资产ID
![Image text](https://github.com/apibarrel/apiword/blob/master/createAPI.PNG)
- 5.带资产ID访问下载接口，获取当前API生成的word文档
http://localhost:8079/apis/{id}/download/word

### 项目规划
- [x] 支持swagger definitions
- [x] 支持swagger header 参数
- [x] 支持swagger query 参数
- [x] 支持swagger path 参数
- [x] 支持swagger 请求体
- [x] 支持swagger 响应头
- [x] 支持swagger 响应体
- [x] 支持swagger 生成文档带章节
- [ ] 支持soap wsdl和xsd 生成word
- [x] 支持上传接口定义文件接口
- [x] 支持下载接口word接口
- [ ] 提供UI操作界面
