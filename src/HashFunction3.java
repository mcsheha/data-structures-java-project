/**
 * Created by Mike on 9/4/2016.
 */
public class HashFunction3 {

    PersonList[] theArray;  //creates the table variable (not yet initialized)

    private final static int TABLE_SIZE = 13;


    public int stringHashFunction(String wordToHash){
        int hashCode = Math.abs(wordToHash.replaceAll("\\s","").toUpperCase().hashCode());
        int bucket = hashCode % TABLE_SIZE;
        //System.out.println(wordToHash + " goes in bucket: " + bucket);
        return bucket;
    }

    HashFunction3(){
        theArray = new PersonList[TABLE_SIZE];
        for(int i = 0; i < TABLE_SIZE; i++)
            theArray[i] = new PersonList();
        }


    public void insert(Person newPerson){
        String personToHash = newPerson.name;
        int hashKey = stringHashFunction(personToHash);
        theArray[hashKey].insert(newPerson, hashKey);
    }


    public String lookUp(String personToFind){
        int hashKey = stringHashFunction(personToFind);
        String thePerson = theArray[hashKey].find(hashKey, personToFind);
        return thePerson;
    }


    public void add(String name, String contactInfo){
        Person newPerson = new Person(name, contactInfo);
        insert(newPerson);
    }


    public void displayTheArray(){
        for(int i = 0; i < TABLE_SIZE; i++){
            System.out.println("Bucket " + i + ": ");
            theArray[i].displayPersonList();
        }
    }
}


class Person {

    public String name;
    public String contactInfo;
    public int key;
    public Person next;

    Person(String name, String contactInfo){
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String toString() {
        return name + ": " + contactInfo;
    }
}


class PersonList {

    public Person firstPerson = null;

    public void insert(Person newPerson, int hashKey){
        Person previous = null;
        Person current = firstPerson;
        newPerson.key = hashKey;
        while(current != null && newPerson.key > current.key){
            previous = current;
            current = current.next;
        }
        if(previous == null)
            firstPerson = newPerson;
        else
            previous.next = newPerson;
        newPerson.next = current;
    }

    public void delete(Person personToDelete, int hashKey){

    }


    public void displayPersonList(){
        Person current = firstPerson;
        while (current != null){
            System.out.println(current);
            current = current.next;
        }
    }


    public String find(int hashKey, String personToFind){
        Person current = firstPerson;
        while(current != null && current.key <= hashKey){
            if(current.name.equals(personToFind))
                return current.toString();
            current = current.next;
        }
        return personToFind + " not found.";
    }


    public static void main(String[] args){

        HashFunction3 personHashTable = new HashFunction3();

        //Test Cases
        personHashTable.add("Bob Smith", "555-235-1111 bsmith@somewhere.com");
        personHashTable.add("Jane Williams","555-235-1112 jw@something.com");
        personHashTable.add("Mohammed al-Salam","555-235-1113 mas@someplace.com");
        personHashTable.add("Pat Jones","555-235-1114 pjones@homesweethome.com");
        personHashTable.add("Billy Kidd","555-235-1115 billy_the_kid@nowhere.com");
        personHashTable.add("H. Houdini","555-235-1116 houdini@noplace.com");
        personHashTable.add("Jack Jones","555-235-1117 jjones@hill.com");
        personHashTable.add("Jill Jones","555-235-1118 jillj@hill.com");
        personHashTable.add("John Doe","555-235-1119 jdoe@somedomain.com");
        personHashTable.add("Jane Doe","555-235-1120 jdoe@somedomain.com");

        System.out.println(personHashTable.lookUp("Pat Jones"));
        System.out.println(personHashTable.lookUp("Billy Kidd"));

        //personHashTable.delete("John Doe");

        personHashTable.add("Test Case","555-235-1121 Test_Case@testcase.com");
        personHashTable.add("Nadezhda Kanachekhovskaya","555-235-1122 dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru");
        personHashTable.add("Jo Wu","555-235-1123 wu@h.com");
        personHashTable.add("Millard Fillmore","555-235-1124 millard@theactualwhitehouse.us");
        personHashTable.add("Bob vanDyke","555-235-1125 vandyke@nodomain.com");
        personHashTable.add("Upside Down","555-235-1126 upsidedown@rightsideup.com");

        System.out.println(personHashTable.lookUp("Jack Jones"));
        System.out.println(personHashTable.lookUp("Nadezhda Kanachekhovskaya"));
        System.out.println(personHashTable.lookUp("Fake Name"));

        //personHashTable.displayTheArray();
    }
}