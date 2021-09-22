# git知识体系

多分支体系





# git常见场景及操作

## 本地配置多git账号

如果你想在一台电脑上同时配置两个git账号，比如gitlab账号123@126.com和github的456@126.com
那么应该如何做呢？

1. 进入git bash，执行 `ssh-keygen -t rsa -C 【123@126.com】`

出现以上提示表示需要输入你的rsa文件名，这里我们命名为：id_rsa，输入之后，回车回车。。。

2. 依照上面的方法生成456@126.com的密匙，下面即为生成的2个账户的四个文件，一个是私钥id_rsa，一个是公钥id_rsa.pub

3. 将两个账号生成的以上四个文件移动至.ssh文件夹下

> 注意点
> 1、上面执行完一定要注意，两个账号生成的id_rsa文件一定要取不同的名字，不然会覆盖；
> 2、两个账号生成的id_rsa文件不一定都在.ssh文件夹下，如果不在，请在计算机的搜索栏将文件搜索出来，然后移到.ssh文件夹下，.ssh文件夹默认在C:\Users\Administrator.ssh

4. 查看账号的公钥信息

```bash
cat id_rsa.pub    # gitlab公钥
cat id_rsa_hub.pub # github公钥
```




5. 把gitlab对应的公钥和github对应的公钥上传到服务器

   Settings	—>	SSH and GPG keys	—>	NewSSHkey，把.pub文件（公钥）的内容放到密钥中

6. 查看.ssh文件夹中是否有config文件，如果没有，请新建一个config文件，配置如下：

   ```bash
   Host gitlab.com 							# 这是远程账号网址真实域名
   HostName gitlab.com 					# 这是你为这个主机取的名字，随意，题主为了省事直接叫gitlab.com
   IdentityFile C:\Users\Administrator.ssh\id_rsa # 这个是该账号的私钥文件
   PreferredAuthentications publickey 						# 选择用公钥与远程建立联系
   User 123@126.com 							# 用户的远程账号
   
   Host github.com
   HostName github.com
   IdentityFile C:\Users\Administrator.ssh\id_rsa_hub
   PreferredAuthentications publickey
   User 456@126.com
   ```

   PS：如果要配置3个，4个…同样的方法往下加即可
   
7. 测试是否配置成功
   进入.ssh文件夹，执行 ssh -T git@gitlab.com，然后选择yes，如果没有报错则配置成功，下面就可以自由的pull不同的远程仓库了，注意，第一次git clone远程仓库时，需要先执行git init，之后就不用了。

   目前成功的标志都是

   ```bash
   ➜  .ssh ssh -T git@github.com
   Hi zero9230! You've successfully authenticated, but GitHub does not provide shell access.
   ```

   

参考文章：https://www.cnblogs.com/popfisher/p/5731232.html

## 本地和远程仓库分支关联

## 切换账号提交代码

有的时候我们有两个甚至多个git账号（公司的git账号和自己的github），为了不混淆提交，我们需要在提交之前查看自己的git账号必要时进行切换。

```bash
git config user.name								# 查看当前git用户名 
git config user.email								# 查看当前git邮箱
git config --global user.name "user name"	# 切换git用户名:
git config --global user.email  "e-mail"	# 切换git邮箱
```





### 重新提交

```bash
然后执行下面的命令，重新修改最新的提交，改正作者和提交者的错误信息。

$ git commit --amend --allow-empty --reset-author
```

