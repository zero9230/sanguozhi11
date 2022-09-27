# 1 typora标题自动编号

方案：修改css

步骤：

1. 打开typora的theme文件夹，创建base.user.css文件
2. 写入内容如下：

```css
.sidebar-content {
    counter-reset: h1
}

.outline-h1 {
    counter-reset: h2
}

.outline-h2 {
    counter-reset: h3
}

.outline-h3 {
    counter-reset: h4
}

.outline-h4 {
    counter-reset: h5
}

.outline-h5 {
    counter-reset: h6
}

.outline-h1>.outline-item>.outline-label:before {
    counter-increment: h1;
    content: counter(h1) ". "
}

.outline-h2>.outline-item>.outline-label:before {
    counter-increment: h2;
    content: counter(h1) "." counter(h2) ". "
}

.outline-h3>.outline-item>.outline-label:before {
    counter-increment: h3;
    content: counter(h1) "." counter(h2) "." counter(h3) ". "
}

.outline-h4>.outline-item>.outline-label:before {
    counter-increment: h4;
    content: counter(h1) "." counter(h2) "." counter(h3) "." counter(h4) ". "
}

.outline-h5>.outline-item>.outline-label:before {
    counter-increment: h5;
    content: counter(h1) "." counter(h2) "." counter(h3) "." counter(h4) "." counter(h5) ". "
}

.outline-h6>.outline-item>.outline-label:before {
    counter-increment: h6;
    content: counter(h1) "." counter(h2) "." counter(h3) "." counter(h4) "." counter(h5) "." counter(h6) ". "
}

/** initialize css counter */
#write {
    counter-reset: h1
}
 
h1 {
    counter-reset: h2
}
 
h2 {
    counter-reset: h3
}
 
h3 {
    counter-reset: h4
}
 
h4 {
    counter-reset: h5
}
 
h5 {
    counter-reset: h6
}
 
/** put counter result into headings */
#write h1:before {
    counter-increment: h1;
    content: counter(h1) ". "
}
 
#write h2:before {
    counter-increment: h2;
    content: counter(h1) "." counter(h2) ". "
}
 
#write h3:before,
h3.md-focus.md-heading:before /** override the default style for focused headings */ {
    counter-increment: h3;
    content: counter(h1) "." counter(h2) "." counter(h3) ". "
}
 
#write h4:before,
h4.md-focus.md-heading:before {
    counter-increment: h4;
    content: counter(h1) "." counter(h2) "." counter(h3) "." counter(h4) ". "
}
 
#write h5:before,
h5.md-focus.md-heading:before {
    counter-increment: h5;
    content: counter(h1) "." counter(h2) "." counter(h3) "." counter(h4) "." counter(h5) ". "
}
 
#write h6:before,
h6.md-focus.md-heading:before {
    counter-increment: h6;
    content: counter(h1) "." counter(h2) "." counter(h3) "." counter(h4) "." counter(h5) "." counter(h6) ". "
}
 
/** override the default style for focused headings */
#write>h3.md-focus:before,
#write>h4.md-focus:before,
#write>h5.md-focus:before,
#write>h6.md-focus:before,
h3.md-focus:before,
h4.md-focus:before,
h5.md-focus:before,
h6.md-focus:before {
    color: inherit;
    border: inherit;
    border-radius: inherit;
    position: inherit;
    left:initial;
    float: none;
    top:initial;
    font-size: inherit;
    padding-left: inherit;
    padding-right: inherit;
    vertical-align: inherit;
    font-weight: inherit;
    line-height: inherit;
}
```



# 2 可折叠的代码栏

方案：使用detail标签

这个效果一般

```
<details>
  <summary>折叠代码块</summary>
  <pre><code> 
     System.out.println("虽然可以折叠代码块");
     System.out.println("但是代码无法高亮");
  </code></pre>
</details>

解读

details：折叠语法标签
summary：折叠语法展示的摘要
pre：以原有格式显示元素内的文字是已经格式化的文本
code：指定代码块
```

 **效果演示** 



<details>
  <summary>折叠代码块</summary>
  <pre><code> 
     System.out.println("虽然可以折叠代码块");
     System.out.println("但是代码无法高亮");
  </code></pre>
</details>


# 3 有序列表多重编号

```css
/**  自定义有序列表  **/
ol ol {
    list-style-type: lower-roman;
}

ol ol ol {
    list-style-type: lower-latin;
}
```



# 4 SliDev

[SliDev使用指南](https://cn.sli.dev/guide/)

# 5 pandoc

## 5.1 资料链接💾

>  [github-pandoc](https://github.com/jgm/pandoc) 

这是[Pandoc官方网站上的说明](http://pandoc.org/installing.html)：

> 如果以后要卸载软件包，可以通过下载 [此脚本](https://raw.githubusercontent.com/jgm/pandoc/master/osx/uninstall-pandoc.pl) 并使用来运行它 `perl uninstall-pandoc.pl`。
>
> 安装使用brew install pandoc即可

## 5.2 基础使用

```bash
# 读取文件
pandoc -f 输入格式 -t 输出格式 -o 输出文件名 输入文件

# 读取网页
pandoc -f html -t 输出格式 -o 输出文件名 --request-header User-Agent:"Mozilla/5.0" \
  https://www.fsf.org
```



## 5.3 常见的输入 / 输出格式

|          格式          |   参数   |
| :--------------------: | :------: |
|        CSV 表格        |   csv    |
|       Word 文档        |   docx   |
|      EPUB 电子书       |   epub   |
|       HTML 网页        |   html   |
|     Markdown 文档      | markdown |
| PDF 文档（仅支持输出） |   pdf    |
| PPt 文档（仅支持输出） |   pptx   |
|       JSON 数据        |   json   |

## 5.4 word导出

![image-20220509162917198](typora.assets/image-20220509162917198.png)

```bash
--toc # 生成目录
--toc-depth=NUMBER # 生成的目录深度
--wrap=auto|none|preserve # 文字换行方式
--reference-doc=FILE # 指定模板（word模板）
```



默认word模板可用以下命令查看

```bash
pandoc --print-default-data-file reference.docx > custom-reference.docx
```

修改完成后，可以通过 `--reference-doc=custom-reference.docx` 来指定模板。 也可以将模板文件放置到 Pandoc 的数据文件夹下，并命名为 `reference.docx`，后续 Pandoc 将把这个文件作为默认模板进行使用。 这里的数据文件夹，可以在 `pandoc -v` 指令的打印信息中，通过 `data-dir` 字段来获取。



## 5.5 参考链接

1.  [Pandoc 实用教程](http://www.atdevin.com/3582.html)  



# 6 typora下载地址

typora开始发行正式版并收费了，因此保存beta版下载地址以防意外

 [mac beta版最后的安装包](typora.assets/Typora-0.11.18.dmg) 

 [win beta版最后的安装包](typora.assets/typora-update-x64-1117.exe) 

 [mac release下载地址](https://typora.io/dev_release.html) 

 [win release下载地址](https://typora.io/windows/dev_release.html) 

