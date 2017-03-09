package com.example.lenovo.materialdesigns;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class PopUpActivity extends AppCompatActivity {
ImageView imageView;
    PopupWindow popUpWindow;
    WindowManager.LayoutParams layoutParams;
    LinearLayout mainLayout;
    LinearLayout containerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        imageView= (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* new SweetAlertDialog(PopUpActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("Sweet!")
                        .setContentText("Here's a custom image.")
                        .setCustomImage(R.mipmap.carfor)
                        .show();*/
                //     showpopup();

            }
        });
    }
/*    private void showpopup()
    {

        final Dialog nagDialog = new Dialog(this);
        nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        nagDialog.setContentView(R.layout.pop_up_item_image);
        ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.imageView3);
        ivPreview.setImageResource(R.mipmap.movie);
        nagDialog.show();

    }*/
}
