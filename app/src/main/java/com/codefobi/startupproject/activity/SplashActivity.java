package com.codefobi.startupproject.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codefobi.startupproject.R;
import com.codefobi.startupproject.adapters.ContentAdapter;
import com.codefobi.startupproject.models.Content;
import com.codefobi.startupproject.models.ReadContent;
import com.codefobi.startupproject.retrofit.ApiClient;
import com.codefobi.startupproject.retrofit.ApiInterface;
import com.codefobi.startupproject.utils.DatabaseHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        databaseHelper = new DatabaseHelper(this);

        dataDownload();

    }

    private void dataDownload() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Content>> call = apiService.contentCall();
        final Call<List<ReadContent>> readContentCall = apiService.readContentCall();

        call.enqueue(new Callback<List<Content>>() {
            @Override
            public void onResponse(Call<List<Content>> call, Response<List<Content>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    databaseHelper.addContent(response.body().get(i));
                    Log.d("amin", "adding model to database");
                }
                if (response.isSuccessful()) {
                    readContentCall.enqueue(new Callback<List<ReadContent>>() {
                        @Override
                        public void onResponse(Call<List<ReadContent>> call, Response<List<ReadContent>> response) {
                            for (int i = 0; i < response.body().size(); i++){
                                databaseHelper.addReadContent(response.body().get(i));
                                Log.d("amin" , "adddin read contetn to database" + response.body().get(i).getTitle());
                            }
                            startActivity(new Intent(SplashActivity.this , MainActivity.class));
                        }
                        @Override
                        public void onFailure(Call<List<ReadContent>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Content>> call, Throwable t) {
                startActivity(new Intent(SplashActivity.this , MainActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    //TODO: Place the server check for update here

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
