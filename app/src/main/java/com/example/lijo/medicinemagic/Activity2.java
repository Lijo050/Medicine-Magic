package com.example.lijo.medicinemagic;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by lijo on 2/5/15.
 */
public class Activity2 extends ListActivity{

    private ListView lv;
    private static final String TAG_RESPONSE = "response";
    private static final String TAG_MEDICINE = "medicine";
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
    private static final String TAG_CONSTITUENTS = "constituents";
    private static final String TAG_CONST_GENERIC_ID = "generic_id";
    private static final String TAG_CONST_ID = "id";
    private static final String TAG_CONST_Name = "name";
    private static final String TAG_CONST_QTY = "qty";
    private static final String TAG_CONST_Strength = "strength";

    ArrayList<String> json_List = new ArrayList<>();
    JSONArray constituents = null;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        Intent i = getIntent();
        final ArrayList<String> list =i.getStringArrayListExtra("key");
        lv = (ListView) findViewById(android.R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item2, R.id.med_name, list);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id){
                // Start your Activity according to the item just clicked.

                new APICall_2().execute(new String[]{list.get(position)});
            }
        });

    }
    class APICall_2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String jsonStr = APIClient_2.executeGetCall(params[0]);
//            Log.d("test >", jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject res = jsonObj.getJSONObject(TAG_RESPONSE);
                    JSONObject medicine = res.getJSONObject(TAG_MEDICINE);

                    String brand = medicine.getString(TAG_BRAND);
                    json_List.add(brand);

                    String category = medicine.getString(TAG_CATEGORY);
                    json_List.add(category);
                    String d_class = medicine.getString(TAG_CLASS);
                    json_List.add(d_class);
                    String generic_id = medicine.getString(TAG_GENERIC);
                    json_List.add(generic_id);
                    String id = medicine.getString(TAG_ID);
                    json_List.add(id);
                    String manufacturer = medicine.getString(TAG_MANU);
                    json_List.add(manufacturer);
                    String package_price = medicine.getString(TAG_PRICE);
                    json_List.add(package_price);
                    String package_qty = medicine.getString(TAG_QTY);
                    json_List.add(package_qty);
                    String package_type = medicine.getString(TAG_TYPE);
                    json_List.add(package_type);
                    String unit_price = medicine.getString(TAG_UNIT_PRI);
                    json_List.add(unit_price);
                    String unit_type = medicine.getString(TAG_UNIT_TYPE);
                    json_List.add(unit_type);
                    String unit_qty = medicine.getString(TAG_UNIT_QTY);
                    json_List.add(unit_qty);
                    constituents = res.getJSONArray(TAG_CONSTITUENTS);
                    JSONObject c = constituents.getJSONObject(0);
                    String const_gen_id = c.getString(TAG_CONST_GENERIC_ID);
                    json_List.add(const_gen_id);
                    String const_id = c.getString(TAG_CONST_ID);
                    json_List.add(const_id);
                    String const_name = c.getString(TAG_CONST_Name);
                    json_List.add(const_name);
                    String const_qty = c.getString(TAG_CONST_QTY);
                    json_List.add(const_qty);
                    String str = c.getString(TAG_CONST_Strength);
                    json_List.add(str);

                    /*for (int i = 0; i < json_List.size(); i++) {
                        Log.d("array >",json_List.get(i));

                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(Activity2.this, Activity3.class);
            intent.putStringArrayListExtra("keys", json_List);
            startActivity(intent);

        }
    }
}

