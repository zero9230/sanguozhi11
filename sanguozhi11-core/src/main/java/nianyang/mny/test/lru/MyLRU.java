package nianyang.mny.test.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyLRU<K,V> extends LinkedHashMap<K,V> {


    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return false;
    }

}
