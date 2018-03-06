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
  GameSDK 遵循Restful Api风格

### 目录规范
*   目录不强制规范，驼峰和小写+下划线模式均支持；
*   类库、函数文件统一以`.php`为后缀；
*   类的文件名均以命名空间定义，并且命名空间的路径和类库文件所在路径一致；
*   类名和类文件名保持一致，统一采用驼峰法命名（首字母大写）

### 函数和类、属性命名
*   类的命名采用驼峰法，并且首字母大写，例如 `User`、`UserType`，默认不需要添加后缀，例如`UserController
*   函数的命名使用小写字母和下划线（小写字母开头）的方式，例如 `get_client_ip`；
*   方法的命名使用驼峰法，并且首字母小写，例如 `getUserName`；
*   属性的命名使用驼峰法，并且首字母小写，例如 `tableName`、`instance`；

### 常量和配置
*   数据表和字段采用小写加下划线方式命名，并注意字段名不要以下划线开头，例如 `think_user` 表和 `user_name`字段，不建议使用驼峰和中文作为数据表字段命名。

## 参与开发
请参阅[GameSDK 开发参考](https://github.com/znlccy/game)

## 版权信息
科技有限公司版权所有
