import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class HashAlgorithm {

    public static void main(String[] args) {

    }

    /**
     * 205:https://leetcode.com/problems/isomorphic-strings/description/
     * <p>
     * 给定两个字符串，要求判断它们是否是同构。
     * 同构即是指字符串s可以被替换为字符串t。每个字符必须替换为另一个字符，一个字符只能替换为另一字符（不能多个），但是可以替换为自身。
     * </p>
     * Input: s = "egg", t = "add"
     * Output: true。e->a，g->d，一一对应。
     * <p>
     * Input: s = "foo", t = "bar"
     * Output: false。o即替换为了a又替换了r，所以不是同构。
     * </p>
     * Input: s = "ab", t = "aa"
     * Output: false。t中的a即替换为了a又替换为了b，所以也不是同构。
     */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> sHashMap = new HashMap<>();
        HashMap<Character, Character> tHashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            Character oldValue = sHashMap.put(sChar, tChar);
            if (oldValue != null && !oldValue.equals(tChar)) {
                return false;
            }

            oldValue = tHashMap.put(tChar, sChar);
            if (oldValue != null && !oldValue.equals(sChar)) {
                return false;
            }
        }

        return true;
    }

}
