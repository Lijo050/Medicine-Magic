
package com.example.lijo.medicinemagic;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.client.utils.URLEncodedUtils;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.util.EntityUtils;
        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by lijo on 28/4/15.
 */
public class APIClient_3 {

    static String res = null;

    public static String executeGetCall(String med_name){

        String URL = "http://www.truemd.in/api/medicine_alternatives/";
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", med_name));
        nameValuePairs.add(new BasicNameValuePair("key", "6eb81f065e81d9b0638c17bc2d6420"));
        nameValuePairs.add(new BasicNameValuePair("limit", "10"));
        HttpClient client = new DefaultHttpClient();
        String paramsString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
        HttpGet get = new HttpGet(URL + "?" + paramsString);
        HttpResponse response = null;
        HttpEntity httpEntity = null;
        try {
            response = client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpEntity = response.getEntity();
        try {
            res = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
        //JSONArray jsonArrayChanged = jsonObject.getJSONArray("suggestions");

    }

}
