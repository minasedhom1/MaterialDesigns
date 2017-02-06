package com.example.lenovo.materialdesigns;

import java.util.ArrayList;

/**
 * Created by lenovo on 09/09/2015.
 */
public class Category {
 private int _id;

   private String _name,_details,_icon;
   private ArrayList<Subcategory> sub_array= new ArrayList<>();

    public ArrayList<Subcategory> getSub_array() {
        return sub_array;
    }

    public void setSub_array(ArrayList<Subcategory> sub_array) {
        this.sub_array = sub_array;
    }

    public boolean isHas_sub() {
        return has_sub;
    }

    private  boolean has_sub;
   /* public Restaurant(int _id, String _name, String _phone, String _category, String _location, String _menu, String _icon, int _flag, String _longtude, String _latitude, String _website) {
        this._id = _id;
        this._name = _name;
        this._phone = _phone;
        this._category = _category;
        this._location = _location;
        this._menu = _menu;
        this._icon = _icon;
        this._flag = _flag;
        this._longtude = _longtude;
        this._latitude = _latitude;
        this._website = _website;+
    }*/

    public void setHas_sub(boolean has_sub) {
        this.has_sub = has_sub;
    }

    public Category(String _name, String _details, String _icon) {
        this._name = _name;
       this._details=_details;
        this._icon = _icon;
    }
    public Category(String _name, String _details) {
        this._name = _name;
        this._details=_details;


    }
public Category()
{}


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }


    public String get_details() {
        return _details;
    }

    public void set_details(String _details) {
        this._details = _details;
    }

    public String get_icon() {
        return _icon;
    }

    public void set_icon(String _icon) {
        this._icon = _icon;
    }


}