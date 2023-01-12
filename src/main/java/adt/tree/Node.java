package adt.tree;

public class Node<T> {
    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node( T value,Node<T> leftChild, Node<T> rightChild ){
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.value = value;
    }
    public Node( T value ){
        this(value, null, null);
        this.value = value;
    }

    public Node(){
        this(null);
    }

    public Node<T> addLeft( Node<T> leftChild){
        this.leftChild = leftChild;
        return leftChild;
    }
    public Node<T> addright( Node<T> rightChild ){
        this.rightChild = rightChild;
        return rightChild;
    }
    public void addValue(T value){
        this.value = value;
    }

    public Node<T> getLeftChild(){
        return  this.leftChild;
    }

    public Node<T> getRightChild(){
        return this.rightChild;
    }

    public T getValue(){
        return this.value;
    }

}
