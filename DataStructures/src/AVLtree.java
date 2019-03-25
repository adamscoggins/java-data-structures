/**
 * The AVL tree is a self-balancing binary search tree, ensuring O (log n)
 * time complexity for actions that require searching.
 * @author Adam Scoggins
 */
public class AVLtree {

    private AVLnode root;

    AVLtree() {
        this.root = null;
    }

    public void insert(int value) {
        if (this.root == null) {
            this.root = new AVLnode(value, null);
            return;
        }

        AVLnode current = this.root;
        while (true) {
            if (current.getValue() < value) {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    current.setRight(new AVLnode(value, current));
                    break;
                }
            } else if (current.getValue() > value) {
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    current.setLeft(new AVLnode(value, current));
                    break;
                }
            } else {
                //the value you are inserting is already in tree
                return;
            }
        }
        rebalance(current);
    }

    public boolean search(int value) {
        AVLnode current = this.root;
        while (current != null) {
            if (current.getValue() > value) {
                current = current.getLeft();
            } else if (current.getValue() < value) {
                current = current.getRight();
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean delete(int value) {
        AVLnode current = this.root;
        while (current != null) {
            if (current.getValue() < value) {
                current = current.getRight();
            } else if (current.getValue() > value) {
                current = current.getLeft();
            } else {
                //found the node i want to delete
                deleteNode(current);
                return true;
            }
        }
        //value is not in tree
        return false;
    }

    public void print() {
        AVLnode[] nodes = {this.root};
        if (this.root == null) {
            return;
        }
        int height = this.root.getHeight();

        while (haveNodesToPrint(nodes)) {
            int offset = (int) Math.pow(2, height + 1);
            for (AVLnode node : nodes) {
                printSpace(offset);
                if (node != null) {
                    System.out.print(node.getValue());
                } else {
                    System.out.print("  ");
                }
                printSpace(offset - 2);

            }
            System.out.println();
            for (int i = 0; i < height; i++) {
                System.out.println();
            }
            AVLnode[] newNodes = new AVLnode[nodes.length * 2];
            int index = 0;
            for (AVLnode node : nodes) {
                if (node != null) {
                    newNodes[index] = node.getLeft();
                    index++;
                    newNodes[index] = node.getRight();
                    index++;
                } else {
                    newNodes[index] = null;
                    index++;
                    newNodes[index] = null;
                    index++;
                }
            }
            nodes = newNodes;
            height--;
        }
    }

    private void deleteNode(AVLnode node) {
        //case 1
        //  node is leaf
        if (node.getLeft() == null && node.getRight() == null) {
            if (node != this.root) {
                AVLnode parent = node.getParent();
                if (parent.getValue() < node.getValue()) {
                    parent.setRight(null);
                } else {
                    parent.setLeft(null);
                }
                rebalance(parent);
            } else {
                // if node have no parent it is root
                this.root = null;
            }
            return;
        }

        //case 2
        // node have one child
        if (node.getLeft() == null) {
            if (node != this.root) {
                AVLnode parent = node.getParent();
                if (parent.getValue() < node.getValue()) {
                    parent.setRight(node.getRight());
                } else {
                    parent.setLeft(node.getRight());
                }
            } else {
                // if node have no parent it is root
                node.getRight().setParent(null);
                this.root = node.getRight();
            }
            rebalance(node.getRight());
            return;
        } else if (node.getRight() == null) {
            AVLnode parent = node.getParent();
            if (node == this.root) {
                node.getLeft().setParent(null);
                this.root = node.getLeft();
            } else if (parent.getValue() < node.getValue()) {
                parent.setRight(node.getLeft());
            } else {
                parent.setLeft(node.getLeft());
            }
            rebalance(node.getLeft());
            return;
        }

        //case 3
        //  node have left and right child
        if (node.getLeft() != null && node.getRight() != null) {
            AVLnode successor = getBiggestNodeFromLeftSubtree(node);
            successor.setLeft(node.getLeft());
            successor.setRight(node.getRight());

            AVLnode parent = node.getParent();
            if (node != this.root) {
                if (parent.getValue() < node.getValue()) {
                    parent.setRight(successor);
                } else {
                    parent.setLeft(successor);
                }
            } else {
                // if node have no parent it is root
                successor.setParent(null);
                this.root = successor;
            }
            rebalance(successor);
        }
    }

    private AVLnode getBiggestNodeFromLeftSubtree(AVLnode node) {
        AVLnode result = node.getLeft();
        if (result.getRight() == null) {
            // no right child
            node.setLeft(result.getLeft());
            return result;
        }

        while (result.getRight() != null) {
            result = result.getRight();
        }

        result.getParent().setRight(result.getLeft()); // left child of result replace result

        return result;
    }

    private void rebalance(AVLnode node) {
        int balance = node.getBalance();

        if (balance == -2) { // height of left subtree is bigger by 2
            if (node.getLeft().getBalance() == 1) {
                node = LR(node);
            } else {
                node = RR(node);
            }
        } else if (balance == 2) { // height of right subtree is bigger by 2
            if (node.getRight().getBalance() == -1) {
                node = RL(node);
            } else {
                node = LL(node);
            }
        }

        if (node != this.root) {
            rebalance(node.getParent());
        }
    }

    private AVLnode LL(AVLnode node) {
        AVLnode right = node.getRight();
        AVLnode parent = node.getParent();
        node.setRight(right.getLeft());

        right.setLeft(node);

        if (node != this.root) {
            if (parent.getValue() < right.getValue()) {
                parent.setRight(right);
            } else {
                parent.setLeft(right);
            }
        } else {
            right.setParent(null);
            this.root = right;
        }

        return right;
    }

    private AVLnode RR(AVLnode node) {
        AVLnode left = node.getLeft();
        AVLnode parent = node.getParent();
        node.setLeft(left.getRight());

        left.setRight(node);

        if (node != this.root) {
            if (parent.getValue() < left.getValue()) {
                parent.setRight(left);
            } else {
                parent.setLeft(left);
            }
        } else {
            left.setParent(null);
            this.root = left;
        }

        return left;
    }

    private AVLnode RL(AVLnode node) {
        RR(node.getRight());
        return LL(node);
    }

    private AVLnode LR(AVLnode node) {
        LL(node.getLeft());
        return RR(node);
    }

    private boolean haveNodesToPrint(AVLnode[] nodes) {
        for (AVLnode node : nodes) {
            if (node != null) {
                return true;
            }
        }
        return false;
    }

    private void printSpace(int n) {
        while (n > 0) {
            System.out.print(" ");
            n--;
        }
    }
}
