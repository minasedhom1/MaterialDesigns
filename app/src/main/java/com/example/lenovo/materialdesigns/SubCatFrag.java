package com.example.lenovo.materialdesigns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubCatFrag extends AppCompatActivity {
    JSONArray jsonArray;
   // RequestQueue queue;
ArrayList<Item>itemArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_cat_frag);

        fetchDataRequest.setUrl(Variables.URL_GET_SELECTED_SUBCATEGORY_ITEM+2);

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try { 
                    JsonElement root=new JsonParser().parse(response);
                    response = root.getAsString();  //not .toString
                    JSONObject jsonObject=new JSONObject(response);
                    jsonArray=jsonObject.getJSONArray("ItemsList");
             //      jsonArray = new JSONArray(response) ;
                    for (int i = 0; i < jsonArray.length(); i++)

                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Item item=new Item();
                       // item.setName(object.getInt("CategoryID"));
                        item.setName(object.getString("Name_En")); // X
                        item.setDescription(object.getString("Description_En")); // X
                       // item.setPhone1(object.getString("Logo"));
                        // listDataHeader.add(object.getString("Name_En"));
                        itemArrayList.add(item);
                        String s=itemArrayList.get(0).getName();
                        toas(s);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        fetchDataRequest fetchRequest = new fetchDataRequest(responseListener);
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(fetchRequest);
  //      toas(itemArrayList.get(0).getPhone1());
    }
    public void toas(String s){
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();}
}
