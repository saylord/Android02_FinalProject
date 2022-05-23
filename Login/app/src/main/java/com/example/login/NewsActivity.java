package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.login.Models.NewsApiResponse;
import com.example.login.Models.NewsHeadlines;

import java.util.List;

public class NewsActivity extends AppCompatActivity implements SelectListener, View.OnClickListener{

    private static final String TAG = "NewsActivityLog";

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button b1, b2, b3, b4, b5, b6, b7;
    Switch switchBtn;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_news);

        searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news articles of " + query);
                dialog.show();
                
                RequestManager manager = new RequestManager(NewsActivity.this);
                manager.getNewsHeadlines(listener, "general", query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching news articles...");
        dialog.show();

        b1 = findViewById(R.id.btn_1);
        b1.setOnClickListener(this);

        b2 = findViewById(R.id.btn_2);
        b2.setOnClickListener(this);

        b3 = findViewById(R.id.btn_3);
        b3.setOnClickListener(this);

        b4 = findViewById(R.id.btn_4);
        b4.setOnClickListener(this);

        b5 = findViewById(R.id.btn_5);
        b5.setOnClickListener(this);

        b6 = findViewById(R.id.btn_6);
        b6.setOnClickListener(this);

        b7 = findViewById(R.id.btn_7);
        b7.setOnClickListener(this);

        switchBtn = findViewById(R.id.switchBtn);
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    LinearLayout layout =(LinearLayout)findViewById(R.id.main);
//                    layout.setBackgroundResource(R.drawable.back02);

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general", null);
    }

    private  final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if (list.isEmpty()){
                Toast.makeText(NewsActivity.this, "No data found!!!", Toast.LENGTH_SHORT).show();
            }
            else{
                showNews(list);
                dialog.dismiss();
            }

            showNews(list);
            dialog.dismiss();
        }
        

        @Override
        public void onError(String message) {
            Toast.makeText(NewsActivity.this, "An Error Occurred!!!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(NewsActivity.this, DetailsActivity.class).putExtra("data", headlines));
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();

        dialog.setTitle("Fetching news articles of " + category);
        dialog.show();

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, category, null);

    }

    @Override
    protected void onRestart(){
        Log.d(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onStart(){
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume(){
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause(){
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop(){
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

}