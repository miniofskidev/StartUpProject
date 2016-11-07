package com.codefobi.startupproject.activity;

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

public class ReadContentActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView toolbarTitle;
    private ImageView toolbarImage;
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

        ReadContentAdapter adapter = new ReadContentAdapter(this,databaseHelper.getReadBy(intent.getStringExtra("WHO")));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);


    }

    private List<ReadContent> getData() {

        List<ReadContent> readContentList = new ArrayList<>();

        ReadContent contentPic = new ReadContent();
        contentPic.setWhodoyou("0");
        contentPic.setSpecifier("2");
        contentPic.setImagePath("http://digiato.com/wp-content/uploads/2014/11/seed-acc.jpg");
        readContentList.add(contentPic);

        databaseHelper.addReadContent(contentPic);

        ReadContent contentoaf = new ReadContent();
        contentoaf.setSpecifier("0");
        contentoaf.setWhodoyou("0");
        contentoaf.setTitle("آشنایی با شتاب دهنده های ایرانی");
        contentoaf.setBody("در ایران هم اکنون چند مجموعه در قالب شتاب دهنده مشغول به فعالیت هستند. آواتک، گروه دیموند و مپس از جمله نام های مطرح این حوزه به شمار می روند. این مجموعه ها امیدوار هستند با آشنایی بیشتر کاربران ایرانی با مفهوم استارتاپ، در آینده ایده های جذابی را به موفقیت رسانند.");
        readContentList.add(contentoaf);

        databaseHelper.addReadContent(contentoaf);

        ReadContent contentOne = new ReadContent();
        contentOne.setWhodoyou("0");
        contentOne.setSpecifier("3");
        contentOne.setTitle("آواتک");
        contentOne.setBody("آواتک قصد دارد تا سال 2018، بر روی 100 استارتاپ ایرانی سرمایه گذاری کند. این تیم چهار مرحله را برای این کار در نظر گرفته است. مرحله اول ارائه طرح هاست. پس از ارائه طرح استارتاپ هایی که ثبت نام کرده اند 20 تیم به مرحله آماده سازی برای شتاب می رسند. در این مرحله تیم ها طی 2 ماه آموزش رایگان می بینند. در طول این مدت فضا و امکانات نیز به رایگان در اختیار استارتاپ ها قرار می گیرد.");
        contentOne.setImagePath("http://digiato.com/wp-content/uploads/2014/11/slider_2.jpg");
        readContentList.add(contentOne);

        databaseHelper.addReadContent(contentOne);

        ReadContent contentTwo = new ReadContent();
        contentTwo.setWhodoyou("0");
        contentTwo.setSpecifier("1");
        contentTwo.setBody("پس از این دو ماه 10 تیم منتخب به مرحله شتاب گیری می رسند. در این مرحله که 4 ماه طول می کشد، استارتاپ های موفق بر روی تولید محصول خود تمرکز می کنند. سپس تمامی نیاز های هر 10 تیم از جمله ایده های فکری، نیازهای مالی، محیط کار و آموزش های مورد نیاز توسط آواتک پشتیبانی می شود. در مرحله آخر روز نمایش دستاورد استارتاپ ها فرا می رسد.");
        readContentList.add(contentTwo);

        databaseHelper.addReadContent(contentTwo);

        ReadContent condgaos = new ReadContent();
        condgaos.setWhodoyou("0");
        condgaos.setSpecifier("1");
        condgaos.setBody("تیم هایی که بتوانند در روز Demo day موفق به نمایش بهتر شوند، با سرمایه گذاری نهایی آواتک مواجه خواهند شد.");
        readContentList.add(condgaos);

        databaseHelper.addReadContent(condgaos);

        ReadContent contentThree = new ReadContent();
        contentThree.setWhodoyou("0");
        contentThree.setSpecifier("1");
        contentThree.setBody("این شرکت از مرحله آمادگی برای شتاب تا روز نمایش دستاوردها برای هر استارتاپ مبلغ 25 میلیون تومان (معادل 8 هزار دلار آمریکا) سرمایه گذاری و در قبال آن 15 درصد سهام هر استارتاپ را به نام خود ثبت می کند.");
        readContentList.add(contentThree);

        databaseHelper.addReadContent(contentThree);

        return readContentList;
    }

    private void init() {
        toolbarTitle = (TextView) findViewById(R.id.toolbar_tv);
        toolbarImage = (ImageView) findViewById(R.id.toolbar_iv);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        toolbarImage.setOnClickListener(this);

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
}
