package trees;

import java.lang.annotation.Target;
import java.util.Iterator;

class Node<V> {

    private int key;

    private V value;

    private Node<V> parent, left, right;

    /**
     * Simple constructor.
     *
     * @param key
     *     to set as key.
     * @param value
     *     to set as value.
     */
    public Node(int key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gives the key of the node.
     *
     * @return the key of the node.
     */
    public int getKey() {
        return key;
    }

    /**
     * Gives the value of the node.
     *
     * @return the value of the node
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets the value of the node.
     *
     * @param value
     *     to set.
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Gives the parent of this node.
     *
     * @return the parent.
     */
    public Node<V> getParent() {
        return parent;
    }

    /**
     * Gives the left child of this node.
     *
     * @return the left child.
     */
    public Node<V> getLeft() {
        return left;
    }

    /**
     * Gives the right child of this node.
     *
     * @return the right child.
     */
    public Node<V> getRight() {
        return right;
    }

    /**
     * Sets the parent of this node.
     *
     * @param parent
     *     to set
     */
    public void setParent(Node<V> parent) {
        this.parent = parent;
    }

    /**
     * Sets the left child of this node.
     *
     * @param left
     *     to set
     */
    public void setLeft(Node<V> left) {
        this.left = left;
    }

    /**
     * Sets the right child of this node.
     *
     * @param right
     *     to set
     */
    public void setRight(Node<V> right) {
        this.right = right;
    }
}

class BinaryTree<V> {

    private Node<V> root;

    /**
     * Simple constructor.
     */
    public BinaryTree() {
        this.root = null;
    }

    /**
     * Gives the root of the tree.
     *
     * @return the root of the tree
     */
    public Node<V> getRoot() {
        return root;
    }

    /**
     * Get the left child of a node.
     *
     * @param node
     *     the node to get the child of.
     * @return the child of the node iff hasLeft(node) is true.
     * @throws InvalidNodeException
     *     else
     */
    public Node<V> getLeft(Node<V> node) throws InvalidNodeException {
        return checkNode(node).getLeft();
    }

    /**
     * Get the right child of a node.
     *
     * @param node
     *     the node to get the child of.
     * @return the child of the node iff hasRight(node) is true.
     * @throws InvalidNodeException
     *     else
     */
    public Node<V> getRight(Node<V> node) throws InvalidNodeException {
        return checkNode(node).getRight();
    }

    /**
     * Check if a node has a left child.
     *
     * @param node
     *     node to check.
     * @return true iff node has a left child.
     * @throws InvalidNodeException
     *     if node is not valid (e.g. null)
     */
    public boolean hasLeft(Node<V> node) throws InvalidNodeException {
        return checkNode(node).getLeft() != null;
    }

    /**
     * Check if a node has a right child.
     *
     * @param node
     *     node to check.
     * @return true iff node has a right child.
     * @throws InvalidNodeException
     *     if node is not valid (e.g. null)
     */
    public boolean hasRight(Node<V> node) throws InvalidNodeException {
        return checkNode(node).getRight() != null;
    }

    /**
     * Adds a new entry to the tree.
     *
     * @param key
     *     to use.
     * @param value
     *     to add.
     */
    public void add(int key, V value) {
        if (root == null) {
            root = new Node<>(key, value);
        } else {
            this.add(root, new Node<>(key, value));
        }
    }

    /**
     * Adds the newNode recursively at the right node.
     *
     * @param node
     *     currently examining this node to see if the new node goes here
     * @param newNode
     *     to place somewhere.
     */
    private void add(Node<V> node, Node<V> newNode) {
        if (node.getKey() == newNode.getKey()) {
            node.setValue(newNode.getValue());
        } else {
            if (node.getKey() > newNode.getKey()) {
                if (node.getLeft() == null) {
                    newNode.setParent(node);
                    node.setLeft(newNode);
                } else {
                    this.add(node.getLeft(), newNode);
                }
            } else {
                if (node.getRight() == null) {
                    newNode.setParent(node);
                    node.setRight(newNode);
                } else {
                    this.add(node.getRight(), newNode);
                }
            }
        }
    }

    /**
     * Checks if the node is valid
     *
     * @param node
     *     node to check
     * @return node iff node is not null.
     * @throws InvalidNodeException
     *     else
     */
    private Node<V> checkNode(Node<V> node) throws InvalidNodeException {
        if (node == null) {
            throw new InvalidNodeException("Node was null");
        }
        return node;
    }
}

class InvalidNodeException extends Exception {

    private static final long serialVersionUID = -8010862139713793775L;

    public InvalidNodeException(String string) {
        super(string);
    }
}

/**
 * Iterates lazily over a binary tree in a depth-first manner. For instance a tree
 * with 8 as it's root and 4 and 10 as its children should be iterated as: 8 ->
 * 4 -> 10.
 */
class BinaryTreeIterator<V> implements Iterator<V> {

    Node<V> node;

    Node<V> prev;

    /**
     * Constructor.
     * Should reset on a new tree.
     *
     * @param tree
     *     takes the tree
     */
    public BinaryTreeIterator(BinaryTree<V> tree) {
        this.node = tree.getRoot();
    }

    /**
     * Indicates whether the iterator has more elements to return.
     *
     * @return True if there is a next element in the iterator, else false
     */
    @Override
    public boolean hasNext() {
        return node.getParent() != null ||
                (prev != node.getRight() && (node.getRight() != null || (node.getLeft() != null && prev == node.getLeft())));
    }

    boolean hasVisited = false;
    /**
     * Get the next element of the iterator and shift
     * iterator by one.
     *
     * @return current element value
     * @post iterator is moved to next element
     */
    @Override
    public V next() {
        if(!hasVisited) {
            hasVisited = true;
            return node.getValue();
        }
        if((node.getLeft() == null || prev == node.getLeft()) && (node.getRight() == null || prev == node.getRight())){
            // can't go down, then go up
            prev = node;
            node = node.getParent();

            if(node == null) return null;

            // came from the left and has right -> go right
            if(node.getLeft() == prev && node.getRight() != null){
                node = node.getRight();
                return goDownAndFind();
            }

            // came from the right or there is no right child -> go up, visit node
            prev = node;
            node = node.getParent();
            return node.getValue();


        }
        // going down
        return goDownAndFind();
    }

    private V goDownAndFind() {
        while(true) {
            if(node.getLeft() != null) node = node.getLeft();
            else if(node.getRight() != null) node = node.getRight();
            else {
                prev = null;
                return node.getValue(); // no children
            }
        }
    }

    /**
     * Skip a single element of the iterator.
     *
     * @post iterator is moved to next element.
     */
    @Override
    public void remove() {
        next();
    }
}

public class BinTree {
    public static void main(String[] args) {

        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.add(42, 42);
        tree.add(21, 21);
        tree.add(84, 84);
        Iterator<Integer> iterator = new BinaryTreeIterator<>(tree);
        int i = 10;
        for(var a = iterator.next(); iterator.hasNext() && i-- > 0;){
            System.out.println(a.intValue());
        }
    }

}
