import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Adam Scoggins
 */
public class TreeNode
{

    private Map<Character, TreeNode> table;
    private boolean isWord;

    TreeNode() {
        this.table = new HashMap<>();
        isWord = false;
    }

    public boolean hasRecord(char c) {
        return this.table.containsKey(c);
    }

    public boolean isEmpty() {
        return this.table.isEmpty();
    }

    public TreeNode getRecord(char c) {
        return this.table.get(c);
    }

    public void addRecord(char c, TreeNode n) {
        this.table.put(c, n);
    }

    public void deleteRecord(char c) {
        this.table.remove(c);
    }

    public boolean isWord() {
        return isWord;
    }

    public void setIsWord(boolean isWord) {
        this.isWord = isWord;
    }

}
