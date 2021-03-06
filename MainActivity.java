package com.example.da08.customlistview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String data_key = "position";
    public static final String data_res_id = "resid";
    public static final String data_title = "title";

    ArrayList<Data> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1 data
        datas = Loader.getData(this);

        // 2 adapter
        CustomAdapter adapter = new CustomAdapter(datas, this);

        // 3 연결
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                Data data = datas.get(position);

                intent.putExtra(data_key, position);
                intent.putExtra(data_res_id, data.resid);
                intent.putExtra(data_title, data.title);


                startActivity(intent);
            }
        });
    }
}


class CustomAdapter extends BaseAdapter {  // 2번의 adapter를 생성해주기위해서 만들어진 class

    // 1 data
    ArrayList<Data> datas;
    // 2 inflater
    LayoutInflater inflater;

    public CustomAdapter(ArrayList<Data> datas, Context context) {
        this.datas = datas;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1 컨버투뷰를 체크해서 널 일경우 아이템 레이아웃을 생성해준다
        Holder holder;  // 겹치므로 밖에다가 한번 선언해준것임
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, null);
            holder = new Holder();
            holder.no = (TextView) convertView.findViewById(R.id.txtno);
            holder.title = (TextView) convertView.findViewById(R.id.txttitle);
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();  // 셋태그로하면 오브젝트로 들어감
        }
        // 2 내 아이쳄에 해당하는 데이터를 세팅
        // 뿌려주는건 if문 밖에 작성해줘야 함
        Data data = datas.get(position);

//            ((TextView) convertView.findViewById(R.id.txtno)).setText(data.no + "");   // 홀더를 생서했기때문에 위에 처럼 표시하면 됨
//            ((TextView) convertView.findViewById(R.id.txttitle)).setText(data.title);
        holder.no.setText(data.no + "");
        holder.title.setText(data.title);
        holder.image.setImageResource(data.resid);

        return convertView;
    }

    // convertView가 null이냐 아니냐에 따라서 한번만 체크 됨
    class Holder {
        TextView no;
        TextView title;
        ImageView image;

    }
}

class Loader {
    public static ArrayList<Data> getData(Context context) {   // 데이터를 가져옴
        ArrayList<Data> result = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Data data = new Data();
            data.no = i;
            data.title = "배우";
            //데이터를 가져오는건 홀더에서 하지 않는다
//            int id = context.getResources().getIdentifier("ac" + suffix, "mipmap" , context.getPackageName());
            data.setImage("ac" + i, context);
            result.add(data);
        }
        return result;
    }
}

class Data {
    public int no;
    public String title;
    public String image;

    public int resid;

    public void setImage(String str, Context context) {
        image = str;
        //문자열로 리소스 이미지 가져오기
        resid = context.getResources().getIdentifier(image, "mipmap", context.getPackageName());
    }
}