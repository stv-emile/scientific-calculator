package adt.tree;

public class BinaryTree<T> {
    Node<T> root;

    public BinaryTree(Node<T> root){
        this.root = root;
    }

    public Node<T> getRoot(){
        return root;
    }



    public BinaryTree(){
        this.root = null;
    }



}
