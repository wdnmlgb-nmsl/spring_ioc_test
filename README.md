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
## 三种工厂方式
+ 静态工厂：如果类别的构造方法为静态，可以使用静态工厂；用工厂方式实例化Bean可以实现在Bean创建之前做一些业务逻辑
+ 动态工厂：针对没有静态构造方法的类，实例工厂适用于根据对象状态或其他条件来决定创建什么样的实例
+ 通过实现FactoryBean接口规范延迟实例化Bean，单例，创建容器时就实例化了BeanFactory并存储在了FactoryBeanObjectCache中，
  当需要获取Bean时再通过工厂的getObject去实例对象
## Bean的实例化过程
+ 加载xml配置文件，解析获取配置中的每个<bean>的信息，封装成一个个的BeanDefinition对象;
+ 将BeanDefinition存储在一个名为beanDefinitionMap的Map<String,BeanDefinition>中;
+ ApplicationContext底层遍历BeanDefinitionMap，通过反射创建Bean实例对象;
+ 创建好的Bean实例对象，被存储到一个名为singletonObjects的Map<String,Object>中;
+ 当执行applicationContext.getBean(beanName)时，从singletonObjects去匹配Bean实例返回.
![img.png](documentimg/img_000.png)
## Spring的后处理器
Spring的后处理器是Spring对外开发的重要扩展点，允许我们介入到Bean的整个实例化流程中来，以达到动态注册BeanDefinition，动态修改BeanDefinition，以及动态修改Bean的作用。Spring主要有两种后处理器:
+ BeanFactoryPostProcessor:Bean工厂后处理器，**在BeanDefinitionMap填充完毕，Bean实例化之前执行；**
+ BeanPostProcessor:Bean后处理器，**一般在Bean实例化之后，填充到单例池singletonObjects之前执行。**
![img.png](documentimg/img_001.png)
## Spring Bean的生命周期
+ Bean的实例化阶段:Spring框架会取出BeanDefinition的信息进行判断当前Bean的范围是否是singleton的，
  是否不是延迟加载的，是否不是FactoryBean等，最终将一个普通的singleton的Bean通过反射进行实例化;
+ Bean的初始化阶段:Bean创建之后还仅仅是个“半成品”，还需要对Bean实例的属性进行填充、执行一些感知操作。
  接口方法、执行BeanPostProcessor方法、执行InitializingBean接口的初始化方法、执行自定义初始化init方法等。
  该阶段是Spring最具技术含量和复杂度的阶段，Aop增强功能，后面要学习的弹簧的注解功能等、弹簧高频面试题豆豆的循环引用问题都是在这个阶段体现的;
+ Bean的完成阶段:经过初始化阶段，Bean就成为了一个完整的SpringBean，被存储到单例池中去了，即完成了SpringBean的整个生命周期。
## Bean属性注入的几种情况
+ 注入普通属性，string、int或基本类型的集合，直接通过set方法的反射设置进去
+ 注入单向对象引用属性，从容器中getBean获取后通过set方法反射设置进去，如果容器中没有，则先创建被注入对象Bean实例（完成整个生命周期）后，再进行注入操作。
+ 注入双向对象应用属性，比较复杂，涉及循环引用（循环依赖）问题。Spring提供了**三级缓存**的解决方法。

```
public class DefaultSingletonBeanRegistry...{
  //1、最终存储单例Bean成品的容器，即实例化和初始化都完成的Bean，称为一级缓存
  Map<String,Object> singletonObjects = new ConcurrentHashMap(256);
  //2、早期Bean的单例池，缓存半成品对象，且当前对象已经被其他对象应用了，称为二级缓存
  Map<String,Object> earlySingletonObjects = new ConcurrentHashMap(16);
  //3、单例Bean的工厂池，缓存半成品对象，对象未被引用，使用时通过工厂创建Bean，称为三级缓存
  Map<String,ObjectFactory<?>> singletonFactories = new HashMap(16); 
}
```
## 三级缓存图解
![img.png](documentimg/img_002.png)
![img.png](documentimg/img_003.png)
![img.png](documentimg/img_004.png)
![img.png](documentimg/img_005.png)
## Bean生命周期一图流
![img.png](documentimg/img_006.png)
## xml整合第三方框架的两种整合方法
+ 不需要自定义名空间，不需要使用Spring的配置文件配置第三方框架本身内容，例如：MyBaits；
+ 需要引入第三方框架命名空间，需要使用Spring的配置文件配置第三方框架本身内容，例如：Dubbo。
## Spring整合MyBatis的原理剖析
整合包里提供了一个SqlSessionFactoryBean和-一个扫描Mapper的配置对象，SqlSessionFactoryBean一旦被实例化，
就开始扫描Mapper并通过动态代理产生Mapper的实现类存储到Spring容器中。相关的有如下四个类:
+ SqlSessionFactoryBean:需要进行配置，用于提供SqlSessionFactory;
+ MapperScannerConfigurer:需要进行配置，用于扫描指定mapper注册BeanDefinition;
+ MapperFactoryBean: Mapper的FactoryBean, 获得指定Mapper时调用getObject方法;
+ ClassPathMapperScanner: definition.setAutowireMode(2) 修改了自动注入状态，
  所以MapperFactoryBean中的setSqlSessionFactory会自动注入进去。
## Spring整合自定义名空间的步骤
+ 将自定义标签的约束 与物理约束文件与网络约束名称的约束以键值对形式存储到一个spring.schemas文件里
  该文件存储在类加载路径的META-INF里，Spring会自动加载到;
+ 将自定义命名空间的名称 与自定义命名空间的处理器映射关系以键值对形式存在到一个叫spring.handlers文
  件里，该文件存储在类加载路径的META-INF里，Spring会自动加载到;
+ 准备好NamespaceHandler, 如果命名空间只有一个标签，那么直接在parse方法中进行解析即可，一般解析结
  果就是注册该标签对应的BeanDefinition。如果命名空间里有多个标签,那么可以在init方法中为每个标签都注
  册一个BeanDefinitionParser,在执行NamespaceHandler的parse方法时在分流给不同的
  BeanDefinitionParser进行解析(重写doParse方法即可)。实现的功能一般是注入一个BeanDefinition或一个BeanPostProcessor。
![img.png](documentimg/img_007.png)
## Spring常用注解
### 声明bean的注解
+ @Component 组件，没有明确的角色
+ @Service 在业务逻辑层使用（service层）
+ @Repository 在数据访问层使用（dao层）
+ @Controller 在展现层使用，控制器的声明（C）
### 注入bean的注解
+ @Autowired：由Spring提供，表示根据类型自动注入，如果有多个相同自定义类型的Bean，根据名字注入
+ @Qualifier：结合Autowired一起使用，表示注入指定名称的Bean
都可以注解在set方法和属性上，推荐注解在属性上（一目了然，少写代码）。
### java配置类相关注解
+ @Configuration 声明当前类为配置类，相当于xml形式的Spring配置（类上）
+ @Bean 注解在方法上，声明当前方法的返回值为一个bean，替代xml中的方式（方法上）
+ @ComponentScan 用于对Component进行扫描，相当于xml中的（类上）
### 切面（AOP）相关注解
+ @Aspect 声明一个切面（类上） 使用@After、@Before、@Around定义建言（advice），可直接将拦截规则（切点）作为参数。
+ @After 在方法执行之后执行（方法上） @Before 在方法执行之前执行（方法上） @Around 在方法执行之前与之后执行（方法上）
+ @PointCut 声明切点 在java配置类中使用@EnableAspectJAutoProxy注解开启Spring对AspectJ代理的支持（类上）
### @Bean的属性支持
+ @Scope 设置Spring容器如何新建Bean实例（方法上，得有@Bean） 其设置类型包括：
Singleton （单例,一个Spring容器中只有一个bean实例，默认模式）, 
Protetype （每次调用新建一个bean）, Request （web项目中，给每个http request新建一个bean）, 
Session （web项目中，给每个http session新建一个bean）,
GlobalSession（给每一个 global http session新建一个Bean实例）
+ @Lazy 在getBean的时候再创建Bean
+ @StepScope 在Spring Batch中还有涉及
+ @PostConstruct 由JSR-250提供，在构造函数执行完之后执行，等价于xml配置文件中bean的initMethod
+ @PreDestory 由JSR-250提供，在Bean销毁之前执行，等价于xml配置文件中bean的destroyMethod
## Spring注解的解析原理
![img.png](documentimg/img_008.png)
## Spring注解方式整合第三方库
Spring与MyBatis注解方式整合有个重要的技术就是@Import，
第三方框架与Spring整合XML方式很多是凭借自定义标签完成的，
而第三方框架与Spring整合注解方式很多事靠@Import注解完成的。
@Import可以导入如下三种类：
+ 普通的配置类
+ 实现ImportSelector接口的类，**适用于需要根据某些条件，批量导入特定类作为 Bean 的场景。例如，基于配置或环境变量，选择性地导入一些组件。**
+ 实现ImportBeanDefinitionRegister接口的方法，**适用于需要对 Bean 的注册过程进行精细控制的场景。例如，MyBatis 框架使用 ImportBeanDefinitionRegistrar 来扫描并注册 Mapper 接口的代理类。**
## AOP思想的实现方案
动态代理技术，在运行期间，对目标对象的方法进行增强，代理对象同名方法内可以执行原有逻辑的同时嵌入执行其他增强逻辑或其他对象的方法
![img.png](documentimg/img_009.png)