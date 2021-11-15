# 概述

**原生NIO存在的问题**

- NIO类库和API繁杂，`Selector`, `ServerSocketChannel`, `SocketChannel`, `ByteBuffer` 等
- 需要额外技能：java多线程编程，因为NIO涉及Reactor模式；必须熟悉网络编程
- 开发工作量和难度较大
- NIO的bug：如 Epoll bug，会导致Selector空轮询，造成CPU100%。



**netty优点**

- 设计优雅：适用于各种类型统一的API阻塞和非阻塞Socket，基于灵活可扩展的时间模型，可定制线程模型
- 使用方便：文档和指南详细
- 高性能，高吞吐
- 安全：完整的`SSL/TLS`和`StartTLS`支持
- 社区活跃



netty由于`netty 5`出现重大bug，已被废弃，目前推荐使用`netty4.x`稳定版

# netty高性能架构设计

netty基于主从Reactor多线程模型做了一定改进，其中主从Reacor多线程模型有多个Reactor

传统BIO模式的缺点

1. 每个连接占用一个线程，当并发数很大，会创建大量线程，占用大量系统资源
2. 连接创建后，如果当前线程没有数据可读，则线程会阻塞在读操作，浪费资源



为了解决上述两个问题，采用以下方案

1. 基于IO复用模型：多个连接共用一个阻塞对象
2. 基于线程池复用线程资源

## Reactor模式

### 概述

![img](netty.assets/chapter05_03.png)

说明

1. Reactor模式，通过一个或多个输入，同时传递给服务处理器的模式（基于事件驱动）
2. 服务器端处理多个请求，并分派给处理线程，因此也叫Dispatcher模式
3. Reactor模式使用IO复用监听事件，收到事件后分发给某个线程，是高并发关键



### Reactor模式核心组成

1. Reactor：运行于单独线程中，负责监听和事件分发
2. Handler：被分派任务的实际执行者

### Reactor模式分类

根据Reactor数量和处理资源线程池数量分3类：

1. 单Reactor单线程
2. 单Reactor多线程
3. 主从Reactor多线程



## 单Reactor单线程

![img](netty.assets/chapter05_04.png)

说明

1. 如果是建立连接请求事件，则由Acceptor通过accept处理连接请求，然后创建一个handler对象处理连接完成后的后续业务处理
2. 如果不是建立连接事件，则Reactor会分发调用连接对应的Handler来响应
3. Handler会完成 Read—>业务处理–〉Send 的完成业务流程



**分析**

优点

1. 模型简单，没有多线程、进程通信、竞争的问题

缺点

1. 单线程的性能问题
2. 单线程的可靠性问题：如果该Reactor线程意外终止/进入死循环，则整个系统通信模块不可用

使用场景：客户端数量有限，业务处理速度快，如redis在处理时间复杂度O(1)的情况



## 单Reactor多线程

![img](netty.assets/chapter05_05.png)

说明

1. handler只负责响应时间，不处理具体业务。通过read读取数据后，分发给worker
2. worker线程池会分配独立线程完成业务，并将结果返回给handler
3. handler收到相应后，通过send将结果返回给client



## 主从Reactor多线程

Reactor单线程运行在高并发场景下容易成为性能瓶颈，因此优化成多线程模式

![img](netty.assets/chapter05_06.png)

说明

1. MainReactor通过select监听连接事件，收到事件后，通过acceptor处理连接事件
2. Acceptor处理连接事件后，MainReactor将连接分配给SubReactor
3. SubReactor将连接加入到连接队列进行监听，并创建handler进行各种事件处理



评价

优点

1. 职责明确，父线程只负责接收新连接，子线程完成后续业务处理
2. 数据交互简单：Reactor主线程只需要把新连接传给子线程，子线程无需返回数据

缺点：复杂度高



## Netty模型

netty基于主从Reactor多线程模式，其中有多个Reactor

![img](netty.assets/chapter05_08.png)

说明

1. BossGroup线程为辅Selector，只关注Accept
2. 当接收到Accept事件，获取到对应的SocketChannel，封装成NIOSocketChannel并注册到worker线程（事件循环），并进行维护
3. 当worker线程监听到selector中通道发生自己感兴趣的事件后，就由handler处理



- 工作原理图补充说明

![img](netty.assets/chapter05_09.png)



![img](netty.assets/chapter05_10.png)

说明

1. netty抽象出两组线程池：BossGroup用于负责客户端链接，workerGroup负责网络读写
2. 两者都是NioEventLoopGroup
3. 其中NioEventGroup相当于一个事件循环组，包含多个事件循环，每个事件循环是NioEventLoop
4. NioEventLoop表示一个不断循环的执行处理任务的线程





# 参考文献

1.  [netty学习手册](https://dongzl.github.io/netty-handbook/#/_content/chapter04) 
2.  [netty官网](https://netty.io/) 