/**
 * Created by harrychen on 17/5/17.
 */
public class dataobject {
    private int time;
    private int type;
    private int version;
    private int counter;
    private int via;
    private int address;
    private int status;
    private int[] sensordata;

    public dataobject(int time, int type, int version, int counter, int via, int address, int status, int[] sensordata){
        this.time=time;
        this.type=type;
        this.version=version;
        this.counter=counter;
        this.via=via;
        this.address=address;
        this.status=status;
        this.sensordata=sensordata;
    }

    public int getTime(){return time;}
    public int getType(){return type;}
    public int getVersion(){return version;}
    public int getCounter(){return counter;}
    public int getVia(){return via;}
    public int getAddress(){return address;}
    public int getStatus(){return status;}
    public int[] getSensordata(){return sensordata;}




}
