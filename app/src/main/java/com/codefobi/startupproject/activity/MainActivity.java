package com.codefobi.startupproject.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codefobi.startupproject.R;
import com.codefobi.startupproject.adapters.ContentAdapter;
import com.codefobi.startupproject.models.Content;
import com.codefobi.startupproject.models.ReadContent;
import com.codefobi.startupproject.retrofit.ApiClient;
import com.codefobi.startupproject.retrofit.ApiInterface;
import com.codefobi.startupproject.utils.DatabaseHelper;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHelper databaseHelper;
    TextView toolbarTitle;
    ImageView toolbarImage, refreshButton;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        init();
    }

    private void init() {
        toolbarTitle = (TextView) findViewById(R.id.toolbar_tv);
        toolbarImage = (ImageView) findViewById(R.id.toolbar_iv);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        refreshButton = (ImageView) findViewById(R.id.refresh_button);

        setRecycler();

        toolbarImage.setOnClickListener(this);
        refreshButton.setOnClickListener(this);

        toolbarImage.setImageResource(R.drawable.menu);
        toolbarImage.setPadding(-20, 25, 0, 25);
    }

    private void setRecycler() {
        ContentAdapter adapter = new ContentAdapter(this, databaseHelper.getAllContents());
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_iv:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.refresh_button:
                refreshButton.animate().rotationBy(360 * 4).setDuration(1000).start();
                break;
        }
    }

    public void recyclerViewClick(View view) {
        Toast.makeText(MainActivity.this, "works fine", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
