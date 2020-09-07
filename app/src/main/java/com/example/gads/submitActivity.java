package com.example.gads;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class submitActivity extends AppCompatActivity {
    private ApiPost mApiPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        final TextView arrow_back=findViewById(R.id.arrow_back);
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(submitActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final EditText firstName= findViewById(R.id.edit_firstName);
        final EditText lastName=findViewById(R.id.edit_lastName);
        final EditText emailAddress=findViewById(R.id.edit_Emailaddress);
        final EditText gitHubLink=findViewById(R.id.edit_GLink);

        final TextView Send=findViewById(R.id.Send);

        mApiPost=ApiUtil.getApiPost();
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName2= firstName.getText().toString().trim();
                String lastName2= lastName.getText().toString().trim();
                String emailAddress2= emailAddress.getText().toString().trim();
                String githubLink2= gitHubLink.getText().toString().trim();

                if (!TextUtils.isEmpty(firstName2) && !TextUtils.isEmpty(lastName2) && !TextUtils.isEmpty(emailAddress2) &&!TextUtils.isEmpty(githubLink2)){
                    showDialog();

                }
                else{
                    Toast.makeText(submitActivity.this, "fields cannot be empty",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void showDialog() {
        final Dialog dialog=new Dialog(submitActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_design);
        final TextView Yes=dialog.findViewById(R.id.Yes);
      final  TextView cancel =dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText firstName= findViewById(R.id.edit_firstName);
                final EditText lastName=findViewById(R.id.edit_lastName);
                final EditText emailAddress=findViewById(R.id.edit_Emailaddress);
                final EditText gitHubLink=findViewById(R.id.edit_GLink);
                String firstName2= firstName.getText().toString().trim();
                String lastName2= lastName.getText().toString().trim();
                String emailAddress2= emailAddress.getText().toString().trim();
                String githubLink2= gitHubLink.getText().toString().trim();
                  mApiPost=ApiUtil.getApiPost();
                 Call<Void> call=mApiPost.savePost(firstName2,lastName2,emailAddress2,githubLink2);
                 call.enqueue(new Callback<Void>() {
                     @Override

                     public void onResponse(Call<Void> call, Response<Void> response) {
                         int statuscode=response.code();
                         if(response.isSuccessful()) {

                             dialog.dismiss();
                             Dialog dialog1=new Dialog(submitActivity.this);

                             dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                             dialog1.setContentView(R.layout.suc);

                             dialog1.show();
                             Window window=dialog1.getWindow();
                             assert window != null;
                             window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                         }
                     }

                     @Override
                     public void onFailure(Call<Void> call, Throwable t) {
                         dialog.dismiss();
                         Dialog dialog2=new Dialog(submitActivity.this);

                         dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);

                         dialog2.setContentView(R.layout.not);

                         dialog2.show();
                         Window window=dialog2.getWindow();
                         assert window != null;
                         window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                     }
                 });



            }
        });
        dialog.show();
        Window window=dialog.getWindow();
        assert window != null;
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
    }



}