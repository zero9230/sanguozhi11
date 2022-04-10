# 前期准备

## 安装

1. 下载flutter源代码

   仓库地址： https://github.com/flutter/flutter

2. 配置环境路径

   ```bash
   export PATH=`pwd`/flutter/bin:$PATH
   ```

   

3. 运行flutter doctor

   运行以下命令查看是否需要安装其他依赖

   ```bash
   flutter doctor
   ```

4. 安装Xcode



## 运行app

1. 给工程文件授权

   ```bash
   sudo chmod -R 777 [projectName]
   ```

2. 双击打开 `[projectName]/ios/Runner.xcodeproj`，使用xcode打开工程文件



## 工程目录介绍

```bash
#安卓文件
android/

# 编译目录
build/

# ios文件
ios/

# 源码和资源文件
lib/
test/

# 项目依赖文件，一般放第三方库依赖
pubspec.yaml
```



## 入口文件、方法

每个flutter项目的lib目录中都有一个main.dart，其中的main方法即是入口

```dart
void main(){
  runApp(MyApp());
}
// 也可简写为
void main()=>runApp(MyApp());
```



自定义组件

## 引入基本库

fim， 呼出提示

```dart
import 'package:flutter/material.dart';
```



# 基本语法

## 自定义组件

```dart
class MyApp extends StatelessWidget{
  @Override
  Widget build(BuildContext context){
    return null;
  }
}
  
```



## MaterialApp

一般作为顶层widget使用，其中封装了theme, color, home等属性

## Scaffold

是Material Design布局的基本实现，提供了drawer、snackbar和底部的sheet的API。

Scaffold有一下几个主要属性

- appBar——在界面顶部显示一个bar
- body——主体内容
- drawer——抽屉菜单控件



statelessWidget

##  Container组件

容器组件，常用于布局



## Text组件

## 列表组件ListView

1. 垂直列表
2. 水平列表
3. 



ListTile——列表单元格



动态列表——ListView.builder

```dart
return ListView.builder(
  itemCount:list.length;
  itemBuilder:(context,index){
    return ListTile(
      title: Text(this.list[index]),
    );
  }
);
```

## 网格布局组件GridView

GridView.count

GridView.builder



## StatefulWidget

setState()方法用于更新状态，即让后台变量实时更新到页面中



# 参考资料

1.    [Flutter中文网](https://flutterchina.club/) 
1.    [flutter使用sqflite](https://flutter.cn/docs/cookbook/persistence/sqlite) 
1.    [B站——Flutter2入门实战视频教程](https://www.bilibili.com/video/BV1S4411E7LY?p=16) 
1.    [Flutter学习思维导图 ](https://www.cnblogs.com/yangyxd/p/11685964.html) 
1.    [Dart思维导图](https://blog.csdn.net/sun_cui_hua/article/details/109330394) 

