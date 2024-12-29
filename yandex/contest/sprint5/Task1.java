package yandex.contest.sprint5;

public class Task1 {

    public static void main(String[] args) {
        try {
            test();
            System.out.println("Тест успешно пройден!");
        } catch (AssertionError e) {
            System.err.println("Тест не пройден! Ожидалось значение 3"); // Выводим ошибку, если тест провален
        }
    }

    public static int treeSolution(Node head) {
        if (head == null) {
            return -1;
        }

        int leftMax = treeSolution(head.left);
        int rightMax = treeSolution(head.right);

        return Math.max(head.value, Math.max(leftMax, rightMax));
    }

    // <template>
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    // <template>

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        assert treeSolution(node4) == 3;
    }
}
