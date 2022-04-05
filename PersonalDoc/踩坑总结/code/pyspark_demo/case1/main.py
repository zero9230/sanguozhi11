from lib.processor import Processor
import pyspark
from pyspark import SparkContext
sc = SparkContext()
nums= sc.parallelize([{"data":1},{"data":2},{"data":3},{"data":4}])
Processor().run(sc, nums)