Game-SDK 1.0.0
=================================

GameSDK只要是提供支付，登录，注册，支付验证，和语音支持，多渠道打包，推送等功能

[![Total Downloads](https://poser.pugx.org/topthink/think/downloads)](https://github.com/znlccy/game.git)
[![Latest Stable Version](https://poser.pugx.org/topthink/think/v/stable)](https://poser.pugx.org/topthink/think/v/stable)
[![Latest UnStable Version](https://poser.pugx.org/topthink/think/v/unstable)](https://packagist.org/packages/topthink/think)
[![Liciense](https://poser.pugx.org/topthink/think/license)](https://packagist.org/packages/topthink/think)

+ 基本的支付宝支付以及验证支付结果
+ 基本的微信支付并且提供验证支付结果接口
+ 用户登录行为
+ 用户注册行为
+ 用户忘记密码行为
+ 发送短信验证码给用户手机
+ 提供游戏语音支持
+ 提供数据统计功能

> GameSDK要求JDK1.7或者JDK1.8

 初始化目录结构
 
~~~
webapps web部署目录(或者子目录)
├ ─game
│  ├─.idea Idea自动生成的目录
│  │  ├─artifacts
│  │  │  ├─game_war.xml
│  │  │  ├─game_war_exploded.xml
│  │  ├─inspectionProfiles
│  ├─.mvn Meaven自动生成的目录
│  ├─libs 放置第三方jar包
│  ├─src 放置源码位置
│  ├─game.iml idea自动生成的目录
│  ├─mvnw maven自动生成的目录
│  ├─mvnw.cmd maven的命令行
│  ├─pom.xml maven第三方添加依赖包的位置
│  ├─README.md 说明文档
~~~

> GameSDK 在命令行里直接输入mvn spring-boot:run（前提配置好环境）
> GameSDK 也可以直接运行
> 上述目录是可以改变的，这取决你的项目

## 命名规范
