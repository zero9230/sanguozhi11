package nianyang.mny.leetcode;

import java.util.HashMap;
import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author sikou
 * @date 2021/07/07
 */
public class P17 {
    public static void main(String[] args) {

        P17 p=new P17();

        String s="";
        List<String> strings = p.letterCombinations(s);
        System.out.println(strings);
    }

    public List<String> letterCombinations(String digits) {
        List<String> res=new ArrayList<>();
        Map<Character, Character[]> map = getMap();


        return  res;
    }

    public Map<Character,Character[]> getMap(){
        Map<Character,Character[]> map=new HashMap<>();

        map.put('1',new Character[]{});
        map.put('2',new Character[]{'a','b','c'});
        map.put('3',new Character[]{'d','e','f'});
        map.put('4',new Character[]{'g','h','i'});
        map.put('5',new Character[]{'j','k','l'});
        map.put('6',new Character[]{'m','n','o'});
        map.put('7',new Character[]{'p','q','r','s'});
        map.put('8',new Character[]{'t','u','v'});
        map.put('9',new Character[]{'w','x','y','z'});

        return map;
    }
}
