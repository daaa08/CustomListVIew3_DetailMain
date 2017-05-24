package com.example.da08.customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

//    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);

        // 액티비티에서 넘어온 값 처리
        // 1 인덴트 꺼냄
        Intent intent = getIntent();
        // 2 값의 묶음인 번들을 꺼냄
        Bundle bundle = intent.getExtras();  //데이터의 꾸러미
        int position = -1;

        // 3 번들이 있는지 유효성 검사
        if(bundle != null){
            // 3.1 번들이 있으면 값을 꺼내서 번들에 담는다
            position = bundle.getInt(MainActivity.data_key);
        }

            if(position > -1) {
            imageView.setImageResource(bundle.getInt(MainActivity.data_res_id));
        }


//        // 뒤로가가하면 리스트로 돌아가기
//        findViewById(R.id.btnback).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent  = new Intent(DetailActivity.this, ListActivity.class);
////                startActivity(intent);  // 스택에 쌓여서 성능 저하가 있음
//                finish();  // 나를 여기서 끝냄
//            }
//        });
    }
}

