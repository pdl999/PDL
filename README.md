## 开发环境:
* mysql5.5/mysql5.6
* jdk1.8
* springboot+mybatis-plus+Lombok+maven
* 注：application.properties文件中修改属性的spring.datasource.password=为数据库密码
******************
二：访问入口以及推荐测试账户

    租户以及房东登录入口：http://localhost:8080/
    租客登录账户：实习人员
    租客登录密码：aaa
******************    
    房东登录账户：大房东
    房东登陆密码：123
******************
    管理员入口：http://localhost:8080/admin/login
    管理员登录账户：kali
    管理员登录密码：abc

******************
三：简要功能展示
1：租户界面
	主要实现了房源展示，用户意见反馈，租户对房源下单，和登录注册功能，其中房源展示分为四个小部分：热推户型、最新上架，专属推荐和所有房源。
	
	（1）热推户型主要是筛选出最新上架的前三名并且满足通过管理员审核和状态为“招租”的房源信息并展示在页面头部
	（2）最新上架是将所有通过管理员审核并且没有被出租的房源按照上传时间降序进行展示
	（3）专属推荐是通过用户注册时候自行选中的标签，和房屋的标签进行对比查询出来实现的简单推荐功能，这里的房源同样满足通过管理员审核并且没有被出租的条件，这里需要用户登录之后才能查看，否则没有推荐数据
	（4）所有房源则是将通过管理员审核且没有被出租的房屋全部分页展示出来
	（5）租户可以点击房源查看目标房源的详细信息，并可以在详情页面进行下单，如果在未登录情况下将会提示登录重新操作
	（6）“联系我们”板块作为用户的反馈渠道，只有在用户登录之后并且在文本框内输入有效的文本信息时才能提交
	（7）登陆注册界面主要提供租客和房东的登录和注册功能，你可以通过现中顶部的按钮实现不同的操作逻辑
 
 


2：管理员界面
	主要实现了当前登录房东注销登录、已经填写的房屋信息的管理、发布房屋以及一些租客相关信息的查看等功能
	
	（1）登陆进来将会展示当前房东名下所有的房源信息（无论是否通过管理员审核，是否已经出租），房东可以点击查看房源当前的一些详细信息，并可以通过点击“下架”来选择房屋是否继续出租，只有不处于被租的房屋可以进行修改和下架操作
    （2）发布房屋功能，需要填写房屋信息，点击提交之后将会跳转到房屋管理界面重新展示自己所有的房源信息，新提交的房屋需要通过管理员审核之后才会展示在租户界面上
    （3）租户管理和租户反馈主要是提供对于一些租户信息的查看，功能并未完善，只可以简单的查看信息
3：管理员界面
	这个模块主要实现了网站管理员的登录、退出登录、房屋审核，以及用户反馈信息的查看等功能

     (1)登录界面，通过拦截器过滤掉未经登录的访问请求，同时会有相关提示信息
     (2)登录成功之后的大厅页面是一些提示信息，点击房源审核，可以查看所有状态为“等待审核”的房源，点击“通过”按钮，就会将目标房源信息展示在租户界面
     (3)点击用户反馈，将会展示所有没有处理过的租户反馈意见，这里用一个按钮模拟处理操作，点击之后隐藏当前反馈信息
 ******************
 
## 运行截图:
![Image text](http://pic.stadc.cn/10.jpg)
![Image text](http://pic.stadc.cn/9.jpg)
![Image text](http://pic.stadc.cn/1.jpg)
![Image text](http://pic.stadc.cn/2.jpg)
![Image text](http://pic.stadc.cn/3.jpg)
![Image text](http://pic.stadc.cn/4.jpg)
![Image text](http://pic.stadc.cn/5.jpg)
![Image text](http://pic.stadc.cn/8.jpg)


