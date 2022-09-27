  
在Obsidian的0.14版本更新中，官方更新了一个叫“Callout Blocks”的功能。善用这个功能可以让我们把内容编写地更美观，也能在需要的地方给我们提供足够显眼的强调。

  

Callout Blocks的基本语法如下：

  

```Markdown
> [!类型] 标题
> Hello world!
> 正文
> 正文**依然支持Markdown语法和双链功能**
```

  

> [!INFO] 标题
> Hello world!
> 正文
> 正文**依然支持Markdown语法和双链功能**

  

不难发现“Callout Blocks”本质上只是一个`> 引用块`，并且由三部分组成——类型、标题和正文。

  

# 1 Callout Blocks的类型

  

Callout Blocks原生支持以下类型——

  

```Markdown

- note

- abstract/summary/tldr

- info/todo

- tip/hint/important

- success/check/done

- question/help/faq

- warning/caution/attention

- failure/fail/missing

- danger/error

- bug

- example

- quote/cite

```

  

> [!quote] 标题

> 正文

  

> 引言

  

可以自定义新种类的Callout Blocks，但本文不做详细说明。

在创建Callout Block的时候，我们首先需要按照`> 引用块`的Markdown格式，在文本编辑器中输入`> `（**注意别漏了空格**），然后使用`[!类型]`的语法，在半角方括号中输入半角惊叹号，然后输入我们想要的类型，再空格，写标题。

  

```Markdown

> [!Tip]

> 你的大脑更擅长思考，而不是记忆。

```

> [!Tip]
> 你的大脑更擅长思考，而不是记忆。

  

# 2 Callout Blocks的标题

  

在指定Callout Block的类型后，再敲击一个空格，然后输入该Callout Block的标题。

  

```Markdown

> [!Tip] 阅读《搞定》的启示

> 你的大脑更擅长思考，而不是记忆。

```

> [!Tip]- 阅读《搞定》的启示
> 你的大脑更擅长思考，而不是记忆。

  

除此之外，在`> [!类型]`和`内容`之间插入“+”和“-”可以将这个Callout Block变成折叠方块。其中——

  

```Markdown

> [!类型]- 标题 %%代表该方块默认折叠

> [!类型]+ 标题 %%代表该方块默认展开

```

  

具体的效果还请各位读者到Obsidian当中自行实践。

  

# 3 Callout Blocks的正文

  

Callout Blocks内部的正文依然支持Markdown语法和双链功能，各位读者可以在方块内部肆意挥发自己的创意。

  

这里需要提醒的一点是，在`> 引用块`的编辑状态下，如果你想要在下一个段落前额外添加一个空行，那么你很有可能会连续按下两次回车键，但这将退出`> 引用块`的编辑——在引用块、有序列表、无序列表的空行上再次敲击回车会消除该行的格式。因此正确的做法是“Shift+回车”——这将继续向下创造新的空行，原有的空行将保持原有的格式。

  

```Markdown

> [!Tip] 阅读《搞定》的启示

> 你的大脑更擅长思考，而不是记忆。

> %% 这里按“Shift+回车”

> 2分钟之内能搞定的事就不要拖延，马上去做！

```

> [!Tip] 阅读《搞定》的启示
> **你的大脑更擅长思考，而不是记忆。**
> 
> 2分钟之内能搞定的事就不要拖延，马上去做！

  

# 4 应用

  

Callout Blocks的基本用法就是这么简单，而关于工具的应用，则完全取决于你的思想、方法和创意。

  

你可以像这样做笔记的例题——

  

```Markdown

> [!Example]- 例1: 计算行列式 $$\begin{vmatrix}

> a & b \\

> c & d \\

> \end{vmatrix}$$

>

> **解:** $$

> \begin{vmatrix}

> a & b \\

> c & d \\

> \end{vmatrix}

> =ad-bc

> $$

```

  

> [!Example]- 例1: 计算行列式 $$\begin{vmatrix}
> a & b \\
> c & d \\
> \end{vmatrix}$$
>
> **解:** $$
> \begin{vmatrix}
> a & b \\
> c & d \\
> \end{vmatrix}
> =ad-bc
> $$

  

也可以做一个这样的总结概括——

  

```Markdown

> [!Summary] 本文主要内容

> 1. Callout Blocks的基本语法

> 2. 方块类型

> 3. 标题与折叠

> 4. 正文

```

  

> [!Summary] 本文主要内容
> 1. Callout Blocks的基本语法
> 2. 方块类型
> 3. 标题与折叠
> 4. 正文

  

总之，**工具是次要的，思想和方法才是主要的**。现在你掌握了工具的基本用法，接下来你要思考的是如何利用这些工具去更好的生活、学习和工作。

  

祝各位读者在有限的人生中，都能用简单的工具创造无限的可能。