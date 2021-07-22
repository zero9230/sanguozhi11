# 业务域

1. 类目
	1. 类目包问题
		1. 如何打包
		2. 如何推包
		3. 各业务应用如何读包
2. 商品
	1. 商品高并发问题
	2. 数据容灾和一致性问题
	3. 领域模型建设问题
3. 产品
	1. 如何组合多种算法能力和工程能力进行数据产出
	2. 离线数据加工体系问题
4. 库存
	1. 库存防超卖问题
	2. 线上库存和本地仓储问题



# 基建



1. DB
	1. 如何抗超高qps
	2. 跨库跨表查询问题
	3. 主备容灾以及数据同步问题
2. 缓存
	1. 缓存如何抗超高qps
	2. 数据一致性问题
	3. 跨表查询
3. 消息队列
4. zk
5. nginx
6. aone做了些什么？
	1. 托管git代码，代码提交，分支合并、构建、打包、部署
	2. 如何实现集群化部署