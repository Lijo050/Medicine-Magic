package com.example.lijo.medicinemagic;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lijo on 3/5/15.
 */
public class Activity3 extends ActionBarActivity {
    private EditText brand;
    private EditText cat;
    private EditText man;
    private EditText pp;
    private EditText pq;
    private EditText pt;
    private EditText up;
    private EditText uq;
    private EditText ut;
    private EditText cn;
    private EditText st;
    private Button search;

    JSONArray suggestion = null;
    ArrayList<HashMap<String, String>> contactList;
    private static final String TAG_RESPONSE = "response";
    private static final String TAG_MEDICINE = "medicine_alternatives";
    private static final String TAG_BRAND = "brand";
    private static final String TAG_CATEGORY = "category";
    private static final String TAG_CLASS = "d_class";
    private static final String TAG_GENERIC = "generic_id";
    private static final String TAG_ID = "id";
    private static final String TAG_MANU = "manufacturer";
    private static final String TAG_PRICE = "package_price";
    private static final String TAG_QTY = "package_qty";
    private static final String TAG_TYPE = "package_type";
    private static final String TAG_UNIT_PRI = "unit_price";
    private static final String TAG_UNIT_TYPE = "unit_type";
    private static final String TAG_UNIT_QTY = "unit_qty";

//    private EditText brand;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        contactList = new ArrayList<HashMap<String, String>>();
        Intent i = getIntent();
        final ArrayList<String> list =i.getStringArrayListExtra("keys");
        brand = (EditText) findViewById(R.id.br);
        brand.setText(list.get(0));
        cat = (EditText) findViewById(R.id.cat);
        cat.setText(list.get(1));
        man = (EditText) findViewById(R.id.manu);
        man.setText(list.get(5));
        pp = (EditText) findViewById(R.id.pp);
        pp.setText(list.get(6));
        pq = (EditText) findViewById(R.id.pq);
        pq.setText(list.get(7));

        up = (EditText) findViewById(R.id.up);
        up.setText(list.get(9));
        uq = (EditText) findViewById(R.id.uq);
        uq.setText(list.get(11));

        cn = (EditText) findViewById(R.id.cn);
        cn.setText(list.get(14));
        st = (EditText) findViewById(R.id.st);
        st.setText(list.get(16));


        search = (Button) findViewById(R.id.button);

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new APICall_3().execute(new String[]{list.get(0)});
            }
        });

    }
    class APICall_3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String jsonStr = APIClient_3.executeGetCall(params[0]);
//            Log.d("test >", jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject res = jsonObj.getJSONObject(TAG_RESPONSE);
                    suggestion = res.getJSONArray(TAG_MEDICINE);

                    for (int i = 0; i < suggestion.length(); i++) {
                        JSONObject c = suggestion.getJSONObject(i);
                        String br = c.getString(TAG_BRAND);
                        String cat = c.getString(TAG_CATEGORY);
                        String cl = c.getString(TAG_CLASS);
                        String gen_id = c.getString(TAG_GENERIC);
                        String id = c.getString(TAG_ID);
                        String man = c.getString(TAG_MANU);
                        String pr = c.getString(TAG_PRICE);
                        String qt = c.getString(TAG_QTY);
                        String ty = c.getString(TAG_TYPE);
                        String up = c.getString(TAG_UNIT_PRI);
                        String uq = c.getString(TAG_UNIT_QTY);
                        String ut = c.getString(TAG_UNIT_TYPE);
//                        Log.d("he ", br);
                        HashMap<String, String> contact = new HashMap<String, String>();
                        contact.put(TAG_BRAND,br);
                        contact.put(TAG_CATEGORY,cat);
                        contact.put(TAG_MANU,man);
                        contact.put(TAG_PRICE,pr);
                        contact.put(TAG_QTY,qt);
                        contact.put(TAG_UNIT_PRI,up);
                        contact.put(TAG_UNIT_QTY,uq);
                        contactList.add(contact);
                    }
//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(Activity3.this, Activity4.class);
            intent.putExtra("key", contactList);
            startActivity(intent);
            super.onPostExecute(result);

        }
    }
}
