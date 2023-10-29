package entities.SplayTree;

import entities.BaseTree;

import java.util.LinkedList;
import java.util.Queue;

public class SplayTree<T extends Comparable<T>> extends BaseTree<T> {
    private NodeSplay<T> root;

    public SplayTree() {
        super("Árvore Splay");
        this.root = null;
    }

    @Override
    public void clear() {
        root = null;
        clearRotations();
    }

    @Override
    public int getHeight() {

        if (root == null) {
            return -1;
        }

        Queue<NodeSplay<T>> queue = new LinkedList<>();
        queue.add(root);

        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;

            for (int i = 0; i < levelSize; i++) {
                NodeSplay<T> node = queue.poll();

                if (node.getLeftChild() != null) {
                    queue.add(node.getLeftChild());
                }
                if (node.getRightChild() != null) {
                    queue.add(node.getRightChild());
                }
            }
        }

        return height;
    }

    public NodeSplay<T> getRoot() {
        return this.root;
    }

    private NodeSplay<T> locateNode(T paCriterion) {
        NodeSplay<T> actual_node = null;

        if(root == null) {
            return actual_node;
        } else {
            actual_node = this.root;
            if(actual_node.getData().compareTo(paCriterion) == 0) {     //requested node is a root
                return actual_node;
            }
            while (actual_node != null) {
                if (actual_node.getData().compareTo(paCriterion) > 0) {     //go left - s1 > s2, positive number
                    if (actual_node.hasLeftChild()) {
                        actual_node = actual_node.getLeftChild();
                    } else {                                            //not found
                        return actual_node;
                    }
                } else if(actual_node.getData().compareTo(paCriterion) < 0){ //go right - s1 < s2, negative number
                    if (actual_node.hasRightChild()) {
                        actual_node = actual_node.getRightChild();
                    } else {                                            //not found
                        return actual_node;
                    }
                } else {                                                //found
                    return actual_node;
                }
            }
        }
        return actual_node;
    }

    @Override
    public boolean search(T paCriterion) {
        NodeSplay<T> located = this.locateNode(paCriterion);
        if(located == null) {
            return true;
        } else if(located.getData().compareTo(paCriterion) == 0) {
            splay(located);
            return true;
        } else {
            splay(located);
            return false;
        }
    }


    @Override
    public void insert(T paData) {

        NodeSplay<T> NodeSplay_toInsert = new NodeSplay<T>(paData);
        if(root == null) {
            root = NodeSplay_toInsert;
        } else {
            NodeSplay<T> actual_NodeSplay = this.root;
            while (actual_NodeSplay != null) {
                if (actual_NodeSplay.getData().compareTo(paData) > 0) {      //go left - s1 > s2, positive number  
                    if (actual_NodeSplay.hasLeftChild()) {
                        actual_NodeSplay = actual_NodeSplay.getLeftChild();
                    } else {
                        actual_NodeSplay.setLeftChild(NodeSplay_toInsert);
                        actual_NodeSplay.getLeftChild().setParent(actual_NodeSplay);

                        splay(actual_NodeSplay.getLeftChild());              //splay operation
                        return;
                    }
                } else if(actual_NodeSplay.getData().compareTo(paData) < 0){ //go right - s1 < s2, negative number  
                    if (actual_NodeSplay.hasRightChild()) {
                        actual_NodeSplay = actual_NodeSplay.getRightChild();
                    } else {
                        actual_NodeSplay.setRightChild(NodeSplay_toInsert);
                        actual_NodeSplay.getRightChild().setParent(actual_NodeSplay);
                        splay(actual_NodeSplay.getRightChild());             //splay operation
                        return;
                    }
                } else {                                                //if the tree contains the NodeSplay
                    return;
                }
            }
        }

    }

    private void splay(NodeSplay<T> paNodeSplay) {
        int type_of_child1 = -1;
        int type_of_child2 = -1;
        boolean NodeSplay_isRoot = false;

        while(!NodeSplay_isRoot) {
            type_of_child1 = this.type_of_child(paNodeSplay);

            switch(type_of_child1) {
                case 0:
                    NodeSplay_isRoot = true;
                    break;
                case 1:
                    type_of_child2 = this.type_of_child(paNodeSplay.getParent());
                    switch(type_of_child2) {
                        case 0:
                            paNodeSplay = this.right_rotation(paNodeSplay.getParent());
                            NodeSplay_isRoot = true;
                            break;
                        case 1:
                            totalDoubleRotation++;
                            paNodeSplay = this.right_rotation(paNodeSplay.getParent().getParent());
                            paNodeSplay = this.right_rotation(paNodeSplay);
                            break;
                        case 2:
                            paNodeSplay = this.right_rotation(paNodeSplay.getParent());
                            paNodeSplay = this.left_rotation(paNodeSplay.getParent());
                            break;
                    }
                    break;
                case 2:
                    type_of_child2 = this.type_of_child(paNodeSplay.getParent());

                    switch(type_of_child2) {
                        case 0:
                            paNodeSplay = this.left_rotation(paNodeSplay.getParent());
                            NodeSplay_isRoot = true;
                            break;
                        case 1:
                            paNodeSplay = this.left_rotation(paNodeSplay.getParent());
                            paNodeSplay = this.right_rotation(paNodeSplay.getParent());
                            break;

                        case 2:
                            totalDoubleRotation++;
                            paNodeSplay = this.left_rotation(paNodeSplay.getParent().getParent());
                            paNodeSplay = this.left_rotation(paNodeSplay);
                            break;
                    }
                    break;
                default:
                    NodeSplay_isRoot = true;
                    break;
            }
        }
        this.root = paNodeSplay;
    }

    private NodeSplay<T> left_rotation(NodeSplay<T> paNodeSplay) {
        totalRotations++;

        NodeSplay<T> parent_of_NodeSplay = paNodeSplay.getParent();      //parent of requested NodeSplay
        NodeSplay<T> new_NodeSplay = paNodeSplay.getRightChild();        //new root of this subtree

        new_NodeSplay.setParent(parent_of_NodeSplay);
        if(parent_of_NodeSplay != null) {
            parent_of_NodeSplay.replaceChild(paNodeSplay.getData(), new_NodeSplay);
        }

        paNodeSplay.setParent(new_NodeSplay);
        paNodeSplay.setRightChild(new_NodeSplay.getLeftChild());
        if(new_NodeSplay.hasLeftChild()) {
            paNodeSplay.getRightChild().setParent(paNodeSplay);
        }
        new_NodeSplay.setLeftChild(paNodeSplay);

        return new_NodeSplay;
    }

    private NodeSplay<T> right_rotation(NodeSplay<T> paNodeSplay) {

        totalRotations++;

        NodeSplay<T> parent_of_NodeSplay = paNodeSplay.getParent();
        NodeSplay<T> new_NodeSplay = paNodeSplay.getLeftChild();

        new_NodeSplay.setParent(parent_of_NodeSplay);

        if(parent_of_NodeSplay != null) {
            parent_of_NodeSplay.replaceChild(paNodeSplay.getData(), new_NodeSplay);
        }

        paNodeSplay.setParent(new_NodeSplay);
        paNodeSplay.setLeftChild(new_NodeSplay.getRightChild());

        if(new_NodeSplay.hasRightChild()) {
            paNodeSplay.getLeftChild().setParent(paNodeSplay);
        }
        new_NodeSplay.setRightChild(paNodeSplay);

        return new_NodeSplay;
    }

    private int type_of_child(NodeSplay<T> paNodeSplay) {
        if(!paNodeSplay.hasParent()) {
            return 0;                   //NodeSplay hasn't gor parent   
        } else {
            NodeSplay<T> parent = paNodeSplay.getParent();
            if (parent.hasLeftChild() && parent.getLeftChild().getData().compareTo(paNodeSplay.getData()) == 0) {
                return 1;               //NodeSplay is left son
            } else {
                return 2;               //NodeSplay is right son
            }
        }
    }
}