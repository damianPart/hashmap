package damian.hashmap.information;

/**
 * Created by damianpart on 2/13/15.
 */
public class License {

    public long since;
    public long until;
    public String name;

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

    @Override
    public boolean equals(Object o){
        if(o == null)                return false;
        if(!(o instanceof License)) return false;

        License other = (License) o;
        return this.name == other.name;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "";
    }

}
