import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by harrychen on 17/5/17.
 */
public class readCSV {
    public ArrayList<data> scan(ArrayList<data> finaldata){
        JButton open=new JButton();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("/Users/harrychen/Desktop"));
        chooser.setDialogTitle("Hello");
        FileNameExtensionFilter filter = new FileNameExtensionFilter( null,"csv");
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
            File selectedFile = chooser.getSelectedFile();
            System.out.println("You choose"+selectedFile.getAbsolutePath());
        }else {
            System.out.println( "No file chosen!" );
        }



        File file=new File(chooser.getSelectedFile().getAbsolutePath());
        try{
            Scanner input=new Scanner(file);

            while(input.hasNext()){  //read each line
                String data=input.next();
                String[] temp=data.split(","); //first split line by comma

                int parsed_time =Integer.parseInt(temp[0]); //parse time


                int parsed_type =Integer.parseInt(temp[1],16); //parse and convert from hex to decimal


                int parsed_version=Integer.parseInt(temp[2],16);


                int parsed_counter=Integer.parseInt(temp[3],16);


                int parsed_via=Integer.parseInt(temp[4],16);


                int parsed_address=Integer.parseInt(temp[5],16);

                //If Error in the data: Flag out
                int parsed_status=Integer.parseInt(temp[6],16);
                if (parsed_status!=0)
                {
                    System.out.println("Error found in this device: "+parsed_address);
                }


                //Split sensor dataobject into 10 pairs
                int[] sensor_data=new int[10];
                for (int i=0;i<10;i++){
                    String te=Character.toString(temp[7].charAt(i*2));
                    String te1=Character.toString(temp[7].charAt(i*2+1));
                    String te2=te+te1;
                    int te3=Integer.parseInt(te2,16);
//                    System.out.println(te2);
                    sensor_data[i]=te3;
//                    System.out.println(sensor_data[i]);
                }

                //create a Dataobject object and store the line information into that object
                data tempob=new data(parsed_time,parsed_type,parsed_version,parsed_counter,parsed_via,parsed_address,parsed_status,sensor_data);
                finaldata.add(tempob);

            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return finaldata;
    }
}
