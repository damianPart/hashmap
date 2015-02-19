package damian.hashmap;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import damian.hashmap.information.License;
import damian.hashmap.information.Person;


public class MyActivity extends ActionBarActivity {
    TextView text;
    Map<Person, License> licences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        text =  (TextView) findViewById(R.id.text);
        licences = new HashMap<Person, License>();
        try {
            generateLicense();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("person.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public void generateLicense() throws JSONException {

        JSONArray obj = new JSONArray(loadJSONFromAsset());
        for (int i=0; i<obj.length(); i++){
             JSONObject obj2 = obj.getJSONObject(i);
             String name = obj2.getString("name");
             int dni = obj2.getInt("dni");
             JSONObject license = obj2.getJSONObject("license");
             String have = license.getString("have");
             long since = 0;
             long until = 0;
             if (have.equalsIgnoreCase("yes")) {
                 since = license.getLong("since");
                 until = license.getLong("until");
             }
             Person person = new Person();
             person.setDni(dni);
             person.setName(name);
             License license1 = new License();
             license1.setName(name);
             license1.setSince(since);
             license1.setUntil(until);
             if (have.equalsIgnoreCase("yes")){
                 license1.setHave("yes");
             }
             else{
                 license1.setHave("no");
                 }
             licences.put(person, license1);
        }
    }

    public String mensaje2 (Person p){
        String textoDevuelto = "";
        if(licences.containsKey(p)){
          if(licences.get(p).getHave()){
              textoDevuelto=p.getName()+" tiene registro para conducir de "+String.valueOf(licences.get(p).getSince())+" a "+String.valueOf(licences.get(p).getUntil())+"\n";
          }
          else{
              textoDevuelto=p.getName()+" no tiene registro de conducir\n";
          }
        }
        else{
          textoDevuelto =p.getName()+" no existe\n";
        }
        return textoDevuelto;
    }

    public void mensaje (View view){
          String textoFinal = "";
          //int dni = 12345678;
          //Person p = getPerson(dni);
          for (Person p : licences.keySet()) {
              textoFinal = textoFinal + mensaje2(p);
          }
          text.setText(textoFinal);

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
