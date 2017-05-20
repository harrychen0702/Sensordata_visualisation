import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by g2arc on 2017/5/17.
 */
public class Visualize extends JFrame{

private ArrayList<data> list;

private final int height;

private final int width;

private float ratio;

private final int baseHeight=600;

private final int baseWidth=800;

private final int baseRecordNum=1000;

private JTextField sensorID;

private JTextField addressID;

private ActionListener buttonListener;

private JButton b;

private JLabel totalErrorCount,totalRecordCount,timeLastSeen,maxValue,minValue,averageValue,sensorIDLabel,addressLabel;

private Canvas canvas;

    public Visualize(ArrayList<data> data)
    {
        setPreferredSize(new Dimension(300,300));
        setVisible(true);
        list=data;

        if (list.size()<=1000) {ratio=1;}
        //else ratio =size/base*0.8
        else  {ratio= (float) (list.size()/baseRecordNum*0.8);}
        //max ratio is 1.85
        if(ratio>1.85) {ratio= (float) 1.85;}
        height=Math.round(baseHeight*ratio);
        width=Math.round(baseWidth*ratio);
       // System.out.println("Height"+height+"ratio "+ratio);
        initUI();

    }

    private void updateInfo(int sensorID,int address)
    {
        String errorCountString="ErrorCount:";
        String recordCountString="RecordCount:";
        String minString="minValue";
        String maxString="maxValue:";
        String averageString="averageValue";
        String lastSeen="timeLastSeen:";

        if(address==-1)
        {
            errorCountString ="ErrorCount:"+ totalErrorCount().size();
            recordCountString ="RecordCount:"+ totalRecordCount().get(sensorID);
            minString="minValue:"+totalMinValue().get(sensorID);
            averageString="averageValue:"+totalAverageValue().get(sensorID);
            maxString="maxValue:"+totalMaxValue().get(sensorID);
            lastSeen="timeLastSeen:"+list.get(totalLastSeen().get(sensorID)).getDate();
        }
           else
        {
            errorCountString ="ErrorCount:"+ totalErrorCount().size();
            recordCountString ="RecordCount:"+ recordCount(address).get(sensorID);
            minString="minValue:"+minValue(address)[sensorID];
            averageString="averageValue:"+averageValue(address)[sensorID];
            maxString="maxValue:"+maxValue(address)[sensorID];
            try {
                lastSeen = "timeLastSeen:" + list.get(lastSeen(address)[sensorID]).getDate();
            }
            catch (Exception e)
            {
                lastSeen="timeLastSeen:" + "Not found";
            }
        }

        totalErrorCount.setText(errorCountString);
        totalRecordCount.setText(recordCountString);
        maxValue.setText(maxString);
        minValue.setText(minString);
        averageValue.setText(averageString);
        timeLastSeen.setText(lastSeen);

    }

    private void initButton()
    {
       b=new JButton("draw");
        b.setBounds((int)((width)-200*ratio),(int) ((height)-70*ratio),(int)(150*ratio),(int)(30*ratio));
        buttonListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sensor;
                int address;
                boolean inputCheck = false;
                try {
                    sensor = Integer.parseInt(sensorID.getText(), 16);
                    address = Integer.parseInt(addressID.getText(), 16);

                if (sensor >= 0 && sensor <= 9) {
                    inputCheck = true;
                }
            }
                catch(NumberFormatException exception)
                {
                    inputCheck=false;
                    sensor=-2;
                    address=-2;
                }

                if(inputCheck) {
                    updateInfo(sensor,address);
                 //   System.out.println("Sensor ID:" + sensorID.getText() + "  addressID:" + addressID.getText());
                 //   System.out.println("Integer sensor: " + sensor + " Integer address: " + address);
                }
                else
                {
                    infoBox("Invalid input! Please check your input","Invalid Input!");
                }
            }
        };
        b.addActionListener(buttonListener);
    }

    private void initTextField()
    {
        sensorID=new JTextField("Input Sensor ID");
        sensorID.setBounds((int)((width)-700*ratio),(int)((height)-70*ratio),(int)(150*ratio),(int)(30*ratio));
        addressID=new JTextField("Input Address");
        addressID.setBounds((int)((width)-400*ratio),(int)((height)-70*ratio),(int)(150*ratio),(int)(30*ratio));
    }
    private void initLabel()
    {
        int widthOfLabel=(int)(200*ratio);
        int heightOfLabel=(int)(40*ratio);
        totalErrorCount=new JLabel("ErrorCount:");
        totalErrorCount.setBounds((int)((width)-780*ratio),(int)((height)-600*ratio),widthOfLabel,heightOfLabel);
        totalRecordCount=new JLabel("RecordCount:");
        totalRecordCount.setBounds((int)((width)-780*ratio),(int)((height)-570*ratio),widthOfLabel,heightOfLabel);
        timeLastSeen=new JLabel("timeLastSeen:");
        timeLastSeen.setBounds((int)((width)-480*ratio),(int)((height)-600*ratio),widthOfLabel,heightOfLabel);
        averageValue=new JLabel("averageValue:");
        averageValue.setBounds((int)((width)-480*ratio),(int)((height)-570*ratio),widthOfLabel,heightOfLabel);
        maxValue=new JLabel("maxValue:");
        maxValue.setBounds((int)((width)-200*ratio),(int)((height)-600*ratio),widthOfLabel,heightOfLabel);
        minValue=new JLabel("minValue:");
        minValue.setBounds((int)((width)-200*ratio),(int)((height)-570*ratio),widthOfLabel,heightOfLabel);
        sensorIDLabel=new JLabel("sensorID");
        sensorIDLabel.setBounds((int)((width)-780*ratio),(int)((height)-70*ratio),widthOfLabel,heightOfLabel);
        addressLabel=new JLabel("addressID");
        addressLabel.setBounds((int)((width)-480*ratio),(int)((height)-70*ratio),widthOfLabel,heightOfLabel);
    }
    private void initUI() {

        //right bottom corner
        initButton();
        initTextField();
        initLabel();
        add(b);
        add(sensorID);
        add(addressID);
        add(totalErrorCount);
        add(totalRecordCount);
        add(timeLastSeen);
        add(maxValue);
        add(averageValue);
        add(minValue);
        add(sensorIDLabel);
        add(addressLabel);
        setTitle("Graph");
        setSize(width,height);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    public ArrayList<Integer> searchAddressResult(int address)
    {
        ArrayList<Integer> resultList=new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            if (list.get(i).getAddress()==address)
            {
                resultList.add(i);
            }
        }
        return resultList;
    }

    public ArrayList<Integer> searchSensorDataResult(int value,int sensor)
    {
        ArrayList<Integer> resultList=new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            if (list.get(i).getSensorData()[sensor]==value)
            {
                resultList.add(i);
            }
        }
        return resultList;
    }

    public ArrayList<Integer> totalLastSeen()
    {
        ArrayList<Integer> resultList=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            for(int j=list.size()-1;j>0;j--)
            {
                if(list.get(j).getSensorData()[i]!=0xff)
                {
                    resultList.add(j);
                    break;
                }
            }
        }
        return resultList;
    }

    public int[] lastSeen(int address)
    {
        int result[]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        for(int i=list.size()-1;i>0;i--)
        {
            for(int j=0;j<10;j++)
            {
                if(list.get(i).getAddress()==address&&result[j]==-1&&list.get(i).getSensorData()[j]!=0xff)
                {
                    result[j]=i;
                }
            }
        }
        return result;
    }

    //The size of the arrayList is the totalErrorCount.
    public ArrayList<Integer> totalErrorCount()
    {
        ArrayList<Integer> resultList=new ArrayList<>();

            for(int j=list.size()-1;j>0;j--) {
                if (list.get(j).getStatus() != 0) {
                    resultList.add(j);
                }
            }
        return resultList;
    }

    public ArrayList<Integer> addressList()
    {
        ArrayList<Integer> resultList=new ArrayList<>();

        for(int j=list.size()-1;j>0;j--) {
            if (!resultList.contains(list.get(j).getAddress())) {
                resultList.add(list.get(j).getAddress());
            }
        }
        return resultList;
    }

    public ArrayList<Integer> totalRecordCount()
    {
        ArrayList<Integer> resultList=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            int counter=0;
            for(int j=list.size()-1;j>0;j--)
            {
                if(list.get(j).getSensorData()[i]!=0xff)
                {
                    counter++;
                }
            }
            resultList.add(counter);
        }
        return resultList;
    }

    public ArrayList<Integer> recordCount(int address)
    {
        ArrayList<Integer> addressList=searchAddressResult(address);
        ArrayList<Integer> resultList=new ArrayList<Integer>()
        {{
            add(0);
            add(0);
            add(0);
            add(0);
            add(0);
            add(0);
            add(0);
            add(0);
            add(0);
            add(0);
        }};

        for(int i=0;i<addressList.size();i++)
        {
            int index=addressList.get(i);
            for(int j=0;j<10;j++)
            {
                if(list.get(index).getSensorData()[j]!=0xff)
                {
                    int oldValue=resultList.get(j);
                    int newValue=oldValue+1;
                    resultList.set(j,newValue);
                }
            }
        }
        return resultList;
    }


    public ArrayList<Integer> totalMaxValue()
    {
        ArrayList<Integer> resultList=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            int max=0;
            for(int j=list.size()-1;j>0;j--)
            {
                int temp=list.get(j).getSensorData()[i];
                if(temp!=0xff)
                {
                    if(temp>max)
                        max = temp;
                }
            }
            resultList.add(max);
        }
        return resultList;
    }

    public int[] maxValue(int address)
    {
        ArrayList<Integer> addressList=searchAddressResult(address);
        int temp=-1;
        int result[] ={temp, temp, temp, temp, temp, temp, temp, temp, temp, temp};
        for(int i=0;i<addressList.size();i++)
        {
            int index=addressList.get(i);
            for(int j=0;j<10;j++)
            {
                if(list.get(index).getSensorData()[j]!=0xff&&list.get(index).getSensorData()[j]>result[j])
                {
                    result[j]=list.get(index).getSensorData()[j];
                }
            }
        }
        return result;
    }

    public ArrayList<Float> totalAverageValue()
    {
        ArrayList<Float> resultList=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            float total=0;
            float counter=0;
            for(int j=list.size()-1;j>0;j--)
            {
                int temp=list.get(j).getSensorData()[i];
                if(temp!=0xff)
                {
                    counter++;
                    total+=temp;
                }
            }
            resultList.add(total/counter);
        }
        return resultList;
    }

    public float[] averageValue(int address)
    {
        ArrayList<Integer> addressList=searchAddressResult(address);
        float temp=0;
        float result[] ={temp, temp, temp, temp, temp, temp, temp, temp, temp, temp};
        float counter[]={temp, temp, temp, temp, temp, temp, temp, temp, temp, temp};
        for(int i=0;i<addressList.size();i++)
        {
            int index=addressList.get(i);

            for(int j=0;j<10;j++)
            {
                if(list.get(index).getSensorData()[j]!=0xff)
                {
                    result[j]+=list.get(index).getSensorData()[j];
                    counter[j]++;
                }
            }
        }
        for (int i=0;i<10;i++)
        {
            try {
                result[i] = result[i]/counter[i];
                if(result[i]==0&&counter[i]==0)
                {
                    result[i]=-1;
                }
            }
            catch (ArithmeticException e)
            {
                result[i]=-1;
            }
        }
        return result;
    }

    public ArrayList<Integer> totalMinValue()
    {
        ArrayList<Integer> resultList=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            int min=9999999;
            for(int j=list.size()-1;j>0;j--)
            {
                int temp=list.get(j).getSensorData()[i];
                if(temp!=0xff)
                {
                    if(temp<min)
                        min = temp;
                }
            }
            resultList.add(min);
        }
        return resultList;
    }
    public int[] minValue(int address)
    {
        ArrayList<Integer> addressList=searchAddressResult(address);
        int temp=999999;
        int result[] ={temp, temp, temp, temp, temp, temp, temp, temp, temp, temp};
        for(int i=0;i<addressList.size();i++)
        {
            int index=addressList.get(i);
            for(int j=0;j<10;j++)
            {
                if(list.get(index).getSensorData()[j]!=0xff&&list.get(index).getSensorData()[j]<result[j])
                {
                    result[j]=list.get(index).getSensorData()[j];
                }
            }
        }
        return result;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor( Color.BLACK );
        g.drawLine( 45, 100, 45, getHeight()-45 );
        g.drawLine( 45, getHeight()-45, getWidth()-45, getHeight()-45);
    }
//    public void paint( Graphics g ) {
////// Clear the entire canvas
////        g.setColor( Color.WHITE );
////        g.clearRect( 0, 0, getWidth(), getHeight() );
////// Draw a bit of background with a 10 pixel border
////        g.setColor( Color.LIGHT_GRAY );
////        g.fillRect( 10, 10, getWidth()-20, getHeight()-20 );
////// Draw some axis lines...
////
////// Draw some data! This is random, yours should be real :)
////        for( int x=30; x<getWidth()-20; x+=10 ) {
////            g.drawLine( x, getHeight()-20, x, getHeight()-20-
////                    (int)(Math.random()*getHeight()*0.8) );}
//        super.paint(g);
//        }
    }

