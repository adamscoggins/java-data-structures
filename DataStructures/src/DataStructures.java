/**
 *
 * @author Adam Scoggins
 */
public class DataStructures {

    // returns true if array contains value
    // otherwise returns false
    public static boolean contains(String[] input, String value) {
        for (String element : input) {
            if (value.equals(element)) {
                return true;
            }
        }
        return false;
    }

    // returns true if array contains value
    // otherwise returns false
    public static boolean contains(int[] input, int value) {
        for (int element : input) {
            if (value == element) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        // test of Integer value
        //int test = 1000000000*3;
        //System.out.print(test);

        /* TEST of BinarySearchTree and AVLtree */
        int[] toInsert = {10, 18, 15, 29, 48, 67, 88};
        int[] toDelete = {10, 1, 2, 19, 58, 67, 8};
        int[] toSearch = {18, 15, 29, 48, 88};
        AVLtree a = new AVLtree();
        BinarySearchTree b = new BinarySearchTree();

        // insert numbers
        for (int number : toInsert) {
            a.insert(number);
            b.insert(number);
        }

        //delete numbers
        boolean error = false;
        for (int number : toDelete) {
            boolean aResult = a.delete(number);
            boolean bResult = b.delete(number);
            if (!(aResult == bResult) // if results are not equal
                    || (!aResult && contains(toInsert, number))) { // or it did not delete element but the element was inserted
                error = true;
                System.out.println("Cannot delete : " + number);

            }
        }

        //search numbers
        for (int number : toSearch) {
            if (!(a.search(number) == b.search(number)) // if results are not equal
                    || (!a.search(number))) { // or it did not find searched number
                error = true;
                System.out.println("Cannot find : " + number);
            }
        }

        /* TEST of LinkedList, Tree, HashTable  */
        String[] insert = {"Dave", "Tom", "Emily", "Sheldon", "Brian", "Jaime", "James",
            "Adam", "Ava", "Anna", "Katty", "Taylor", "Fred", "Luke"};
        String[] delete = {"James", "Joe", "Emily", "Howard", "Tonny", "Anna", "Luke"};
        String[] search = {"Dave", "Tom", "Sheldon", "Brian", "Jaime", "Adam", "Ava", "Katty", "Taylor", "Fred"};
        HashTable ht = new HashTable(6); // hash table of size 6
        Tree t = new Tree();
        LinkedList l = new LinkedList();

        //insert names
        for (String name : insert) {
            ht.insert(name);
            t.insert(name);
            l.insert(name);
        }

        //delete names
        for (String name : delete) {
            boolean htResult = ht.delete(name);
            boolean tResult = t.delete(name);
            boolean lResult = l.delete(name);
            if (!((htResult == tResult) && (htResult == lResult)) // if all the results are not equal
                    || (!tResult && contains(insert, name))) { // or it did not delete element but the element was inserted
                error = true;
                System.out.println("Cannot delete : " + name);
            }
        }

        //search names
        for (String name : search) {
            if (!((ht.search(name) == t.search(name)) && (ht.search(name) == l.search(name))) // if all the results are not equal
                    || (!t.search(name))) { // or it did not find searched name
                error = true;
                System.out.println("Cannot find : " + name);
            }
        }

        if (!error) {
            System.out.println("It Works");
        }
    }

}
