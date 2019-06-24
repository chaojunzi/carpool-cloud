# carpool-cloud
spring-boot  mybatis-plus  

### 开源项目依赖（文档）：  
[hutool-超级工具类](https://www.hutool.cn/docs/)  
[mybatis-plus-语法](https://baomidou.gitee.io/mybatis-plus-doc/#/quick-start)  
[layui](https://github.com/sentsin/layui/)  

### 项目结构
>com.carpool.cloud.server.  
>>>>>>>>>auth : 角色权限（注解/接口）  
>>>>>>>>>code : 代码自动生成  
>>>>>>>>>config : 配置  
>>>>>>>>>controller：控制层  
>>>>>>>>>service：业务层    
>>>>>>>>>dal : 持久层，（自动生成）  
>>>>>>>>>domain ： vo/dto  接受参数/返回值  
>>>>>>>>>CarpoolServerApplication 项目启动类  

### 静态文件配置
>resources
>>>>>>>>>config : 配置（h2 数据配置）  
>>>>>>>>>public： 静态文件  
>>>>>>>>>sql ： sql脚本  
>>>>>>>>>static： 静态文件（优先级高于public）  
>>>>>>>>>templates ： html 模板  
>>>>>>>>>bootstrap.yaml  项目初始配置  

### 数据库建表规范：  
#### 1. cp_模块名_子模块名（cp_user）
#### 2. 表名字段名->全部小写+下划线（user_name, user_type）
#### 3. 强制字段（id/version/deleted/create_time）  主键自增id/乐观锁/逻辑删除/创建时间

### 返回值格式规范(json)
| body | retMsg | retCode  |   
| - | :-: | :-: |  
|  object | 操作成功/失败 | 0000:成功 0500：失败  |  
 




