# 这是一个个人学习Spring的项目
## 基于SpringBean的配置详解
+ bean id="" class="" Bean的id和全限定名配置
+ name="" 通过name设置Bean别名
+ scope="" Bean的作用范围
+ lazy-init="" Bean的初始化时机，是否延迟加载，BeanFactory模式下失效
+ init_method="" 实例化后自动执行的初始化方法
+ destroy_method="" 实例销毁前的方法
+ autowire="" 设置自动注入模式，按类型byType，按名字byName
+ factory-bean="" 指定哪个工厂Bean的哪个方法完成Bean的创建