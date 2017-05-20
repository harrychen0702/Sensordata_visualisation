import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//        ArrayList<data> list=new ArrayList<>();
//        data temp=new data(530622021,0x20,0x02,0x4d,0x6,0x52000a58,0x00,"111278fffffffffffff8");
//        list.add(temp);

//        System.out.println(list.get(0).getDate());
//        int temp2=list.get(0).getType();
//
//        System.out.println(Integer.toHexString(temp2));
//        Visualize v=new Visualize(list);
//v.paint();
//        System.out.println(list.get(0).getHour());
//        System.out.println(list.get(0).getMinute());
//        System.out.println(list.get(0).getSecond());
       // System.out.println(data);
test();
        //v.setVisible(true);

    }
    public static void test()
    {
        readCSV read=new readCSV();
        ArrayList<data> list=new ArrayList<>();
        read.scan(list);
        //System.out.println(list.get(0).getDate());
        //System.out.println(list.get(1).getDate());
        Visualize v=new Visualize(list);
        ArrayList<Integer> resultList=new ArrayList<>();
        resultList=v.totalMinValue();
        System.out.println(resultList);

        ArrayList<Integer> tmp=v.addressList();
        for (int j=0;j<tmp.size();j++) {
            int[] result = v.minValue(tmp.get(j));
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
        }
//        ArrayList<Integer> resultList=new ArrayList<>();
//        resultList=v.totalRecordCount();
//        System.out.println(resultList);
//        ArrayList<Float> ist=v.totalAverageValue();
//        System.out.println(ist);
//        resultList=v.searchAddressResult(0x52000a58);
//        System.out.println(resultList);
//        resultList=v.recordCount(0x52000a58);
//        System.out.println(resultList);
//        resultList=v.addressList();
//        System.out.println(resultList);
//        for(int i=0;i<resultList.size();i++)
//        {
//            ArrayList<Integer> temp=v.recordCount(resultList.get(i));
//            System.out.println(temp);
//        }
        //[656, 652, 648, 657, 657, 655, 317, 41, 255, 470]
//        ArrayList<Integer> resultList=new ArrayList<>();
//        resultList=v.searchAddressResult(0x52000a58);
//        System.out.println("The result is "+ resultList.size());
//        for(int i=0;i<resultList.size();i++)
//        {
//            System.out.println(i+": "+resultList.get(i));
//        }
//
//        resultList=v.searchSensorDataResult(0x3c,0);
//        System.out.println("The result is "+ resultList.size());
//        for(int i=0;i<resultList.size();i++)
//        {
//            System.out.println(i+": "+resultList.get(i));
//        }
       // ArrayList<Integer> resultList=new ArrayList<>();
      //  resultList=v.totalMaxValue();
      //  System.out.println(resultList);
      //  resultList=v.totalMinValue();
      //  System.out.println(resultList);
    }
}
