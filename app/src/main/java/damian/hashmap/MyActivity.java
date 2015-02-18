package damian.hashmap;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import damian.hashmap.R;
import damian.hashmap.information.License;
import damian.hashmap.information.Person;


public class MyActivity extends ActionBarActivity {
    TextView texto;
    Map<Person, License> licences;
    Person Alejandro;
    Person Homero;
    Person Charlie;
    Person Peter;
    Person Fry;
    License l1;
    License l2;
    License l3;
    License l4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        texto =  (TextView) findViewById(R.id.texto);
        Alejandro = new Person();
        Alejandro.setDni(12345678);
        Alejandro.setName("Alejandro Gomez");
        Charlie = new Person();
        Charlie.setDni(12345679);
        Charlie.setName("Charlie Harper");
        Homero = new Person();
        Homero.setDni(12345680);
        Homero.setName("Homero Simpson");
        Peter = new Person();
        Peter.setDni(12345681);
        Peter.setName("Peter Griffin");
        Fry = new Person();
        Fry.setDni(12345682);
        Fry.setName("Philip Fry");
        l1 = new License();
        l1.setSince(20130201);
        l1.setUntil(20160201);
        l1.setName("Alejandro Gomez");
        l2 = new License();
        l2.setSince(20100302);
        l2.setUntil(20140302);
        l2.setName("Charlie Harper");
        l3 = new License();
        l3.setSince(20140403);
        l3.setUntil(20170403);
        l3.setName("Homero Simpson");
        l4 = new License();
        l4.setSince(0);
        l4.setUntil(0);
        l4.setName("Peter Griffin");
        licences = new HashMap();
        licences.put(Alejandro, l1);
        licences.put(Charlie, l2);
        licences.put(Homero, l3);
        licences.put(Peter, l4);
    }

    public String mensaje2 (Person p){
        String textoDevuelto = "";
        if(licences.containsKey(p)){
          if(licences.get(p).getSince()==licences.get(p).getUntil()){
              textoDevuelto=p.getName()+" no tiene registro de conducir\n";
          }
          else{
              textoDevuelto=p.getName()+" tiene registro para conducir de "+String.valueOf(licences.get(p).getSince())+" a "+String.valueOf(licences.get(p).getUntil());
          }
        }
        else{
          textoDevuelto =p.getName()+" no existe\n";
        }
        return textoDevuelto;
    }

    public void mensaje (View view){
          String textoFinal = "";
          for(Person p: licences.keySet()){
              Log.e("nombre", p.getName());
              textoFinal = textoFinal + mensaje2(p);
          }
          texto.setText(textoFinal);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
