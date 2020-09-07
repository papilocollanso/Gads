package com.example.gads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LeadersAdapter adapter;
private skillAdapter adapter1;
private RecyclerView recyclerview,recyclerview1;
private TextView submit;

ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



















        progressDialog= new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        ApiService apiService= RetrofitClass.getRetrofitInstance().create(ApiService.class);
        Call<List<RetrofitSkills>> call1= apiService.getALLSkills();
        call1.enqueue(new Callback<List<RetrofitSkills>>() {
            @Override
            public void onResponse(Call<List<RetrofitSkills>> call, Response<List<RetrofitSkills>> response) {
                progressDialog.dismiss();
                generateSkillList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetrofitSkills>> call, Throwable t) {

            }
        });
        Call<List<RetroLeaders>> call= apiService.getAllLeaders();
        call.enqueue(new Callback<List<RetroLeaders>>() {
            @Override
            public void onResponse(Call<List<RetroLeaders>> call, Response<List<RetroLeaders>> response) {
                progressDialog.dismiss();
                 generateLeaderList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroLeaders>> call, Throwable t) {
             progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Check your Internet Connection",Toast.LENGTH_LONG).show();

            }
        });



 submit=findViewById(R.id.c_submit);
 submit.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Intent intent=new Intent(MainActivity.this, submitActivity.class);
         startActivity(intent);
     }
 });




        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("Learning Leaders "));
        mTabLayout.addTab(mTabLayout.newTab().setText("Skill IQ Leaders "));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        SectionsPageAdapter sectionsPagerAdapter=new SectionsPageAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void generateLeaderList(List<RetroLeaders> leadersList) {
        recyclerview=findViewById(R.id.recycler);
        adapter=new LeadersAdapter(this,leadersList);
        recyclerview.setAdapter(adapter);
        recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);

    }
    private void generateSkillList(List<RetrofitSkills> skillLists){
        recyclerview1 = findViewById(R.id.reclerskill);
        adapter1 = new skillAdapter(this, skillLists);
        recyclerview1.setAdapter(adapter1);
        recyclerview1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerview1.setLayoutManager(linearLayoutManager1);


    }
}