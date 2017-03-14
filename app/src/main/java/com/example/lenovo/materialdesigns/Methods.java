package com.example.lenovo.materialdesigns;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lenovo on 26/01/2017.
 */

public class Methods {

    public static void toast(String s, Context context) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public static String htmlRender(String ss) {
        ss = ss.replace("span", "font");
        ss = ss.replace("style=\"color:", "color=");
        ss = ss.replace(";\"", "");
        ss = ss.replaceAll("<p>", "");
        ss = ss.replaceAll("</p>", ""); //********
        return ss;
    }

    static int count = 0;

    public static void signture(Context context) {
        count++;
        if (count == 10) {
            Methods.toast("Apps-V@lley", context);
            count = 0;
        }
    }


    //public  static void FabToList(Li)
}
/*
    public ArrayList<Item> get_items(String url,Context context) {

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JsonElement root = new JsonParser().parse(response);
                    response = root.getAsString();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("ItemsList");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Item item = new Item();
                        item.setId(object.getString("ItemID"));
                        item.setName(Methods.htmlRender(object.getString("Name_En")));
                        item.setDescription(object.getString("Description_En"));
                        item.setPhone1(object.getString("Phone1"));
                        item.setPhone2(object.getString("Phone2"));
                        item.setPhone3(object.getString("Phone3"));
                        item.setPhone4(object.getString("Phone4"));
                        item.setPhone5(object.getString("Phone5"));
                        item.setMenu_url(object.getString("PDF_URL"));
                        if (object.getString("Rate") != "null") {
                            item.setRate(Float.valueOf(object.getString("Rate"))); //get rate and round it implicitly
                            Log.d("rate", Float.valueOf(object.getString("Rate")).toString());
                        }

                        item.setPhoto1(Urls.URL_IMG_PATH + object.getString("Photo1"));
                        item.setCategoryName(object.getString("CategoryName_En"));
                        item.setSubcategoryName(object.getString("SubcategoryName_En"));
                        item.setCategoryID(Variables.catID);

                      *//*  if(fav_ids.size()!=0 && fav_ids.contains(item.getId()))
                        {
                            item.setLike(true);
                        }*//*

                        itemArrayList.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }

                , null);
        RequestQueue queue=Volley.newRequestQueue(context);
        queue.add(request);

    }}*/
