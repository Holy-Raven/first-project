package yandex.contest.sprint5;

public class Task12 {

    public static int siftDown(int[] heap, int idx) {

        int heapSize = heap.length;

        int left = 2 * idx;
        int right = 2 * idx + 1;

        if (left >= heapSize) {
            return idx;
        }

        int indexLargest = left;
        if (right < heapSize && heap[right] > heap[left]) {
            indexLargest = right;
        }

        if (heap[indexLargest] > heap[idx]) {
            int temp = heap[idx];
            heap[idx] = heap[indexLargest];
            heap[indexLargest] = temp;

            return siftDown(heap, indexLargest);
        }

        return idx;
    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        assert siftDown(sample, 2) == 5;
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