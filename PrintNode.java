public class PrintNode<T extends Comparable<T>> {
    Node<T> node;

    public PrintNode(Node<T> node) {
        this.node = node;
    }

    @Override
    public String toString() {
        String valueString = node.value != null ? node.value.toString() : "null";
        String colorString = node.color == Color.RED ? "R" : "B";
        return valueString + colorString + " ";
    }
}