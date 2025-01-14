package yandex.finaly;

public class Sprint5Task2 {

    public static void main(String[] args) {
        test();
        System.out.println("Test passed!");
    }

    public static Node remove(Node root, int key) {

        if (root == null) {
            return null;
        }

        if (key < root.getValue()) {
            root.setLeft(remove(root.getLeft(), key));
        } else if (key > root.getValue()) {
            root.setRight(remove(root.getRight(), key));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            }
            if (root.getRight() == null) {
                return root.getLeft();
            }

            Node maxLeft = findMaxLeft(root.getLeft());

            root.setValue(maxLeft.getValue());
            root.setLeft(remove(root.getLeft(), maxLeft.getValue()));
        }

        return root;
    }

    private static Node findMaxLeft(Node node) {

        if (node.getRight() == null) {
            return node;
        }
        return findMaxLeft(node.getRight());
    }

    private static void test() {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        assert newHead.getValue() == 5;
        assert newHead.getRight() == node5;
        assert newHead.getRight().getValue() == 8;
    }
}

class Node {
    private int value;
    private Node left;
    private Node right;

    Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

/*
-- ПРИНЦИП РАБОТЫ --
Сперва мы рекурсивно ищем искомый (удаляемый) элемент: если key меньше значения в текущей вершине — идём в левое поддерево,
если больше — в правое, иначе (ключ найден) переходим к удалению. Проверяем, если у вершины нет одного из потомков, то
возвращаем оставшийся, а если нет потомков совсем, возвращаем null. Если у вершины есть два потомка, то как описано в теории,
берём самую правую вершину из левого поддерева и заменяем значение удаляемой вершины на найденное. Таким образом сохраняется
структура дерева без создания новых узлов.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
В худшем случае если дерево будет в виде цепочки и его высота равна O(n), то двигаесь на каждом этапе на один шаг в сторону
при удаленнии последенго элемента, сложность будет равна O(n). Но принято считать, что сложнно равна O(h), где h — высота дерева.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм использует рекурсию, и глубина рекурсии будет равна не более высоты дерева - h. Больше памяти мы не используем,
поэтому общая пространственная сложность — O(h).
*/

//https://contest.yandex.ru/contest/24810/problems/B/