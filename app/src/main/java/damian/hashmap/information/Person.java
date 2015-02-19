package damian.hashmap.information;

/**
 * Created by damianpart on 2/13/15.
 */
public class Person {

    public int dni;
    public String name;

    public void setDni (int dni){
        this.dni = dni;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getName (){
        return name;
    }

    public int getDni (){
        return dni;
    }

    @Override
    public boolean equals(Object o){
        if(o == null)                return false;
        if(!(o instanceof Person)) return false;

        Person other = (Person) o;
        return this.dni == other.dni;
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
