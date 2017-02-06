package com.example.lenovo.materialdesigns;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import us.feras.mdv.MarkdownView;

public class SecondActivity extends AppCompatActivity {

    //private category myCategory = new category();
    private ListView customListView;
    private ArrayList<Category> categoryArrayList;
    private MyCustomListAdapter myAdapter;
    private ImageView icon_nav;

    JSONArray jsonArray;
    TextView htmlView;
    MarkdownView markdownView,markdownView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        customListView= (ListView) findViewById(R.id.listview_category);

        categoryArrayList=new ArrayList<>();
       /* categoryArrayList.add(new category("<font color=#ff0000 >Anchor</font> <font color=#ff9900>Stores</font>", "The Mega stores in city stars", R.mipmap.ic_launcher));
        categoryArrayList.add(new category("<font color=808000>Fashion </font><font color= #ff00ff>Accessories</font>", "Accessories for all ages and genders", R.mipmap.ic_launcher));*/



        //   htmlView= (TextView) findViewById(R.id.htmlView);

/*
markdownView= (MarkdownView) findViewById(R.id.markdownView);
*/
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/
            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
            //   CharSequence charSequence =response;
                //  response="{\"cat\""+": " + response +"}";
                    //response=response.trim();
                 //  response = response.substring(1,response.length()-1);
                   // response = response.replace("\\", "");
                  //  response.replaceAll()
                  // response= response.replaceAll("\\\\","");
                   //

                    try {
                      //  JSONObject jsonObject=new JSONObject(response);
                        // JSONStringer=new JSONStringer(response);

                        JsonElement root=new JsonParser().parse(response);

                        response = root.getAsString();  //not .toString
                        jsonArray = new JSONArray(response) ;

                         //jsonArray=jsonObject.getJSONArray("cat");
                       for (int i = 0; i < jsonArray.length(); i++)

                        {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Category myCategory=new Category();
                            myCategory.set_id(object.getInt("CategoryID"));
                            myCategory.set_name(htmlRender(object.getString("Name_En")));
                            myCategory.set_details(htmlRender(object.getString("Description_En")));
                            myCategory.set_icon(object.getString("Logo")); //filter here
                            categoryArrayList.add(myCategory);
                           // Toast.makeText(getApplicationContext(),object.toString(), Toast.LENGTH_SHORT).show();
                            //  mMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(object.getString("lat")), Double.valueOf(object.getString("lng")))));

                        }

               /*         myAdapter=new MyCustomListAdapter(SecondActivity.this,android.R.layout.simple_list_item_1,R.id.shopNameTextView,categoryArrayList);
                        customListView.setAdapter(myAdapter);*/

                      // String ss= htmlRender(String.valueOf(jsonArray.getJSONObject(0).getString("Description_En")));  // ********** ;)
                      //  htmlView.setText(ss, TextView.BufferType.EDITABLE);
                      //  markdownView.loadMarkdown(ss);
                      // ss=ss.replace("span","font");


                     //htmlView.setText(Html.fromHtml(ss),TextView.BufferType.SPANNABLE);

                      //  toast(String.valueOf(Html.fromHtml()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            fetchDataRequest.setUrl(Variables.URL_GET_CATEGORIES_GOODS);
            fetchDataRequest fetchRequest = new fetchDataRequest(responseListener);
           // fetchRequest.setShouldCache();
            RequestQueue queue = Volley.newRequestQueue(SecondActivity.this);
            queue.add(fetchRequest);
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/


    }









    /*----------------------------------------------------------------------------------------------------------------------------------------------------*/
    public class MyCustomListAdapter extends ArrayAdapter<Category> {

        class ViewHolder {
            Button explore;
            TextView shopName, shopDetails;
            ImageView categoryIcon;
        }
        public MyCustomListAdapter(Context context, int resource, int textViewResourceId, List<Category> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            try {

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                if(convertView==null)
                {convertView = inflater.inflate(R.layout.shop_item_list, parent, false);}

                final Category myCat=categoryArrayList.get(position); //final for each element

                holder = new ViewHolder();
                holder.shopName= (TextView) convertView.findViewById(R.id.shopNameTextView); //*******kont nasi el view.
                holder.shopDetails = (TextView) convertView.findViewById(R.id.shopDetailsTextview);
                holder. categoryIcon = (ImageView) convertView.findViewById(R.id.shopPic);
                holder.explore = (Button) convertView.findViewById(R.id.explore_btn);


                holder.shopName.setText(Html.fromHtml(myCat.get_name()), TextView.BufferType.SPANNABLE);
                holder. shopName.setTextSize(16f);
                holder.  shopDetails.setText(Html.fromHtml(myCat.get_details()));
                String logoURL="https://sa3ednymalladmin.azurewebsites.net/IMG/"+ myCat.get_icon();
                //Picasso.with(getContext()).load(logoURL).into(categoryIcon);
               //Picasso.with(getContext()).load(logoURL).get();
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                Picasso.with(getContext()).load(logoURL).transform(new RoundedCornersTransformation(20,0)).into(holder.categoryIcon);

/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

               // explore.setTextSize(13f);
                holder.explore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        toast(myCat.get_name());

                    }
                });

                return convertView;

            } catch (Exception e) {
                return null;
            }

        }



    }



    public void toast(String s)
    {
        Toast.makeText(SecondActivity.this,s,Toast.LENGTH_SHORT).show();
    }
    public String htmlRender(String ss)

    {
        ss=ss.replace("span","font");
        ss=ss.replace("style=\"color:","color=");
        ss=ss.replace(";\"","");

        return ss;
    }

/*----------------------------------------------------------------------------------------------------------------------------------------------------*/

   /* public ArrayList<category> getRequest (Context context)
    {
        final ArrayList<category> categoryArrayList2=new ArrayList<>();


        StringRequest stringRequest=new StringRequest(Request.Method.GET,Variables.URL_GET_CATEGORIES_GOODS,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {


                    JsonElement root=new JsonParser().parse(response);
                    response = root.getAsString();  //not .toString
                  JSONArray  jsonArray = new JSONArray(response) ;

                    for (int i = 0; i < jsonArray.length(); i++)

                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        category myCategory=new category();
                        myCategory.set_id(object.getInt("CategoryID"));
                        myCategory.set_name(htmlRender(object.getString("Name_En")));
                        myCategory.set_details(htmlRender(object.getString("Description_En")));
                        myCategory.set_icon(object.getString("Logo")); //filter here
                         categoryArrayList2.add(myCategory);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
       ,null);

         Volley.newRequestQueue(context).add(stringRequest);

        return  categoryArrayList2;
    }
*/
}


