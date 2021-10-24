# 概述

自动化构建工具，具有以下功能

- 自动化执行编译、测试、打包、部署
- 依赖管理工具
- 项目信息管理工具，通过自动生成的站点，可获得项目文档、测试报告等项目信息



# maven目录

conf目录下包含重要文件 `setting.xml` 。推荐将该文件复制到/.m2/setting.xml，只对当前用户产生影响，避免影响其他用户





# mavan项目目录结构

```text
pom.xml #配置项目依赖 
src/main/java # 存放java文件
src/main/resource # 存放xml/properties
src/webapp/WEB-INF# 存放web.xml以及html,jsp等web资源
src/test/java # 存放java文件
src/test/resource# 存放xml/properties
# maven项目不需要lib目录，因为依赖配置全在pom文件中，打包会自动生成lib
```



# 使用入门配置

## pom.xml示例

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!--指定了当前pom的版本 只能是4.0.0-->
    <modelVersion>4.0.0</modelVersion>

    <!--子模块对父模块的继承-->
    <parent>
        <groupId>abc</groupId>
        <artifactId>abc</artifactId>
        <version>1.0</version>
        <!--父pom的相对目录-->
        <relativePath>../pom.xml</relativePath>
    </parent>
    <!--聚合多个模块进行编译，可多个子项-->
    <modules>
        <!--module所在目录相对路径 值是module目录名称-->
        <module>accout-email</module>
        <module>account-persist</module>
    </modules>


    <!--坐标信息  start-->
    <groupId>反写的公司网址</groupId>    <!--主项目标识(org.springframework)-->
    <artifactId>项目名+模块名</artifactId>    <!--spring-context-->
    <!--第一个0表示大版本号
    第二个0表示分支版本号
    第三个0标识小版本号
    0.0.1SNAPSHOT
    snapshot快照(表示还不稳定)
    alpha内测
    beta公测
    Release稳定
    GA正式发布
    -->
    <version></version>
    <!--打包方式：默认是jar
    war(web项目)  zip  pom(无实际意义，仅作为继承或聚合的标识)
  -->
    <packaging></packaging>
    <!--坐标信息  end-->

    <!--项目描述名-->
    <name>hi</name>
    <!--项目地址-->
    <url>http://maven.apache.org</url>
    <!--项目描述-->
    <description></description>
    <!--开发人员列表-->
    <developers></developers>
    <!--许可证信息-->
    <license></license>
    <!--组织信息-->
    <organization></organization>
    <!--maven自定义属性声明-->
    <properties>
        <!--统一定义版本号,便于更新-->
        <springframwork.version>2.5.6</springframework.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <!--依赖列表-->
    <dependencies>
        <!--依赖项-->
        <dependency>
            <!--项目坐标 start-->
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <type></type>
            <scope>test</scope>            <!--指依赖范围 常用test/compile/provided-->
            <!--设置依赖范围是否可选：默认false,true表示该依赖不会参与依赖传递-->
            <optional></optional>
            <!--排除依赖传递列表-->
            <executions>
                <execution>
                    <!--排除依赖不需要版本号(因为maven解析后的依赖不存在group&art相同 versiong不同依赖)-->
                    <groupId></groupId>
                    <artifactId></artifactId>
                </execution>
            </executions>
            <!--项目坐标 end-->
        </dependency>
    </dependencies>

    <!--依赖的管理-->
    <!--不会运行，即不会调用到依赖中
    一般定义在父模块中，供子模块调用
  -->
    <dependencyManagement>
        <dependencies>
            <!--定义groupId,artifactId,version,scope,子模块调用只需定义groupId,artifactId-->
            <dependency></dependency>
            <!--注意scope=import-->
        </dependencies>
    </dependencyManagement>
    <!--提供支持-->
    <build>
        <!--插件列表-->
        <plugins>
            <plugin>
                <!--坐标-->
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-source-plugin</artifactId>
                <version>2.4</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>jar-no-fork</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```





## setting.xml配置

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <!--表示构建系统本地仓库的路径-->
    <localRepository>${user.home}/.m2/repository</localRepository>
    <!--我们使用某个插件，并且没有在命令行为其提供组织Id（groupId）的时候，Maven就会使用该列表。默认情况下该列表包含了org.apache.maven.plugins和org.codehaus.mojo。-->
    <pluginGroups>
        <!--plugin的组织Id（groupId） -->
        <pluginGroup>org.codehaus.mojo</pluginGroup>
    </pluginGroups>
    <!--配置服务端的一些设置。一些设置如安全证书不应该和pom.xml一起分发。这种类型的信息应该存在于构建服务器上的settings.xml文件中。 -->
    <servers>
        <!--服务器元素包含配置服务器时需要的信息 -->
        <server>
            <!--这是server的id（注意不是用户登陆的id），该id与distributionManagement中repository元素的id相匹配。 -->
            <id>server001</id>
            <!--鉴权用户名。鉴权用户名和鉴权密码表示服务器认证所需要的登录名和密码。 -->
            <username>my_login</username>
            <!--鉴权密码 。鉴权用户名和鉴权密码表示服务器认证所需要的登录名和密码。密码加密功能已被添加到2.1.0 +。详情请访问密码加密页面 -->
            <password>my_password</password>
            <!--鉴权时使用的私钥位置。和前两个元素类似，私钥位置和私钥密码指定了一个私钥的路径（默认是${user.home}/.ssh/id_dsa）以及如果需要的话，一个密语。将来passphrase和password元素可能会被提取到外部，但目前它们必须在settings.xml文件以纯文本的形式声明。 -->
            <privateKey>${usr.home}/.ssh/id_dsa</privateKey>
            <!--鉴权时使用的私钥密码。 -->
            <passphrase>some_passphrase</passphrase>
            <!--文件被创建时的权限。如果在部署的时候会创建一个仓库文件或者目录，这时候就可以使用权限（permission）。这两个元素合法的值是一个三位数字，其对应了unix文件系统的权限，如664，或者775。 -->
            <filePermissions>664</filePermissions>
            <!--目录被创建时的权限。 -->
            <directoryPermissions>775</directoryPermissions>
        </server>
        <mirrors>
            <!-- 给定仓库的下载镜像。 -->
            <mirror>
                <!-- 该镜像的唯一标识符。id用来区分不同的mirror元素。 -->
                <id>planetmirror.com</id>
                <!-- 镜像名称 -->
                <name>PlanetMirror Australia</name>
                <!-- 该镜像的URL。构建系统会优先考虑使用该URL，而非使用默认的服务器URL。 -->
                <url>http://downloads.planetmirror.com/pub/maven2</url>
                <!-- 被镜像的服务器的id。例如，如果我们要设置了一个Maven中央仓库（http://repo.maven.apache.org/maven2/）的镜像，就需要将该元素设置成central。这必须和中央仓库的id central完全一致。 -->
                <mirrorOf>central</mirrorOf>
            </mirror>
        </mirrors>
        <proxies>
            <!--代理元素包含配置代理时需要的信息 -->
            <proxy>
                <!--代理的唯一定义符，用来区分不同的代理元素。 -->
                <id>myproxy</id>
                <!--该代理是否是激活的那个。true则激活代理。当我们声明了一组代理，而某个时候只需要激活一个代理的时候，该元素就可以派上用处。 -->
                <active>true</active>
                <!--代理的协议。 协议://主机名:端口，分隔成离散的元素以方便配置。 -->
                <protocol>http</protocol>
                <!--代理的主机名。协议://主机名:端口，分隔成离散的元素以方便配置。 -->
                <host>proxy.somewhere.com</host>
                <!--代理的端口。协议://主机名:端口，分隔成离散的元素以方便配置。 -->
                <port>8080</port>
                <!--代理的用户名，用户名和密码表示代理服务器认证的登录名和密码。 -->
                <username>proxyuser</username>
                <!--代理的密码，用户名和密码表示代理服务器认证的登录名和密码。 -->
                <password>somepassword</password>
                <!--不该被代理的主机名列表。该列表的分隔符由代理服务器指定；例子中使用了竖线分隔符，使用逗号分隔也很常见。 -->
                <nonProxyHosts>*.google.com|ibiblio.org</nonProxyHosts>
            </proxy>
        </proxies>
        <profiles>
            <profile>
                <!-- profile的唯一标识 -->
                <id>test</id>
                <!-- 自动触发profile的条件逻辑 -->
                <activation />
                <!-- 扩展属性列表 -->
                <properties />
                <!-- 远程仓库列表 -->
                <repositories />
                <!-- 插件仓库列表 -->
                <pluginRepositories />
            </profile>
        </profiles>
        <activation>
            <!--profile默认是否激活的标识 -->
            <activeByDefault>false</activeByDefault>
            <!--当匹配的jdk被检测到，profile被激活。例如，1.4激活JDK1.4，1.4.0_2，而!1.4激活所有版本不是以1.4开头的JDK。 -->
            <jdk>1.5</jdk>
            <!--当匹配的操作系统属性被检测到，profile被激活。os元素可以定义一些操作系统相关的属性。 -->
            <os>
                <!--激活profile的操作系统的名字 -->
                <name>Windows XP</name>
                <!--激活profile的操作系统所属家族(如 'windows') -->
                <family>Windows</family>
                <!--激活profile的操作系统体系结构 -->
                <arch>x86</arch>
                <!--激活profile的操作系统版本 -->
                <version>5.1.2600</version>
            </os>
            <!--如果Maven检测到某一个属性（其值可以在POM中通过${name}引用），其拥有对应的name = 值，Profile就会被激活。如果值字段是空的，那么存在属性名称字段就会激活profile，否则按区分大小写方式匹配属性值字段 -->
            <property>
                <!--激活profile的属性的名称 -->
                <name>mavenVersion</name>
                <!--激活profile的属性的值 -->
                <value>2.0.3</value>
            </property>
            <!--提供一个文件名，通过检测该文件的存在或不存在来激活profile。missing检查文件是否存在，如果不存在则激活profile。另一方面，exists则会检查文件是否存在，如果存在则激活profile。 -->
            <file>
                <!--如果指定的文件存在，则激活profile。 -->
                <exists>${basedir}/file2.properties</exists>
                <!--如果指定的文件不存在，则激活profile。 -->
                <missing>${basedir}/file1.properties</missing>
            </file>
        </activation>
        <!--该元素包含了一组activeProfile元素，每个activeProfile都含有一个profile id。任何在activeProfile中定义的profile id，不论环境设置如何，其对应的 profile都会被激活。如果没有匹配的profile，则什么都不会发生。-->
        <activeProfiles>
            <!-- 要激活的profile id -->
            <activeProfile>env-test</activeProfile>
        </activeProfiles>
    </servers>
```





# 常用maven命令

格式为  `mvn [life-stage]:[goal-name]` 可接受以下参数

- -D 指定参数，如-Dmaven.test.skip=true 跳过单元测试
- -P 指定Profile配置，可用于区分环境



常用命令

```bash
mvn archetype:create # 创建maven项目
mvn compile # 编译主程序代码
mvn test-compile # 编译测试代码，compile之后会生成target文件夹
mvn test # 运行应用程序中的单元测试
mvn site # 生成项目相关信息的网站——这是啥？
mvn clean # 清除目标目录中的生成结果
mvn package # 依据项目生成jar文件，打包之前会进行编译，测试
mvn install # 在本地Repository中安装jar
mvn deploy # 在整合或发布环境下执行，将最终版本的包拷贝到远程的repository中

```



常用命令参数

```bash
-Dmaven.test.skip=true # 跳过测试类
-DdownloadSource=true # 下载jar包源码
-DdownloadJavadocs=true # 下载javadocs
```



# maven依赖

依赖范围

| 依赖范围 | 编译有效 | 测试有效 | 运行有效 | 例子        |
| -------- | -------- | -------- | -------- | ----------- |
| compile  | Y        | Y        | Y        | Spring-core |
| test     | -        | Y        | -        | Junit       |
| provided | Y        | Y        | -        | Servlet-api |
| runtime  | -        | Y        | Y        | JDBC驱动    |
| system   | Y        | Y        | -        | 本地        |



- 编译有效：
- 测试有效：
- 运行有效：运行的时候需要引入（运行有效才是最后真正有效的）



传递性依赖： A依赖B，B依赖C==A依赖C



依赖调解

- 第一原则：优先选路径最短的
- 路径相同下，选在同路径下优先声明者



# 仓库

构件



私服

他是架设在局域网内的仓库服务，私服代理广域网上的远程仓库。建立私服的好处

- 节省自己的外网带宽。
- 加速maven构建（内网缓存）
- 部署第三方构件（私有构件共享）
- 提高稳定性，增强控制（内网缓存）
- 降低中央仓库的负荷（对外网请求次数少）



# 使用入门maven生命周期

maven生命周期是抽象的，即生命周期本身不提供任何实际工作，实际工作由插件完成。所以命令 `mvn [life-stage] [goal-name] ` 中，`goal-name` 就是指插件目标

maven拥有三套相互独立的生命周期

clean生命周期 || default生命周期 || site生成站点生命周期 



# 高级入门 聚合与继承

聚合：将项目的各个模块聚合在一起构建

具体见上面的xml元素，注意配置package–>pom以及modules元素即可

聚合构建顺序

1. 按照定义的module先后顺序
2. 如果发现module有依赖于或继承的其他模块，优先构建其他模块

dependencyManagement元素：不会给子嘞引入任何依赖，子类仍然要配置dependency，如何发现和父类配置一致，则会继承

scope–>import：只在dependencyManagement下生效，由于会包含大量依赖，如果想把这些依赖分类管理，就需要分文件配置，通过import把文件下的配置引入当前pom

聚合和继承通常在一个pom下，约定优于配置，遵循maven原是默认的约定



# 高级入门maven profile

profile定义一系列配置信息，然后指定激活条件，如此达到不同环境使用不同配置的效果

例如：数据库配置文件，可以配置不同的账号密码

1. 需要让配置文件识别maven属性语法

   ```xml
   <!--超级pom，所有pom都会继承的这样一个文件-->
   <resources>
   	<resource>
   		<directory>${project.basedir}/src/main/resources<deirectory>
   	<filter>true</filter>
   	</resource>
   </resources>
   ```

   

2. 配置profile

   ```xml
   <!--项目pom.xml-->
   <profiles>
       <profile>
           <id>dev</id>
               <properties>
                   <db.username>dev</db.username>
                   <db.password>abc</db.password>
               </properties>
       </profile>
   </profiles>
   ```

3. 数据库配置文件

   ```text
   username=${db.name}
   password=${db.password}
   ```

4. 命令行激活

   ```bash
   mvn clean install -P dev ,dev-x
   ```



激活方式：

- 命令行激活
- 基于操作环境激活
- 基于文件存在激活
- 默认激活













