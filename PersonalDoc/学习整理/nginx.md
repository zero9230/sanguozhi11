

1. 反向代理，
2. 负载均衡

什么是nginx（engine x）：高性能的http和反响代理web服务器，同时提供IMAP/POP3/SMTP服务

可支持5W个并发连接数，几乎7*24小时不间断运行



iphash对客户端请求的ip进行hash操作，根据hash将该客户端请求到同一台服务器上，用于解决session不共享问题

3. 动静分离

   jquery、bootstrap等内容在客户端运行



# 常用命令

```bash
nginx							# 启动
nginx -s stop			# 停止
nginx -s reload		# 重启
nginx -s quit			# 安全退出
```







