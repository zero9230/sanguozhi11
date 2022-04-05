from functools import partial
def add_one(n):
    return n["data"]
    
class Processor(object):
    def run(self, sc, num):
        result = num.map(add_one)
        result.collect()
        print("success")


