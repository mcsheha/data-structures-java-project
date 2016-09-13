package tree;

/**
 * Created by Mike on 9/5/2016.
 */
public class BinaryTree {
    Node root;

    public void add(int data){
        Node nodeToAdd = new Node(data);

        if(root == null)
            root = nodeToAdd;

        // if data < node, traverse left child, else traverse right child
        //until we get to a node that we can't travers... insert our new node

        traverseAndAddNode(root, nodeToAdd);



    }

    private void traverseAndAddNode(Node node, Node nodeToAdd){
        if(nodeToAdd.data < node.data){
            if(node.leftChild == null){
                nodeToAdd.parent = node;
                node.leftChild = nodeToAdd;
            }
            else {
                traverseAndAddNode(node.leftChild, nodeToAdd);
            }
        }
        else if (nodeToAdd.data > node.data){
            if(node.rightChild == null){
                nodeToAdd.parent = node;
                node.rightChild = nodeToAdd;
            }
            else {
                traverseAndAddNode(node.rightChild, nodeToAdd);
            }
        }

    }

    public void traverse (){
        if (root != null){
            Node nodeToTraverse = root;
            if (nodeToTraverse.leftChild == null && nodeToTraverse.rightChild == null){
                System.out.println(nodeToTraverse.data);
            }
            else{
                inOrderTraversal(nodeToTraverse);
                }
            }
        }

    public Node find (int val){
        if (root != null){
            return findNode(root, new Node (val));
        }
        return null;
    }

    private Node findNode (Node search, Node node){
        if (search == null)
            return null;

        if (search.data == node.data)
            return search;

        else{
            Node returnNode = findNode(search.leftChild, node);
            if (returnNode == null)
                returnNode = findNode (search.rightChild, node);
            return returnNode;
        }
    }


    private void inOrderTraversal (Node node){

        if (node.leftChild != null){
            inOrderTraversal(node.leftChild);
        }

        System.out.println(node.data);

        if (node.rightChild != null){
            inOrderTraversal(node.rightChild);
        }
    }

    public boolean delete(int val){
        //case 1: node has no children
        //case 2: node has one child
        //case 3: node has two children

        Node nodeToBeDeleted = find(val);

        if (nodeToBeDeleted != null){
            if (nodeToBeDeleted.leftChild == null && nodeToBeDeleted.rightChild == null)
                deleteCase1(nodeToBeDeleted);

            else if (nodeToBeDeleted.leftChild != null && nodeToBeDeleted.rightChild != null)
                deleteCase3(nodeToBeDeleted);

            else if (nodeToBeDeleted.leftChild != null)
                deleteCase2(nodeToBeDeleted);

            else if (nodeToBeDeleted.rightChild != null)
                deleteCase2(nodeToBeDeleted);
        }
        return false;
    }

    private void deleteCase2(Node nodeToBeDeleted) {
        if(nodeToBeDeleted.parent.leftChild == nodeToBeDeleted){
            if(nodeToBeDeleted.leftChild != null)
                nodeToBeDeleted.parent.leftChild = nodeToBeDeleted.leftChild;
            else if(nodeToBeDeleted.rightChild != null)
                nodeToBeDeleted.parent.leftChild = nodeToBeDeleted.rightChild;
        }
        else if (nodeToBeDeleted.parent.rightChild == nodeToBeDeleted){
            if(nodeToBeDeleted.leftChild != null)
                nodeToBeDeleted.parent.rightChild = nodeToBeDeleted.leftChild;
            else if(nodeToBeDeleted.rightChild != null)
                nodeToBeDeleted.parent.rightChild = nodeToBeDeleted.rightChild;
        }
    }

    private void deleteCase3(Node nodeToBeDeleted){
        Node minNode = minLeftTraversal(nodeToBeDeleted.rightChild);
        deleteCase2(minNode);

        minNode.parent = nodeToBeDeleted.parent;
        minNode.leftChild = nodeToBeDeleted.leftChild;
        minNode.rightChild = nodeToBeDeleted.rightChild;

        if(nodeToBeDeleted.parent == null)
            root = minNode;

        else {
            if (nodeToBeDeleted.parent.leftChild == nodeToBeDeleted)
                nodeToBeDeleted.parent.leftChild = minNode;

            else if (nodeToBeDeleted.parent.rightChild == nodeToBeDeleted)
                nodeToBeDeleted.parent.rightChild = minNode;
        }
    }

    private Node minLeftTraversal(Node node){
        if (node.leftChild == null)
            return node;
        return minLeftTraversal(node.leftChild);

    }


    private void deleteCase1(Node nodeToBeDeleted){
        // check if the node to be deleted is the left or right Child of a Parent of the node
        if (nodeToBeDeleted.parent.leftChild == nodeToBeDeleted){
            nodeToBeDeleted.parent.leftChild = null;
        }
        else if (nodeToBeDeleted.parent.rightChild == nodeToBeDeleted){
            nodeToBeDeleted.parent.rightChild = null;
        }
    }
}

