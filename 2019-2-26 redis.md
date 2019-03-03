---
title: 2019-2-26 redis
tags: 
grammar_cjkRuby: true
---

当redis锁被释放后，发送通知给其他等待的请求（发布订阅机制）
subscribe <> publish

![释放锁][1]
![分布式锁][2]
![分布式锁][3]
![JVM锁][4]

  
  
#### 等待请求过多咋办？
使用JVM锁+redis锁。如图4.（纠正finally：lock.unlock);


客户端运行时间过慢，导致锁过期，程序依然运行。锁被两个对象持有，怎么解决？
无最优解（设置长超时时间）。


### RedLock
![enter description here][5]
![enter description here][6]
![enter description here][7]
### 分布式锁总结
* 分局应用场景选择合适的，没有最完美的
* 分布式环境中，对资源进行锁有时候很重要，比如秒杀，这时候使用分布式锁可以有效地控制资源
 


  [1]: ./images/1551141921942.jpg "1551141921942.jpg"
  [2]: ./images/1551141833558.jpg "1551141833558.jpg"
  [3]: ./images/1551141841292.jpg "1551141841292.jpg"
  [4]: ./images/1551142111924.jpg "1551142111924.jpg"
  [5]: ./images/1551142803396.jpg "1551142803396.jpg"
  [6]: ./images/1551178393943.jpg "1551178393943.jpg"
  [7]: ./images/1551179196673.jpg "1551179196673.jpg"