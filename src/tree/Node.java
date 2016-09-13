package tree;

/**
 * Created by Mike on 9/5/2016.
 */
class Node {
    int data;
    tree.Node leftChild;
    tree.Node rightChild;
    tree.Node parent;

    public Node(){

    }

    public Node(int data){
        this.data = data;
    }

}
