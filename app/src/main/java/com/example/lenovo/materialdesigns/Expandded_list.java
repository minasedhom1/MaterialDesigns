package com.example.lenovo.materialdesigns;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class Expandded_list extends AppCompatActivity {
    private ArrayList<Category> categoryArrayList;
    CustomExpandedListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    JSONArray jsonArray;
    RequestQueue queue;
    static  int sub_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandded_list);

        expListView= (ExpandableListView) findViewById(R.id.expandded_list);
        categoryArrayList = new ArrayList<>();
        /*----------------------------------------------------------------------------------------------------------------------------------------------------*/
        fetchDataRequest.setUrl(Variables.URL_GET_CATEGORIES_GOODS);

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JsonElement root=new JsonParser().parse(response);
                    response = root.getAsString();  //not .toString
                    jsonArray = new JSONArray(response) ;
                    for (int i = 0; i < jsonArray.length(); i++)

                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Category myCategory=new Category();
                        myCategory.set_id(object.getInt("CategoryID"));
                        myCategory.set_name(object.getString("Name_En")); // X
                        myCategory.set_details(object.getString("Description_En")); // X
                        myCategory.set_icon(object.getString("Logo"));
                        myCategory.setHas_sub(object.getBoolean("AllowSubcategory"));//filter here
                        if(myCategory.isHas_sub())
                        {myCategory.setSub_array(getSubs(object.getInt("CategoryID")));}
                        categoryArrayList.add(myCategory);

                        // listDataHeader.add(object.getString("Name_En"));
                    }

                    listAdapter = new CustomExpandedListAdapter();
                    expListView.setAdapter(listAdapter);
                    //myAdapter.setNotifyOnChange(true);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        fetchDataRequest fetchRequest = new fetchDataRequest(responseListener);
        queue= Volley.newRequestQueue(this);
        queue.add(fetchRequest);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Subcategory subcategory= (Subcategory) listAdapter.getChild(groupPosition,childPosition);
                sub_id=  subcategory.getCat_ID();
                Toast.makeText(Expandded_list.this,subcategory.getSubCat_name(),Toast.LENGTH_SHORT).show();
               startActivity(new Intent(Expandded_list.this,SubCatFrag.class));
                /*&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*/

                return false;
            }

        });
    }







   public class CustomExpandedListAdapter extends BaseExpandableListAdapter
   {
       class ViewHolder {
           Button explore;
           TextView shopName, shopDetails,subCat;
           ImageView categoryIcon;
       }

       @Override
       public Object getChild(int groupPosition, int childPosititon) {
           return   categoryArrayList.get(groupPosition).getSub_array().get(childPosititon);
       }

       @Override
       public long getChildId(int groupPosition, int childPosition) {
           return childPosition;
       }

       @Override
       public View getChildView(int groupPosition, final int childPosition,
                                boolean isLastChild, View convertView, ViewGroup parent) {

         //  ViewHolder holder = new ViewHolder();
           try {

               Category myCategory=categoryArrayList.get(groupPosition);

                   LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                   convertView = inflater.inflate(R.layout.expanddedlist_child, parent, false);
               ArrayList<Subcategory> subcategories =myCategory.getSub_array();
              String sub_name= subcategories.get(childPosition).getSubCat_name();
               TextView subCat= (TextView) convertView.findViewById(R.id.lblListItem);
               ImageView child_icon= (ImageView) convertView.findViewById(R.id.child_icon);
               subCat.setText(Html.fromHtml(sub_name) );
               Picasso.with(Expandded_list.this).load(subcategories.get(childPosition).getSubCat_icon_url()).
                       transform(new RoundedCornersTransformation(20,0)).fit().into(child_icon);

/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

               return convertView;

           } catch (Exception e) {
               return null;
           }
       }

       @Override
       public int getChildrenCount(int groupPosition) {
           return categoryArrayList.get(groupPosition).getSub_array().size();

       }

       @Override
       public Object getGroup(int groupPosition) {
       return categoryArrayList.get(groupPosition);
       }

       @Override
       public int getGroupCount() {
     return categoryArrayList.size();       }

       @Override
       public long getGroupId(int groupPosition) {
           return groupPosition;
       }

       @Override
       public View getGroupView(int groupPosition, boolean isExpanded,
                                View convertView, ViewGroup parent) {

           ViewHolder holder = new ViewHolder();
           try {



               if(convertView==null)
               {LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                   convertView = inflater.inflate(R.layout.shop_item_list, parent, false);

                   holder.shopName= (TextView) convertView.findViewById(R.id.shopNameTextView); //*******kont nasi el view.
                   holder.shopDetails = (TextView) convertView.findViewById(R.id.shopDetailsTextview);
                   holder.categoryIcon = (ImageView) convertView.findViewById(R.id.shopPic);
                   holder.explore = (Button) convertView.findViewById(R.id.explore_btn);

                   convertView.setTag(holder);
               }
               else {holder = (ViewHolder) convertView.getTag();}

               final Category myCat=categoryArrayList.get(groupPosition); //final for each element

               holder.shopName.setText(Html.fromHtml(myCat.get_name()), TextView.BufferType.SPANNABLE);
               holder.shopDetails.setText(Html.fromHtml(myCat.get_details()));
               String logoURL="https://sa3ednymalladmin.azurewebsites.net/IMG/"+ myCat.get_icon();
               Picasso.with(Expandded_list.this).load(logoURL).transform(new RoundedCornersTransformation(20,0)).fit().into(holder.categoryIcon);
               holder.shopName.setTextSize(16f);
               holder.explore.setTextSize(13f);
/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

               // explore.setTextSize(13f);
               holder.explore.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       //toast(myCat.get_name());

                   }
               });

               return convertView;

           } catch (Exception e) {
               return null;
           }
       }


       @Override
       public boolean hasStableIds() {
           return false;
       }

       @Override
       public boolean isChildSelectable(int groupPosition, int childPosition) {
           return true;
       }


}

    public  ArrayList<Subcategory> getSubs(int catID)
    {final ArrayList<Subcategory> subCat_array=new ArrayList();
        Volley.newRequestQueue(Expandded_list.this).add(new StringRequest(Request.Method.GET, Variables.URL_GET_SELECTED_CATEGORY_SUBCATEGORIES + catID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JsonElement root=new JsonParser().parse(response);
                            response = root.getAsString();  //not .toString
                            jsonArray = new JSONArray(response) ;
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject object = jsonArray.getJSONObject(i);
                                Subcategory mySub=new Subcategory();
                                mySub.setSubCat_ID(object.getInt("SubCategoryID"));
                                mySub.setSubCat_name(object.getString("Name_En")); // X
                                mySub.setSubCat_describtion(object.getString("Description_En")); // X
                                mySub.setSubCat_icon_url("https://sa3ednymalladmin.azurewebsites.net/IMG/"+object.getString("Photo1"));
                                subCat_array.add(mySub);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },null)
        );


      //  StringRequest subCats=
return subCat_array;
}
}