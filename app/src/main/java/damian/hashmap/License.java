package damian.hashmap;

/**
 * Created by damianpart on 2/13/15.
 */
public class License {

    public static long since;
    public static long until;
    public static String name;

    public void setName(String name){
        this.name=name;
    }
    public void setSince(long since){
        this.since=since;
    }
    public void setUntil(long until){
        this.until=until;
    }
    public String getName(){
        return name;
    }

    public Long getSince (){
        return since;
    }

    public Long getUntil (){
        return until;
    }

}
