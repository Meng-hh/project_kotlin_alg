package leetcode;

public class Day2 {

    public static void main(String[] args) {
        new Day2().lengthOfLongestSubstring("abcabcbb");
    }
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = i + 1;
            if (j == s.length()) {
                if (max < j - i) {
                    max = j - i;
                }
                break;
            }
            if (s.charAt(i) == s.charAt(j)) {
                if (max < j - i) {
                    max = j - i;
                }
                i++;
            } else {
                j++;
            }
        }
        return max;
    }
}
