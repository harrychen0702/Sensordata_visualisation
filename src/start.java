import java.util.ArrayList;

/**
 * Created by harrychen on 17/5/17.
 */
public class start {
    public static void main(String[] args){
        readCSV read=new readCSV();
        ArrayList<dataobject> mydata=new ArrayList<dataobject>();
        read.scan(mydata);
        System.out.println(mydata.get(999).getSensordata()[0]);

    }
}
