/**
 * Created by Mike on 9/4/2016.
 */
public class HashFunction3 {

    PersonList[] theArray;  //creates the table variable (not yet initialized)

    private final static int TABLE_SIZE = 13;

    //Hashes a name to see which bucket it should go in
    public int stringHashFunction(String wordToHash){
        int hashCode = Math.abs(wordToHash.replaceAll("\\s","").toUpperCase().hashCode());
        int bucket = hashCode % TABLE_SIZE;
        //System.out.println(wordToHash + " goes in bucket: " + bucket);
        return bucket;
    }

    //Creates the main array and fills each bucket with a person list
    HashFunction3(){
        theArray = new PersonList[TABLE_SIZE];
        for(int i = 0; i < TABLE_SIZE; i++)
            theArray[i] = new PersonList();
        }

    //Takes previously created Person and inserts them to the Array
    public void insert(Person newPerson){
        String personToHash = newPerson.name;
        int hashKey = stringHashFunction(personToHash);
        theArray[hashKey].insert(newPerson, hashKey);
    }


    //Takes Person's name, sends Person object PersonList.remove
    public void remove(String personToDelete){
        int hashKey = stringHashFunction(personToDelete);
        Person personObjectToDelete = find(personToDelete);
        if (personObjectToDelete != null)
            theArray[hashKey].delete(personObjectToDelete);
        else
            System.out.println(personToDelete + " not found.");
    }


    //Takes the Person object returns the name and contact info
    public void lookUp(String personToFind){
        Person personObjectToFind = find(personToFind);
        if (personObjectToFind != null)
            System.out.println(personObjectToFind.name + ": " + personObjectToFind.contactInfo);
        else
            System.out.println(personToFind + " not found.");
    }


    //Takes Person's name, returns the person
    public Person find(String personToFind){
        int hashKey = stringHashFunction(personToFind);
        Person thePerson = theArray[hashKey].find(hashKey, personToFind);
        return thePerson;
    }

    //Creates new Person, adds them to the Array
    public void add(String name, String contactInfo){
        Person newPerson = new Person(name, contactInfo);
        insert(newPerson);
    }

    //Prints out a list of the buckets and contents
    public void displayTheArray(){
        for(int i = 0; i < TABLE_SIZE; i++){
            System.out.println("Bucket " + i + ": ");
            theArray[i].displayPersonList();
        }
    }
}

//Creates a Person object with: name, contact info, key(bucket), and next person
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

    public void delete(Person personToDelete){
        Person currentPerson = firstPerson;
        if(personToDelete == firstPerson){
            firstPerson = firstPerson.next;
        }
        else{
            Person previousPerson = firstPerson;
            while(currentPerson != personToDelete) {
                previousPerson = currentPerson;
                currentPerson = currentPerson.next;
            }
            previousPerson.next = currentPerson.next;
        }
    }




    public void displayPersonList(){
        Person current = firstPerson;
        while (current != null){
            System.out.println(current);
            current = current.next;
        }
    }


    public Person find(int hashKey, String personToFind){
        Person current = firstPerson;
        while(current != null && current.key <= hashKey){
            if(current.name.equals(personToFind))
                return current;
            current = current.next;
        }
        return null;
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
        personHashTable.lookUp("Pat Jones");
        personHashTable.lookUp("Billy Kidd");
        personHashTable.remove("John Doe");
        personHashTable.add("Test Case","555-235-1121 Test_Case@testcase.com");
        personHashTable.add("Nadezhda Kanachekhovskaya","555-235-1122 dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru");
        personHashTable.add("Jo Wu","555-235-1123 wu@h.com");
        personHashTable.add("Millard Fillmore","555-235-1124 millard@theactualwhitehouse.us");
        personHashTable.add("Bob vanDyke","555-235-1125 vandyke@nodomain.com");
        personHashTable.add("Upside Down","555-235-1126 upsidedown@rightsideup.com");
        personHashTable.lookUp("Jack Jones");
        personHashTable.lookUp("Nadezhda Kanachekhovskaya");
        personHashTable.remove("Jill Jones");
        personHashTable.remove("John Doe");
        personHashTable.lookUp("Jill Jones");
        personHashTable.lookUp("John Doe");
        //personHashTable.displayTheArray();

    }
}