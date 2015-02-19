package damian.hashmap.information;

/**
 * Created by damianpart on 2/13/15.
 */
public class License {

    public long since;
    public long until;
    public String name;
    public String have;

    public void setName(String name){
        this.name=name;
    }

    public void setHave(String have){
        this.have=have;
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

    public boolean getHave(){
        return have.equalsIgnoreCase("yes");
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
