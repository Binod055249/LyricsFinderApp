package com.example.lyricsfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.textclassifier.ConversationActions;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {


    EditText edtArtistName,edtSongName;
    Button btnGetLyrics;
    TextView textLyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        edtArtistName=findViewById(R.id.edtArtistName);
        edtSongName=findViewById(R.id.edtSongName);
        btnGetLyrics=findViewById(R.id.btnGetLyrics);
        textLyrics=findViewById(R.id.textLyrics);

       btnGetLyrics.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             Toast.makeText(MainActivity.this, "now button is tapped", Toast.LENGTH_SHORT).show();

                String url = "https://api.lyrics.ovh/v1/"+edtArtistName.getText().toString()+"/"+edtSongName.getText().toString();
              url.replace(" ","20%");
               RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
               JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {

                   try{
                       textLyrics.setText(response.getString("lyrics"));
                   }catch(JSONException e){
                       e.printStackTrace();
                   }

                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               });
               requestQueue.add(jsonObjectRequest);
           }
       });
       }
    }



