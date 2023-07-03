 public class Node<T extends Comparable<T>> {
        T value;
        Node<T> left;
        Node<T> right;
        Color color;


    public T getValue() {
        return value;
    }

    public void Value(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}