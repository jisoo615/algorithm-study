package week2.Programmers_입국심사;

class Solution {
    public long solution(int n, int[] times) {

        long max = 0;
        for (int time : times) {
            max = Math.max(max, time);
        }

        long left = 0;
        long right = n / times.length * max;

        while (left <= right) {

            long sum = 0;
            long mid = (left + right) / 2;

            for (long time : times) {
                sum += mid / time;
            }

            if (sum < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ++right;
    }
}