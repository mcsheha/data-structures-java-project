/**
 * Created by Mike on 9/3/2016.
 */
public class Application {

    public static void main(String[] args){
        HashMap directory = new HashMap();
        directory.add("Bob Smith","555-235-1111 bsmith@somewhere.com");
        System.out.println(directory.lookUp("Bob Smith"));
    }
}
