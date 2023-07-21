package com.example.step02listview;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    //필드 선언
    List<String> names;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res/layout/activity_main
        setContentView(R.layout.activity_main);
        //activity_main.xml의 <ListView/>로 new ListView를 생성
        //ListView 의 참조값 findViewById(R.id.listView)으로 리턴된다
        ListView listView=findViewById(R.id.listView);

        //ListView 에 출력할 sample data
        names=new ArrayList<>();
        names.add("김구라");
        names.add("해골");
        names.add("원숭이");
        for(int i=0; i<100; i++){
            names.add("아무개"+i);
        }
        //ListView 에 연결할 아답타 객체
        // new ArrayAdapter<>( Context , layout resource , 모델 )
        adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                names);
        //ListView 에 아답타 연결하기
        listView.setAdapter(adapter);
        //Activity 를 아이템 클릭 리스너로 등록 하기
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        //버튼에 리스너 등록
        Button addBtn = findViewById(R.id.addBtn);
        //addBtn을 클릭하면 메소드가 호출된다
        addBtn.setOnClickListener(view -> {
            //1. Edit Text에 입력한 문자를 읽어온다
            EditText inputName = findViewById(R.id.inputName);
            String name = inputName.getText().toString();
            //2. names (모델) 에 추가한다
            names.add(name);
            //3. adapter에 names(모델) 이 변경되었다고 알린다
            adapter.notifyDataSetChanged();

            int position = adapter.getCount();// 전체 아이템의 갯수
            listView.smoothScrollToPosition(position);
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // int i 는 클릭한 아이템의 인덱스가 들어 있다.
        String name=names.get(i);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        /*//알림창에 있는 버튼을 눌렀을 때 호출되는 메소드를 가지고 있는 리스너 객체
        //익명의 local inner class
        //이안에서는 바깥영역에서 정의된 지역변수와 필드를 참조할 수 있다.
        DialogInterface.OnClickListener listener= new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int result) {

                if(result == DialogInterface.BUTTON_POSITIVE){
                    //POSITIVE 버튼을 눌렀을때
                    // i 번째 인덱스의 아이템을 제거
                    // 1. 모델에서 제거하고
                    names.remove(i);
                    // 2. 모델이 변경되었다고 아답타에 알리면 Listview 가 업데이트 된다
                    adapter.notifyDataSetChanged();
                }
            }
        };

        //AlertDialog 안에 Builder 클래스 생성... 나머지 메소드 연결
        new AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("삭제 할까요")
                .setPositiveButton("네", listener)
                .setNegativeButton("아니요", listener)
                .create()
                .show();
        return false;*/

        new AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("삭제 할까요")
                .setPositiveButton("네", (a,b)->{
                    // 1. 모델에서 제거하고
                    names.remove(i);
                    // 2. 모델이 변경되었다고 아답타에 알리면 Listview 가 업데이트 된다
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("아니요", null)
                .create()
                .show();
        return false;

    }
}






