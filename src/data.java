import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by g2arc on 2017/5/17.
 */
public class data {
    private int time;
    private int type;
    private int version;
    private int counter;
    private int via;
    private int address;
    private int status;
    private int[] sensorData;

    private String date;
    private int hour;
    private int minute;
    private int second;
    private boolean error;

    public  data(int time,int type,int version,int counter,int via,int address,int status,int[] sensorData)
    {
        this.time=time;
        this.type=type;
        this.version=version;
        this.counter=counter;
        this.via=via;
        this.address=address;
        this.status=status;
        this.sensorData=sensorData;

        if (status!=0)
        {
            this.error=true;
        }
        else
        {
            this.error=false;
        }

        date="01/01/2000";
        calculateDate();
    }


    public data()
    {

    }

    private void calculateDate()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        Calendar c= Calendar.getInstance();
        try
        {
            c.setTime(sdf.parse(date));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        //calculate second, minute etc
        int rest=time;
        second=rest%60;
        rest=rest/60;
        minute=rest%60;
        rest=rest/60;
        hour=rest%24;
        rest=rest/24;

        c.add(Calendar.DATE,rest);
        date=sdf.format(c.getTime());
        if (minute>=10) {
            date = date + " " + hour + ":" + minute + ":" + second;
        }
        else
        {
            date = date + " " + hour + ":" +"0"+ minute + ":" + second;
        }
    }



    public int getTime()
    {
        return time;
    }

    public int getVersion()
    {
        return version;
    }

    public int getAddress() {
        return address;
    }

    public int getCounter() {
        return counter;
    }

    public int[] getSensorData() {
        return sensorData;
    }

    public int getStatus() {
        return status;
    }

    public int getVia() {
        return via;
    }

    public int getType() {
        return type;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public String getDate() {
        return date;
    }

    public boolean isError() {
        return error;
    }
}
