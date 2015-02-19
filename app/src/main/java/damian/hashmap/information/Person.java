package damian.hashmap.information;

/**
 * Created by damianpart on 2/13/15.
 */
public class Person {

    private int dni;
    private String name;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (dni != person.dni) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return dni;
    }

    @Override
    public String toString(){
        return name;
    }

}
