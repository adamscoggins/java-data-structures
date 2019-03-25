/**
 *
 * @author Adam Scoggins
 */
public class AVLnode {

    private int value;
    private AVLnode left;
    private AVLnode right;
    private AVLnode parent;

    AVLnode(int value, AVLnode parent) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = parent;
    }

    private void setAsParent(AVLnode child) {
        if (child != null) {
            child.setParent(this);
        }
    }

    public int getBalance() {
        if (this.left != null && this.right != null) {
            return this.right.getHeight() - this.left.getHeight();
        } else if (this.left != null) {
            return 0 - this.left.getHeight();
        } else if (this.right != null) {
            return this.right.getHeight();
        } else {
            return 0;
        }
    }

    public int getHeight() {
        if (this.left != null && this.right != null) {
            return 1 + Math.max(this.left.getHeight(), this.right.getHeight());
        } else if (this.left != null) {
            return 1 + this.left.getHeight();
        } else if (this.right != null) {
            return 1 + this.right.getHeight();
        } else {
            return 1;
        }
    }

    public AVLnode getParent() {
        return parent;
    }

    public void setParent(AVLnode parent) {
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public AVLnode getLeft() {
        return left;
    }

    public void setLeft(AVLnode left) {
        this.setAsParent(left);
        this.left = left;
    }

    public AVLnode getRight() {
        return right;
    }

    public void setRight(AVLnode right) {
        this.setAsParent(right);
        this.right = right;
    }
}
