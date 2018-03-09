public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> re = new ArrayList<>();
        re.add(0);
        if (n == 0) return re;
        re.add(1);
        if (n == 1) return re;
        int i = 2;
        int times = 2;
        while (i++ <= n) {
            int s = re.size();
            for (int j = s - 1; j >= 0; j--) {
                re.add(re.get(j) + times);
            }
            times *= 2;
        }
        return re;
    }
}