package yandex.contest.sprint2;

public class Task2 {

    public static void solution(Node<String> head) {
        // Начинаем с головы списка
        Node<String> current = head;

        // Проходим по всем узлам, пока не дойдём до конца списка
        while (current != null) {
            System.out.println(current.value); // Печатаем значение текущего узла
            current = current.next; // Переходим к следующему узлу
        }
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        solution(node0);
        /*
        Output is:
        node0
        node1
        node2
        node3
        */
    }
}

// <template>
class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}
// </template>