import java.util.ArrayList;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>> {
    private Node<T> root;

    public boolean insert(T value) {        // Вставка узла
        if (root == null) {
            root = new Node<>();
            root.value = value;
            root.color = Color.BLACK;
            return true;
        }
        return insertNode(root, value);

    }

    private boolean insertNode(Node<T> node, T value) {        // Рекурсивная вставка узла
        if (node.value.compareTo(value) == 0)
            return false;
        if (node.value.compareTo(value) > 0) {
            if (node.left != null) {
                boolean result = insertNode(node.left, value);
                node.left = rebalanced(node.left);
                return result;
            } else {
                node.left = new Node<>();
                node.left.color = Color.RED;
                node.left.value = value;
                return true;
            }
        } else {
            if (node.right != null) {
                boolean result = insertNode(node.right, value);
                node.right = rebalanced(node.right);
                return result;
            } else {
                node.right = new Node<>();
                node.right.color = Color.RED;
                node.right.value = value;
                return true;
            }
        }

    }

    public boolean contains(T value) {   // Поиск значения в дереве
        Node<T> currentNode = root;
        while (currentNode != null) {
            if (currentNode.value.equals(value))
                return true;
            if (currentNode.value.compareTo(value) > 0)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        return false;
    }

    public boolean remove(T value) {    // Удаление значения из дерева
        if (!contains(value))
            return false;
        Node<T> deleteNode = root;
        Node<T> prevNode = root;
        while (deleteNode != null) {
            if (deleteNode.value.compareTo(value) == 0) {
                Node<T> currentNode = deleteNode.right;
                if (currentNode == null) {
                    if (deleteNode == root) {
                        root = root.left;
                        root.color = Color.BLACK;
                        return true;
                    }
                    if (deleteNode.left == null) {
                        deleteNode = null;
                        return true;
                    }
                    if (prevNode.left == deleteNode)
                        prevNode.left = deleteNode.left;
                    else
                        prevNode.right = deleteNode.left;
                    return true;
                }
                while (currentNode.left != null)
                    currentNode = currentNode.left;
                deleteNode.value = currentNode.value;
                currentNode = null;
                return true;
            }
            if (prevNode != deleteNode) {
                if (prevNode.value.compareTo(value) > 0)
                    prevNode = prevNode.left;
                else
                    prevNode = prevNode.right;
            }
            if (deleteNode.value.compareTo(value) > 0)
                deleteNode = deleteNode.left;
            else
                deleteNode = deleteNode.right;
        }
        return false;
    }

    private Node<T> rebalanced(Node<T> node) { // Балансировка дерева
        Node<T> result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.right != null && result.right.color == Color.RED
                && (result.left == null || result.left.color == Color.BLACK)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED
                && result.left.left != null && result.left.left.color == Color.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED
                && result.right != null && result.right.color == Color.RED) {
                needRebalance = true;
                result = colorSwap(result);
            }
        } while (needRebalance);
        return result;
    }



    private Node<T> rightSwap(Node<T> node) {   // Правый поворот
        Node<T> result = node.right;
        node.right = result.left;
        result.left = node;
        node.color = Color.RED;
        result.color = Color.BLACK;
        return result;
    }

    private Node<T> leftSwap(Node<T> node) {    // Левый поворот
        Node<T> result = node.left;
        node.left = result.right;
        result.right = node;
        node.color = Color.RED;
        result.color = Color.BLACK;
        return result;
    }

    private Node<T> colorSwap(Node<T> node) {   // Перекрашивание узлов
        Node<T> result = node;
        result.color = Color.RED;
        result.left.color = Color.BLACK;
        result.right.color = Color.BLACK;
        return result;
    }

    public void print() {   // Вывод дерева на экран
        List<List<PrintNode<T>>> levels = new ArrayList<>();
        traverse(root, levels, 0);
        for (List<PrintNode<T>> level : levels) {
            for (PrintNode<T> node : level) {
                System.out.print(node);
            }
            System.out.println();
        }
    }

    private void traverse(Node<T> node, List<List<PrintNode<T>>> levels, int level) {   // Рекурсивный обход дерева

        if (node == null)
            return;
        if (levels.size() <= level) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(new PrintNode<>(node));
        traverse(node.left, levels, level + 1);
        traverse(node.right, levels, level + 1);
    }
}