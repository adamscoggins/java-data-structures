/**
 *
 * @author Adam Scoggins
 */
public class Tree
{

    private TreeNode root;

    Tree() {
        this.root = new TreeNode();
    }

    public void insert(String value) {
        char[] input = value.toCharArray();
        TreeNode n = root; // "n" represents some prefix in "value"
        for (char c : input) {
            if (!n.hasRecord(c)) {
                n.addRecord(c, new TreeNode());
            }
            n = n.getRecord(c);
        }
        n.setIsWord(true);
    }

    public boolean search(String value) {
        char[] input = value.toCharArray();
        TreeNode n = root;
        for (int i = 0; i < input.length; i++) {
            if (!n.hasRecord(input[i])) {
                return false;
            }
            n = n.getRecord(input[i]);
        }
        return n.isWord();
    }

    public boolean delete(String value) {
        char[] input = value.toCharArray();
        TreeNode n = root;
        TreeNode[] path = new TreeNode[input.length];
        int pathLength = 0;
        for (int i = 0; i < input.length; i++) {
            if (!n.hasRecord(input[i])) {
                //value is not in this tree
                return false;
            }
            n = n.getRecord(input[i]);
            path[pathLength] = n;
            pathLength++;
        }
        n.setIsWord(false);

        //delete nodes that are no longer needed
        int inputIndex = input.length - 1; // from the end to 0
        //path[pathLength - 1] == n
        for (int i = pathLength - 2; i >= 0; i--) {
            if (n.isEmpty() && (!n.isWord())) {
                //node can be deleted
                n = path[i];
                n.deleteRecord(input[inputIndex]);
                inputIndex--;
            } else {
                //node cannot be deleted
                break;
            }
        }
        return true;
    }
}
