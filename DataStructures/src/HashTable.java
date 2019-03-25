/**
 *
 * @author Adam Scoggins
 */
public class HashTable {

    private LinkedList[] data;

    HashTable(int size) {
        this.data = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            this.data[i] = new LinkedList();
        }
    }

    public void insert(String value) {
        int index = getIndex(value);
        this.data[index].insert(value);
    }

    public boolean delete(String value) {
        int index = getIndex(value);
        return data[index].delete(value);
    }

    public boolean search(String value) {
        int index = getIndex(value);
        return data[index].search(value);
    }

    private int hashFunction(String value) {
        int hash = 5;
        for (char c : value.toCharArray())
            hash = (hash * 41) + c;
        return hash;
    }

    private int getIndex(String value) {
        if (data == null) {
            return -1;
        }
        return Math.abs(hashFunction(value)) % data.length;
    }

}
