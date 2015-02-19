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
             License license1 = new License();
             if(license.length()>0){
                license1.setName(name);
                long since = license.getLong("since");
                long until = license.getLong("until");
                license1.setSince(since);
                license1.setUntil(until);
             }
             license1.setHaveLicense(license);
             Person person = new Person();
             person.setDni(dni);
             person.setName(name);
             if(license1.personHaveLicense()) {
                 licences.put(person, license1);
             }
             else{
                 licences.put(person, null);
             }
        }
    }

    public Person getPerson (int dni){
        Person p = new Person();
        p.setDni(dni);
        return p;
    }

    public String finalMessage (Person p){
        String textoDevuelto = "";
        if(licences.containsKey(p)){
          if(licences.get(p)!=null){
              textoDevuelto="la persona tiene registro para conducir de "+String.valueOf(licences.get(p).getSince())+" a "+String.valueOf(licences.get(p).getUntil())+"\n";
          }
          else{
              textoDevuelto="la persona no tiene registro de conducir\n";
          }
        }
        else{
          textoDevuelto ="la persona no existe\n";
        }
        return textoDevuelto;
    }

    public void startMessage (View view){
          int dni = 12345681;
          Person p = getPerson(dni);
          text.setText(finalMessage(p));

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
