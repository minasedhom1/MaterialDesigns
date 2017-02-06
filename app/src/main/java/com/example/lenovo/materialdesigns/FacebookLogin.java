package com.example.lenovo.materialdesigns;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.widget.LikeView;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class FacebookLogin extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
  TextView faceName;
ImageView imageView;
    Profile profile;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
            faceBookStuff();

 /*       faceName= (TextView) findViewById(R.id.name_tv);
        callbackManager = CallbackManager.Factory.create();
        imageView= (ImageView) findViewById(R.id.imageView4);

        FacebookSdk.sdkInitialize(FacebookLogin.this);
       // AppEventsLogger.activateApp(this);


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(FacebookLogin.this,String.valueOf(loginResult.getAccessToken().isExpired()),Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });


      profile=Profile.getCurrentProfile();
        if(profile!=null)
        {
            faceName.setText(profile.getName());
            Picasso.with(getBaseContext()).load(profile.getProfilePictureUri(300, 300)).transform(new CropCircleTransformation()).into(imageView);
        }
         else
        {
            imageView.setImageResource(R.mipmap.ic_launcher);
            faceName.setText("none");
        }
        ProfileTracker profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                     if(currentProfile!=null) {
                         faceName.setText(currentProfile.getName());
                         Picasso.with(getBaseContext()).load(currentProfile.getProfilePictureUri(300, 300)).transform(new CropCircleTransformation()).into(imageView);
                     }
                     else
                     {
                         imageView.setImageResource(R.mipmap.ic_launcher);
                         faceName.setText("none");
                     }
            }
        };
        profileTracker.startTracking();*/
       /* loginButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       // Toast.makeText(getApplicationContext(),loginButton.getText(),Toast.LENGTH_LONG).show();
      *//*  if(loginButton.getText()=="Log out") {
            faceName.setText(profile.getName());
            Picasso.with(getBaseContext()).load(profile.getProfilePictureUri(300, 300)).transform(new RoundedCornersTransformation(30,0)).into(imageView);
        }
        else {
            imageView.setImageResource(R.mipmap.ic_launcher);
            faceName.setText("none");
        }*//*
    }
});*/



      //  sp=getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

/*      ProfileTracker profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

                String name =  currentProfile.getName();
                String imageUrl=currentProfile.getProfilePictureUri(300,300).toString();

                faceName.setText(name);
                Picasso.with(getBaseContext()).load(imageUrl).into(imageView);

             //   currentProfile=Profile.getCurrentProfile();
            }
        };*/

/*profileTracker.startTracking();*/
     /*
        faceName.setText(sp.getString("name","name"));
        Picasso.with(getBaseContext()).load(sp.getString("img","img")).into(imageView);
*/
       /* try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }*/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

void faceBookStuff()
{
    FacebookSdk.sdkInitialize(FacebookLogin.this);
    callbackManager = CallbackManager.Factory.create();

    faceName= (TextView) findViewById(R.id.name_tv);
    imageView= (ImageView) findViewById(R.id.imageView4);
/*   loginButton = (LoginButton) findViewById(R.id.login_button);
    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {
            Toast.makeText(FacebookLogin.this,"login succeed",Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCancel() {
        }
        @Override
        public void onError(FacebookException error) {
        }
    });*/

    LikeView likeView = (LikeView) findViewById(R.id.like_btn);
    likeView.setLikeViewStyle(LikeView.Style.STANDARD);
    likeView.setObjectIdAndType("https://www.facebook.com/sa3ednyapps/",LikeView.ObjectType.PAGE);

    profile=Profile.getCurrentProfile();
    if(profile!=null)
    {
        faceName.setText(profile.getName());
        Picasso.with(getBaseContext()).load(profile.getProfilePictureUri(300, 300)).transform(new CropCircleTransformation()).into(imageView);
    }
    else
    {
        imageView.setImageResource(R.mipmap.ic_launcher);
        faceName.setText("none");
    }
     //loginTracker();
    ProfileTracker profileTracker=new ProfileTracker() {
        @Override
        protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
            if(currentProfile!=null) {
                faceName.setText(currentProfile.getName());
                Picasso.with(getBaseContext()).load(currentProfile.getProfilePictureUri(300, 300)).transform(new CropCircleTransformation()).into(imageView);
            }
            else
            {
                imageView.setImageResource(R.mipmap.ic_launcher);
                faceName.setText("none");
            }
        }
    };
    profileTracker.startTracking();
}


   /* void loginTracker()
    {
        ProfileTracker profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if(currentProfile!=null) {
                    faceName.setText(currentProfile.getName());
                    Picasso.with(getBaseContext()).load(currentProfile.getProfilePictureUri(300, 300)).transform(new CropCircleTransformation()).into(imageView);
                }
                else
                {
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    faceName.setText("none");
                }
            }
        };
        profileTracker.startTracking();
    }*/
}
