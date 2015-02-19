package damian.hashmap.information;

import org.json.JSONObject;

/**
 * Created by damianpart on 2/13/15.
 */
public class License {

    private long since;
    private long until;
    private String name;
    private Boolean haveLicense;

    public void setName(String name){
        this.name=name;
    }

    public void setHaveLicense(JSONObject license){
        this.haveLicense=(license.length()>0);
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

    public boolean personHaveLicense(){
        return haveLicense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        License license = (License) o;

        if (!name.equals(license.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString(){
        return name;
    }

}
