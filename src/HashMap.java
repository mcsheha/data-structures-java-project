/**
 * Created by Mike on 8/30/2016.
 */
public class HashMap {
    private final static int TABLE_SIZE = 13;

    HashEntry[] table;  //creates the table variable (not yet initialized)

    HashMap(){
        table = new HashEntry[TABLE_SIZE];  //initializes the table
        for (int i = 0; i < TABLE_SIZE; i++)   //fills the table with null values
            table[i] = null;
    }

    public String lookUp (String name) {
        int firstSpace = name.indexOf(" ");
        String firstName = name.substring(0,firstSpace);
        String lastName = name.substring(firstSpace).trim();
        int key = Math.abs((firstName + lastName).toUpperCase().hashCode());
        int hash = key % TABLE_SIZE;
        while (table[hash] != null && table[hash].getKey() != key)
            hash = (hash +1) % TABLE_SIZE;
        if (table[hash] == null)
            return "Key not found";
        else
            return table[hash].getValue();
    }



//This is the real add function
    public void add (String name, String value){
        int firstSpace = name.indexOf(" ");
        String firstName = name.substring(0,firstSpace);
        String lastName = name.substring(firstSpace).trim();
        int key = Math.abs((firstName + lastName).toUpperCase().hashCode());
        int hash = (key % TABLE_SIZE);
        while (table[hash] != null && table[hash].getKey() != key)
            hash = (hash + 1) % TABLE_SIZE;
        table[hash] = new HashEntry(key, value);
    }


    public void testAdd(String name, String value){
        int firstSpace = name.indexOf(" ");
        String firstName = name.substring(0,firstSpace);
        String lastName = name.substring(firstSpace).trim();
        int key = Math.abs((firstName + lastName).toUpperCase().hashCode());
        int hash = (key % TABLE_SIZE);
        System.out.println(hash);
    }


    public void remove (String name){
        int firstSpace = name.indexOf(" ");
        String firstName = name.substring(0,firstSpace);
        String lastName = name.substring(firstSpace).trim();
        int key = Math.abs((firstName + lastName).toUpperCase().hashCode());
        int hash = (key % TABLE_SIZE);
        table[hash] = null;
    }

}


/**
 * For Hash function concat firstname and lastname, make upper, run it though hashCode() and modulo by table size
 */