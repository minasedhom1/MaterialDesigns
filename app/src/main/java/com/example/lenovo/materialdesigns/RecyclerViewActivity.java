package com.example.lenovo.materialdesigns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    JSONArray jsonArray;
    ArrayList<Item>items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        items=new ArrayList<>();
        StringRequest request=new StringRequest(Request.Method.GET,Urls.URL_GET_SELECTED_SUBCATEGORY_ITEM+"31",new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JsonElement root=new JsonParser().parse(response);
                    response = root.getAsString();
                    JSONObject jsonObject=new JSONObject(response);
                    jsonArray=jsonObject.getJSONArray("ItemsList");
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Item item=new Item();
                        item.setId(object.getString("ItemID"));
                        item.setName(Methods.htmlRender(object.getString("Name_En")));
                        item.setDescription(object.getString("Description_En"));
                        item.setPhone1(object.getString("Phone1"));
                        item.setPhone2(object.getString("Phone2"));
                        item.setPhone3(object.getString("Phone3"));
                        item.setPhone4(object.getString("Phone4"));
                        item.setPhone5(object.getString("Phone5"));
                        item.setMenu_url(Urls.URL_PDF_PATH +object.getString("PDF_URL"));
                        if(object.getString("Rate")!="null")
                        {item.setRate(Float.valueOf(object.getString("Rate"))); //get rate and round it implicitly
                            Log.d("rate",Float.valueOf(object.getString("Rate")).toString());}

                        item.setPhoto1(Urls.URL_IMG_PATH +object.getString("Photo1"));
                        item.setCategoryName(object.getString("CategoryName_En"));
                        item.setSubcategoryName(object.getString("SubcategoryName_En"));
                        item.setCategoryID(Variables.catID);
                        items.add(item);

                    }
                    mAdapter = new MyAdapter(items,getBaseContext());
                    mRecyclerView.setAdapter(mAdapter);
                  //  mAdapter.notifyDataSetChanged();
                    //  itemAdapter=new MyCustomListAdapter(getContext(),android.R.layout.simple_list_item_1,R.id.name2_tv,itemArrayList);
          /*          itemAdapter1=new MyItemAdapter(getContext(),android.R.layout.simple_list_item_1,itemArrayList);
                    ItemList.setAdapter(itemAdapter1);
                    itemAdapter1.setNotifyOnChange(true);*/
                }   catch (JSONException e ) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Methods.toast(error.toString(),getBaseContext());
            }
        });
        //           GetDataRequest.setUrl(Variables.catID);
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

        // specify an adapter (see also next example)

    }
}
