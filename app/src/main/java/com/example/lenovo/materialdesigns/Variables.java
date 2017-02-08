package com.example.lenovo.materialdesigns;

/**
 * Created by lenovo on 23/01/2017.
 */

public class Variables {

    public static String catID;
    public static final String URL_GET_ALL_ACTIVE_ITEMS="http://sa3ednymallservice.azurewebsites.net/sodic/Item/GetAllActiveItems/GetAll";
    public static final String URL_GET_CATEGORIES_GOODS="http://sa3ednyMallservice.azurewebsites.net/sodic/Category/GetAllActiveCategoriesGoods/Get";
    public static final String URL_GET_ALL_ITEMS="http://Sa3ednyMallservice.azurewebsites.net/sodic//Item/GetAllActiveItems/GetAll";
    public static final String URL_GET_NEW_ITEMS="http://Sa3ednyMallservice.azurewebsites.net/sodic///Item/GetAllNewItems/GetAllNew";
    public static final String URL_GET_SELECTED_CATEGORY_ITEMS="http://Sa3ednyMallservice.azurewebsites.net/sodic//Item/GetActiveItem/Get?categoryID=";
    public  static  final String URL_GET_SELECTED_CATEGORY_SUBCATEGORIES="http://sa3ednyMallservice.azurewebsites.net/sodic/Category/GetActiveSubCategories/Get?categoryID=";
    public  static  final String URL_GET_SELECTED_SUBCATEGORY_ITEM="http://Sa3ednymallservice.azurewebsites.net/sodic/Item/GetActiveItemforsubcategory/Getitems?subcategoryID=";
    public  static final  String URL_GET_FAVOURITES_FOR_ID="http://sa3ednymallservice.azurewebsites.net/sodic/Favourite/GetFavourite/Get?AccountID=12";

/*    public static ArrayList<category> getRequest (Context context)
    {

        final ArrayList<category> categoryArrayList2=new ArrayList<>();


        StringRequest stringRequest=new StringRequest(Request.Method.GET,URL_CATEGORIES,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {


                    JsonElement root=new JsonParser().parse(response);
                    response = root.getAsString();  //not .toString
                    JSONArray jsonArray = new JSONArray(response) ;

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
  static  public String htmlRender(String ss)

    {
        ss=ss.replace("span","font");
        ss=ss.replace("style=\"color:","color=");
        ss=ss.replace(";\"","");

        return ss;
    }*/
}
