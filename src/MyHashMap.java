import java.util.Arrays;

/**
 * 706:https://leetcode.com/problems/design-hashmap/description/
 * <p>
 * 实现一个简易版的HashMap，待优化
 */
public class MyHashMap {

    private int[] mInts;

    public MyHashMap() {
        mInts = new int[16];
        Arrays.fill(mInts, -1);
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        if (key >= mInts.length) {
            int[] ints = new int[key + 1];
            System.arraycopy(mInts, 0, ints, 0, mInts.length);
            Arrays.fill(ints, mInts.length, ints.length - 1, -1);
            mInts = ints;
        }
        mInts[key] = value;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        if (key >= 0 && key < mInts.length) {
            return mInts[key];
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        if (key >= 0 && key < mInts.length) {
            mInts[key] = -1;
        }
    }

}
