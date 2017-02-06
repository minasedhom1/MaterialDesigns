package com.example.lenovo.materialdesigns;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.sackcentury.shinebuttonlib.ShineButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {
    JSONArray jsonArray;

    ListView ItemList;
    ArrayList<Item> itemArrayList = new ArrayList<>();
    private ArrayAdapter itemAdapter;
    static ArrayList<Item> favouriteList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ItemList= (ListView) findViewById(R.id.clickedItem_customList);
        itemArrayList = new ArrayList<>();
       Intent intent=getIntent();
      String id=  intent.getStringExtra("catID");
      fetchDataRequest.setUrl(Variables.URL_GET_SELECTED_CATEGORY_ITEMS + id );
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
                     JsonElement root=new JsonParser().parse(response);
                    response = root.getAsString();
                     JSONObject jsonObject=new JSONObject(response);
                     jsonArray=jsonObject.getJSONArray("ItemsList");

                      //not .toString
                   /* jsonArray = new JSONArray(response) ;*/


                    for (int i = 0; i < jsonArray.length(); i++)

                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Item item=new Item();
                        item.setName(htmlRender(object.getString("Name_En")));
                        item.setDescription(htmlRender(object.getString("Description_En")));
                        item.setPhone1(object.getString("Phone1"));
                        item.setPhoto1("https://sa3ednymalladmin.azurewebsites.net/IMG/"+object.getString("Photo1"));
                        item.setCategoryName(object.getString("CategoryName_En"));
                        itemArrayList.add(item);

                    }

                    itemAdapter=new MyCustomListAdapter(getBaseContext(),android.R.layout.simple_list_item_1,R.id.name2_tv,itemArrayList);
                    ItemList.setAdapter(itemAdapter);

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

        fetchDataRequest fetchRequest = new fetchDataRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(fetchRequest);


        ItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = (Item) itemAdapter.getItem(i);
                toast(item.getPhoto1());
            }
        });

    }


    public class MyCustomListAdapter extends ArrayAdapter<Item> {


        public MyCustomListAdapter(Context context, int resource, int textViewResourceId, List<Item> objects) {
            super(context, resource, textViewResourceId, objects);
        }
        class ViewHolder
        {
            Button website, call;
            ImageView image;
            TextView name, description;
            ShineButton shineButton;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder=new ViewHolder();
            try {

                if(convertView==null) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.custom_item, parent, false);


                    holder.name = (TextView) convertView.findViewById(R.id.name2_tv);
                    holder.description = (TextView) convertView.findViewById(R.id.promo2_tv);
                    holder.image = (ImageView) convertView.findViewById(R.id.item_icon);
                    holder.call = (Button) convertView.findViewById(R.id.call_btn);
                    holder.website = (Button) convertView.findViewById(R.id.website_btn);
                    holder.shineButton = (ShineButton) convertView.findViewById(R.id.like_btn);
                    convertView.setTag(holder);
                }
                else {holder = (ViewHolder) convertView.getTag();}
                final Item myItem = itemArrayList.get(position);

/*------------------------------------set values and action to views----------------------------------------*/

                holder.name.setText(Html.fromHtml(myItem.getName()));
                holder. description.setText(Html.fromHtml(myItem.getDescription()));

/*
                Picasso.with(ItemActivity.this).load(myItem.getPhoto1()).error(R.mipmap.ic_launcher).into(holder.image);  //             //new DownLoadImageTask(image).execute(imageUrl);
*/
                Picasso.Builder builder = new Picasso.Builder(ItemActivity.this);
                builder.listener(new Picasso.Listener()
                {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
                    {
                        exception.printStackTrace();
                    }
                });
                 builder.build().load(myItem.getPhoto1()).into(holder.image);
                holder.call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                        phoneIntent.setData(Uri.parse("tel:" + myItem.getPhone1()));
                        startActivity(phoneIntent);
                    }
                });



                holder.website.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(myItem.getSite()));
                        startActivity(i);*/
                       toast(myItem.getPhoto1());
                    }
                });



                /*-------------------like btn--------------------*/

                holder.shineButton.init(ItemActivity.this);


                //holder.shineButton.setChecked(itemArrayList.get(position).isLike());


                holder.shineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // like=myItem.isLike();

                      /*  if(!myItem.isLike())
                        {
                            toast(myItem.name);
                            itemArrayList.get(position).setLike(true);
                            favouriteList.add(myItem);
                            //  itemAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            toast("Removed from Favourite List!");
                            // myItem.setLike(false);
                            itemArrayList.get(position).setLike(false);
                            favouriteList.remove(myItem);
                        }
*/
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
        Toast.makeText(ItemActivity.this,s,Toast.LENGTH_SHORT).show();
    }
    public String htmlRender(String ss)

    {
        ss=ss.replace("span","font");
        ss=ss.replace("style=\"color:","color=");
        ss=ss.replace(";\"","");
        ss=ss.replaceAll("<p>","");
        ss=ss.replaceAll("</p>",""); //********
        return ss;
    }
}
