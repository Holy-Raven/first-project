package yandex.contest.sprint5;

public class Task13 {

    public static int siftUp(int[] heap, int idx) {

        if (idx == 1) {
            return idx;
        }

        int parentIdx = idx / 2;

        if (heap[parentIdx] < heap[idx]) {
            int temp = heap[parentIdx];
            heap[parentIdx] = heap[idx];
            heap[idx] = temp;

            return siftUp(heap, parentIdx);
        }

        return idx;
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        assert siftUp(sample, 5) == 1;
    }

    public static void main(String[] args) {
        try {
            test();
            System.out.println("Тест успешно пройден!");
        } catch (AssertionError e) {
            System.err.println("Тест не пройден!");
        }
    }
}