package com.example.lijo.medicinemagic;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    private Button search;
    private EditText med_name;
    ArrayList<String> med_List = new ArrayList<>();

    JSONArray suggestion = null;
    private static final String TAG_RESPONSE = "response";
    private static final String TAG_SUGGESTIONS = "suggestions";
    private static final String TAG_Med = "suggestion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        med_name = (EditText) findViewById(R.id.medicine);
        search = (Button) findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String med = med_name.getText().toString();
                new APICall().execute(new String[]{med});

            }
        });
    }

    class APICall extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String jsonStr = APIClient_1.executeGetCall(params[0]);
            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject status = jsonObj.getJSONObject(TAG_RESPONSE);
                    suggestion = status.getJSONArray(TAG_SUGGESTIONS);
                    for (int i = 0; i < suggestion.length(); i++) {
                        JSONObject c = suggestion.getJSONObject(i);
                        String name = c.getString(TAG_Med);
//                        Log.d("array >",name);
                        med_List.add(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                }
                return null;
            }

            @Override
            protected void onPostExecute (String result){
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putStringArrayListExtra("key", med_List);
                startActivity(intent);

            }
        }
}

