package entities.SplayTree;

public class NodeSplay<T extends Comparable<T>> {
    private NodeSplay parent;
    private NodeSplay leftChild;
    private NodeSplay rightChild;
    private T data;
    public NodeSplay() {}
    public NodeSplay(T data) {
        this.data = data;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    
    public NodeSplay(NodeSplay<T> NodeSplayToCopy) {
        this.data = NodeSplayToCopy.getData();
        this.parent = NodeSplayToCopy.getParent();
        this.leftChild = NodeSplayToCopy.getLeftChild();
        this.rightChild = NodeSplayToCopy.getRightChild();
    }

    
    public NodeSplay getParent() {
        return this.parent;
    }

    
    public NodeSplay getLeftChild() {
        return this.leftChild;
    }

    
    public NodeSplay getRightChild() {
        return this.rightChild;
    }

    
    public T getData() {
        return this.data;
    }

    
    public void setParent(NodeSplay parent) {
        this.parent = parent;
    }

    
    public void setLeftChild(NodeSplay leftChild) {
        this.leftChild = leftChild;
    }

    
    public void setRightChild(NodeSplay rightChild) {
        this.rightChild = rightChild;
    }

    
    public boolean hasLeftChild() {
        return (this.leftChild == null ? false : true);
    }

    
    public boolean hasRightChild() {
        return (this.rightChild == null ? false : true);
    }

    
    public boolean isLeaf() {
        return (!this.hasLeftChild() && !this.hasRightChild());
    }

    
    public void removeChild(T paCriterion) {
        if (this.getData().compareTo(paCriterion) > 0) {
            this.leftChild = null;
        } else if(this.getData().compareTo(paCriterion) < 0) {
            this.rightChild = null;
        }
    }

    
    public void replaceChild(T paCriterion, NodeSplay paReplaceNodeSplay) {
        if (this.getData().compareTo(paCriterion) > 0) {
            this.leftChild = paReplaceNodeSplay;
        } else if(this.getData().compareTo(paCriterion) < 0) {
            this.rightChild = paReplaceNodeSplay;
        }
    }

    
    public boolean hasOnlyOneChild() {
        if(this.leftChild == null && this.rightChild != null) {
            return true;
        } else if(this.leftChild != null && this.rightChild == null){
            return true;
        } else {
            return false;
        }
    }

    public boolean hasParent() {
        return (this.parent != null);
    }
}