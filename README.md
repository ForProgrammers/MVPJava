# MvpJava

项目结构：project 分为app 部分和model部分（clean 框架会分为三个部分 app、usercase、model）依赖关系为app -> model
其中model 为java lib库 好处为 调试接口数据时 可以直接使用单元测试。而不用运行到真机 方便开发。
APP 分包使用了分模块模式：相同的模块放统一个包下，里面有个Contract约束来管理 V和P

```
|-- APP
    |-- module
        |-- VP        
    |-- util
    |-- view
    
|-- Model
    |-- entity
    |-- http
    |-- repository
    |-- util
    |-- test 
```

