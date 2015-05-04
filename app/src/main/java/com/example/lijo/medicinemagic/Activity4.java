package com.example.lijo.medicinemagic;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lijo on 3/5/15.
 */
public class Activity4 extends ListActivity {
    private ListView lv;
    private static final String TAG_BRAND = "brand";
    private static final String TAG_CATEGORY = "category";
    private static final String TAG_MANU = "manufacturer";
    private static final String TAG_UNIT_PRI = "unit_price";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent i = getIntent();
        ArrayList<HashMap<String, String>> arl = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("key");
        lv = (ListView) findViewById(android.R.id.list);

        ListAdapter adapter = new SimpleAdapter(
                Activity4.this, arl,
                R.layout.activity_item2, new String[] { TAG_BRAND, TAG_CATEGORY,
                TAG_MANU, TAG_UNIT_PRI }, new int[] { R.id.brand,
                R.id.category, R.id.manu, R.id.pr });
        setListAdapter(adapter);

    }

}
