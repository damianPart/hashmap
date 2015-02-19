package damian.hashmap;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import damian.hashmap.information.License;
import damian.hashmap.information.Person;


public class MyActivity extends ActionBarActivity {
    TextView text;
    Map<Person, License> licences;
    EditText input;
    private static final int BUFFER_SIZE = 8*1024;
    private static final String TAG = "JSON";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        text =  (TextView) findViewById(R.id.text);
        licences = new HashMap<Person, License>();
        input = (EditText) findViewById(R.id.input);
        try {
            generateLicenses();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String loadJSONFromAsset() {
        String json = "{}";
        InputStreamReader is = null;
        try {
            is = new InputStreamReader(getAssets().open("person.json"), "UTF-8") ;

            char[] buffer = new char[BUFFER_SIZE];

            StringBuilder stringBuilder = new StringBuilder();

            int r = is.read(buffer);
            while (r != -1){

                stringBuilder.append(buffer, 0, r);
                r = is.read(buffer);
            }

            json = new String(stringBuilder.toString());

        } catch (IOException ex) {
            Log.e(TAG, "Error reading questions file", ex);
            return "{}";
        }
        finally{
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing questions file", e);
                }
            }
        }
        return json;

    }

    public void generateLicenses() throws JSONException {

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
             Person person = new Person();
             person.setDni(dni);
             person.setName(name);
             if(license.length()>0) {
                 licences.put(person, license1);
             }
             else{
                 licences.put(person, null);
             }
        }
    }

    public Person getPerson (String id){
        int dni = Integer.parseInt(id);
        Person p = new Person();
        p.setDni(dni);
        return p;
    }

    public String finalMessage (Person p){
        String textoDevuelto = "";
        if(licences.containsKey(p)){
          if(licences.get(p)!=null){
              textoDevuelto="the person has license since "+String.valueOf(licences.get(p).getSince())+" to "+String.valueOf(licences.get(p).getUntil())+"\n";
          }
          else{
              textoDevuelto="the person hasn't license\n";
          }
        }
        else{
          textoDevuelto ="the person doesn't exist\n";
        }
        return textoDevuelto;
    }

    public void startMessage (View view){
          String dni = input.getText().toString();
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
