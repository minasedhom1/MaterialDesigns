package com.example.lenovo.materialdesigns;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.melnykov.fab.FloatingActionButton;
import com.sackcentury.shinebuttonlib.ShineButton;

public class MainActivity extends AppCompatActivity {
    boolean  like=false;
    FloatingActionButton fab;
    ShineButton shineButton;
    Button popUp,faceLogin,expandlist,subCatItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_main);
        shineButton  = (ShineButton) findViewById(R.id.po_image1);
        shineButton.init(this);
        if(savedInstanceState!=null)
        shineButton.setChecked(savedInstanceState.getBoolean("btn"));
        shineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(like==false)
                {
                Toast.makeText(getApplicationContext(),"Like!",Toast.LENGTH_SHORT).show();
                    like=true;}
                else
                {
                    Toast.makeText(getApplicationContext(),"UnLike!",Toast.LENGTH_SHORT).show();
                   like=false;
                }
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        final ListView listView = (ListView) findViewById(R.id.list_view);

         fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.attachToListView(listView);
        fab.hide();
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {

        }

        @Override
        public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            if(i==0)
                fab.hide();
            else fab.show();
        }
    });


popUp= (Button) findViewById(R.id.popup_btn);
        popUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PopUpActivity.class));
            }
        });


        faceLogin= (Button) findViewById(R.id.face_btn);
        faceLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,FacebookLogin.class));
            }
        });

       expandlist= (Button) findViewById(R.id.list_btn);
        expandlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Expandded_list.class));
            }
        });

        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome/fonts/fontawesome-webfont.ttf" );
        subCatItems= (Button) findViewById(R.id.button3);
        subCatItems.setTypeface(font);
        subCatItems.setText(getString(R.string.phone_font)+" CALL");
        subCatItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        Boolean likelike=shineButton.isChecked();
        outState.putBoolean("btn",likelike);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
