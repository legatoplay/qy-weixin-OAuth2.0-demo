>[官方API文档](https://open.work.weixin.qq.com/api/old/doc#10028/%E5%85%B3%E4%BA%8E%E7%BD%91%E9%A1%B5%E6%8E%88%E6%9D%83%E7%9A%84%E5%8F%AF%E4%BF%A1%E5%9F%9F%E5%90%8D)
# 菜单配置
如果企业需要在打开的网页里面携带用户的身份信息，第一步需要构造如下的链接来获取code参数
```text
https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww66f2d5b59e8e9240&redirect_uri=http%3a%2f%2fxmbmaa.natappfree.cc&response_type=code&scope=snsapi_base&agentid=1000002#wechat_redirect
```
通过拦截器或者过滤器获取的参数`code`
# 获取access_token 
[官方介绍](https://open.work.weixin.qq.com/api/old/doc#10013/%E7%AC%AC%E4%B8%89%E6%AD%A5%EF%BC%9A%E8%8E%B7%E5%8F%96access_token)
```text
 https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRECT
```
# 获取userID
```text
https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=COD
```
