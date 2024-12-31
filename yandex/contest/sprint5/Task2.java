package yandex.contest.sprint5;

public class Task2 {

    public static void main(String[] args) {

        try {
            test();
            System.out.println("Тест успешно пройден!");
        } catch (AssertionError e) {
            System.err.println("Тест не пройден! Ожидалось значение true"); // Выводим ошибку, если тест провален
        }
    }

    public static boolean treeSolution(Node head) {

        return check(head) != -1;
    }

    private static int check(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = check(node.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = check(node.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }


//    public static boolean treeSolution(Node head) {
//
//        if (head == null) {
//            return true;
//        }
//
//        int leftCount = 0;
//        int rightCount = 0;
//
//        while (true) {
//            if (head.left != null) {
//                leftCount++;
//                head.left = head.left.left;
//            } else {
//                break;
//            }
//        }
//
//        while (true) {
//            if (head.right != null) {
//                rightCount++;
//                head.right = head.right.right;
//            } else {
//                break;
//            }
//
//        }
//
//        return Math.abs(leftCount - rightCount) <= 1;
//    }

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
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        assert treeSolution(node5);
    }
}