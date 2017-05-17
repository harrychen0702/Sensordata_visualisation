import java.util.ArrayList;

/**
 * Created by harrychen on 17/5/17.
 */
public class start {
    public static void main(String[] args){
//        ArrayList<dataobject> list=new ArrayList();
//        dataobject test1=new dataobject(530622021,0x20,0x2,0x6,0x6,0x52000a58,0x0,"111278fffffffffffff8");
//        System.out.println(test1.getTime());
//        System.out.println(test1.getType());
//        System.out.println(test1.getVersion());
        readCSV read=new readCSV();
        ArrayList<dataobject> mydata=new ArrayList<dataobject>();
        read.scan(mydata);
        System.out.println(mydata.get(999).getSensordata()[0]);
    }
}
