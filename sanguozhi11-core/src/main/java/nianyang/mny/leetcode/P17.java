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
 *
 * @author sikou
 * @date 2021/07/07
 */
public class P17 {
    public static void main(String[] args) {

        P17 p = new P17();

        String s = "89";
        List<String> strings = p.letterCombinations(s);
        System.out.println(strings);
    }

    public List<String> letterCombinations(String digits) {

        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() <= 0) {
            return res;
        }

        Map<Character, Character[]> map = getMap();
        char[] chars = digits.toCharArray();
        List<List<Character>> charList = new ArrayList<>();
        dfs(chars,0,0,new ArrayList<Character>(),charList,map);


        for (List<Character> characters : charList) {
            StringBuilder sb=new StringBuilder();
            for (Character character : characters) {
                sb.append(character);
            }
            res.add(sb.toString());
        }


        return res;
    }

    /**
     * @param chars
     * @param numIndex
     * @param letterIndex
     * @param seq         一种组合
     * @param charList    所有组合的结果
     * @param digitMap
     */
    public void dfs(char[] chars, int numIndex, int letterIndex, List<Character> seq, List<List<Character>> charList,
        Map<Character, Character[]> digitMap) {
        if (numIndex == chars.length - 1) {
            charList.add(new ArrayList<>());
        }
        //要下一个
        char diaNum = chars[numIndex];
        Character[] characters = digitMap.get(diaNum);
        char letter = characters[letterIndex];
        seq.add(letter);
        dfs(chars, numIndex + 1, letterIndex , seq, charList, digitMap);
        //不要下一个
        seq.remove(letter);
        dfs(chars, numIndex , letterIndex + 1, seq, charList, digitMap);

    }

    public Map<Character, Character[]> getMap() {
        Map<Character, Character[]> map = new HashMap<>();

        map.put('1', new Character[] {});
        map.put('2', new Character[] {'a', 'b', 'c'});
        map.put('3', new Character[] {'d', 'e', 'f'});
        map.put('4', new Character[] {'g', 'h', 'i'});
        map.put('5', new Character[] {'j', 'k', 'l'});
        map.put('6', new Character[] {'m', 'n', 'o'});
        map.put('7', new Character[] {'p', 'q', 'r', 's'});
        map.put('8', new Character[] {'t', 'u', 'v'});
        map.put('9', new Character[] {'w', 'x', 'y', 'z'});

        return map;
    }
}
