package com.codefobi.startupproject.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codefobi.startupproject.R;
import com.codefobi.startupproject.adapters.ContentAdapter;
import com.codefobi.startupproject.models.Content;
import com.codefobi.startupproject.utils.DatabaseHelper;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHelper databaseHelper;
    TextView toolbarTitle;
    ImageView toolbarImage;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        init();
    }

    private void init(){
        toolbarTitle = (TextView) findViewById(R.id.toolbar_tv);
        toolbarImage = (ImageView) findViewById(R.id.toolbar_iv);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        setRecycler();

        /*Content content = new Content();
        content.setSpecifier("0");
        content.setTitle("آواتک");
        content.setSpecifier("0");
        content.setBody("ر ایران هم اکنون چند مجموعه در قالب شتاب دهنده مشغول به فعالیت هستند. آواتک، گروه دیموند ");

        databaseHelper.addContent(content);*/

        toolbarImage.setOnClickListener(this);

    }

    private void setRecycler() {
        ContentAdapter adapter = new ContentAdapter(this,databaseHelper.getAllContents());
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_iv:
//                drawerLayout.openDrawer(Gravity.RIGHT);
                startActivity(new Intent(MainActivity.this , ReadContentActivity.class));
                break;
        }
    }

    public void recyclerViewClick(View view) {
        Toast.makeText(MainActivity.this, "works fine", Toast.LENGTH_SHORT).show();
    }
}
