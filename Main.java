public class Main {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(5);
        tree.print();

        System.out.println("Contains 20: " + tree.contains(20));
        System.out.println("Contains 25: " + tree.contains(25));

        System.out.println("Remove 15: " + tree.remove(15));
        System.out.println("Remove 25: " + tree.remove(25));

        tree.print();
    }
}