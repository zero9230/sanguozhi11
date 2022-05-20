# 线程和线程池

## 线程

### 实现方式

1. 继承Thread类
2. 实现Runnable接口
3. 实现Callable接口

## 线程池

## ThreadLocal



# Atomic类



# AbstractQueuedSynchronizer

## AQS概述

concurrent包的结构层次

![concurrent目录结构.png](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d646139353165623939633564616266642e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)



concurrent包整体实现如下图

![concurrent包实现整体示意图.png](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d323464613832326464633232366230332e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)



AQS可重写的方法如下图

![AQS可重写的方法.png](Java%E5%B9%B6%E5%8F%91.assets/163260cff7d16b38~tplv-t2oaga2asx-zoom-in-crop-mark:1304:0:0:0.awebp)



在实现同步组件时AQS提供的模板方法如下图

![AQS提供的模板方法.png](Java%E5%B9%B6%E5%8F%91.assets/163260cff87fe8bf~tplv-t2oaga2asx-zoom-in-crop-mark:1304:0:0:0.awebp)



独占锁

> 独占式获取同步状态，如果失败则插入同步队列进行等待

共享锁

> 共享式获取同步状态，同一时刻有多个线程获取同步状态

## 同步队列

AQS内部的静态内部类Node，具有如下属性

> - volatile int waitStatus //节点状态 
> - volatile Node prev //当前节点/线程的前驱节点
> - volatile Node next; //当前节点/线程的后继节点
> - volatile Thread thread;//加入同步队列的线程引用
> - Node nextWaiter;//等待队列中的下一个节点



![LockDemo debug下 .png](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d643035643366343463653463323035612e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)





另外AQS中有另外两个重要成员变量

```java
private transient volatile Node head;
private transient volatile Node tail;
```

即AQS通过头尾指针管理同步队列，示意图如下：

![队列示意图.png](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d646266633937356433363031626235322e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)



## 独占锁

### 独占锁的获取（acquire方法）

对enq()方法可以做这样的总结：

1. 在当前线程是第一个加入同步队列时，调用compareAndSetHead(new Node())方法，完成链式队列的头结点的初始化；
2. 自旋不断尝试CAS尾插入节点直至成功为止。



> 获取同步锁失败，入队操作

![自旋获取锁整体示意图.png](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d336665383363666166303361303263382e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)



> 获取锁成功，出队操作

![687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d313339363365316233626366653635362e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d313339363365316233626366653635362e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)



独占式锁的获取过程，即acquire()方法的执行流程如下图所示：

![独占式锁获取（acquire()方法）流程图.png](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d613064393133646334306461353632392e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)



### 独占锁的释放（release方法）

总体来说：**在获取同步状态时，AQS维护一个同步队列，获取同步状态失败的线程会加入到队列中进行自旋；移除队列（或停止自旋）的条件是前驱节点是头结点并且成功获得了同步状态。在释放同步状态时，同步器会调用unparkSuccessor()方法唤醒后继节点。**



### 可中断式获取锁（acquireInterruptibly）

可响应中断式锁可调用方法lock.lockInterruptibly();而该方法其底层会调用AQS的acquireInterruptibly方法

与acquire方法逻辑几乎一致，唯一的区别是当**parkAndCheckInterrupt**返回true时即线程阻塞时该线程被中断，代码抛出被中断异常。

### 超时等待式获取锁（tryAcquireNanos）



![超时等待式获取锁（doAcquireNanos()方法）](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d613830373739643437333661666238372e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)

## 共享锁

### 共享锁获取（acquireShared）



### 共享锁的释放（releaseShared）



### 可中断（acquireSharedInterruptly）



### 超时等待（tryAcquireSharedNanos）





## ReentrantLock

ReentrantLockde 底层原理主要是同步语义的学习：

1. 重入性的实现原理；
2. 公平锁和非公平锁。

### 重入性原理

要想实现重入性，要解决两个问题

1. 在线程获取锁的时候，如果获取锁的线程是当前线程则直接再次获取成功
2. 由于锁会被获取n次，因此只有锁在被释放同样的n次后，该锁才算完全释放成功



需要注意的是，重入锁的释放必须得等到同步状态为0时锁才算成功释放



### 公平锁和非公平锁

公平性解释：如果一个锁是公平的，那么锁的获取顺序应该符合请求上的绝对时间顺序，满足**FIFO**。

ReentrantLock默认选择的是非公平锁，可通过传入一个boolean值声明为公平锁





# Condition

## 简介

功能说明：

使用Lock和Condition配合，可以实现如下效果

```text
多线程之间顺序调用，实现A->B->C轮流启动
A打印5次，B打印10次，C打印15次，A打印5次……等循环
```



关键方法：

```java
condition1.await()
condition1.signal()
```







Condition与Lock配合完成等待通知机制，设计功能上用于取代Object类的wait，signal系列的方法。Condition的具有更高的可控制性和扩展性，除了在使用方式上不同，在功能特性上也有区别：

1. Condition能支持不响应中断
2. 能支持多个等待队列（new多个Condition对象）
3. 能支持超时时间设置

>  针对Object的wait方法

1. void await() throws InterruptedException:当前线程进入等待状态，如果其他线程调用condition的signal或者signalAll方法并且当前线程获取Lock从await方法返回，如果在等待状态中被中断会抛出被中断异常；
2. long awaitNanos(long nanosTimeout)：当前线程进入等待状态直到被通知，中断或者**超时**；
3. boolean await(long time, TimeUnit unit)throws InterruptedException：同第二种，支持自定义时间单位
4. boolean awaitUntil(Date deadline) throws InterruptedException：当前线程进入等待状态直到被通知，中断或者**到了某个时间**



> 针对Object的notify/notifyAll方法

1. void signal()：唤醒一个等待在condition上的线程，将该线程从**等待队列**中转移到**同步队列**中，如果在同步队列中能够竞争到Lock则可以从等待方法中返回。
2. void signalAll()：与1的区别在于能够唤醒所有等待在condition上的线程

## 原理分析

### 等待队列

ConditionObject中有两个成员变量

```java
/** First node of condition queue. */
private transient Node firstWaiter;
/** Last node of condition queue. */
private transient Node lastWaiter;
```

其中Node类复用了AQS中的Node类，Node类中有这样一个属性

```
//后继节点
Node nextWaiter;
```

由这两个指针维护一个单向的等待队列，结构图如下

![等待队列的示意图](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d356161316565316165386362376635612e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)





同时还有一点需要注意的是：我们可以多次调用lock.newCondition()方法创建多个condition对象，也就是一个lock可以持有多个等待队列。而在之前利用Object的方式实际上是指在**对象Object对象监视器上只能拥有一个同步队列和一个等待队列，而并发包中的Lock拥有一个同步队列和多个等待队列**。示意图如下：

![AQS持有多个Condition.png](https://camo.githubusercontent.com/f1fac20ea4a6c7484460efca06d720839b35576b70fce676041abb8cc28545d7/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d363632313138316663313936303363322e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430)



## await实现原理

![await方法示意图](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d316362316332666533633164646633382e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)



## signal/signalAll实现原理

![signal执行示意图](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d333735306635626166373939353632332e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)



## await与signal/signalAll的结合思考

![condition下的等待通知机制.png](Java%E5%B9%B6%E5%8F%91.assets/687474703a2f2f75706c6f61642d696d616765732e6a69616e7368752e696f2f75706c6f61645f696d616765732f323631353738392d303234343964633331366665316465362e706e673f696d6167654d6f6772322f6175746f2d6f7269656e742f7374726970253743696d61676556696577322f322f772f31323430.png)



# Semaphore

# CountDownLatch



## 参考链接

1.  [初识Lock与AbstractQueuedSynchronizer(AQS)](https://juejin.cn/post/6844903601534418958) 
2.  [深入理解AbstractQueuedSynchronizer(AQS).md](https://github.com/CL0610/Java-concurrency/blob/master/09.%E6%B7%B1%E5%85%A5%E7%90%86%E8%A7%A3AbstractQueuedSynchronizer(AQS)/%E6%B7%B1%E5%85%A5%E7%90%86%E8%A7%A3AbstractQueuedSynchronizer(AQS).md) 

