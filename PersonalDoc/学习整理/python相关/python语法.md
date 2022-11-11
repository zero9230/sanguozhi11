# 1 函数
格式： 
```python
def 函数名（参数列表）:
    函数体
```

# 2 json化
将json对象序列化为json
```python
json_str = json.dumps(configInfo, default=lambda obj: obj.__dict__, sort_keys=True)
```



# 3 参考资料
1.  [菜鸟教程——Python 3 教程](https://www.runoob.com/python3/python3-tutorial.html) 
2.  [菜鸟教程——Python 基础教程](https://www.runoob.com/python/python-tutorial.html) 

