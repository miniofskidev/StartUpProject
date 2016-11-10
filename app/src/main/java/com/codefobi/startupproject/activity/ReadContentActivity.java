package com.codefobi.startupproject.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codefobi.startupproject.R;
import com.codefobi.startupproject.adapters.ReadContentAdapter;
import com.codefobi.startupproject.models.ReadContent;
import com.codefobi.startupproject.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ReadContentActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView toolbarTitle;
    private ImageView toolbarImage , refreshButton;
    private DatabaseHelper databaseHelper;
    private Intent intent ;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_lesson);

        databaseHelper = new DatabaseHelper(this);

        intent = getIntent();

        init();

        ReadContentAdapter adapter = new ReadContentAdapter
                (this,databaseHelper.getReadBy(intent.getStringExtra("WHO")));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

    }

    private void init() {
        toolbarTitle = (TextView) findViewById(R.id.toolbar_tv);
        toolbarImage = (ImageView) findViewById(R.id.toolbar_iv);
        refreshButton = (ImageView) findViewById(R.id.refresh_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        toolbarImage.setOnClickListener(this);

        refreshButton.setVisibility(View.INVISIBLE);

        toolbarTitle.setText(intent.getStringExtra("TITLE"));
        toolbarImage.setImageResource(R.drawable.arrow_right);
        toolbarImage.setPadding(-20,30,0,30);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_iv:
                finish();
                break;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
