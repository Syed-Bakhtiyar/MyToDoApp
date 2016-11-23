package com.example.bakhtiyar.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref;

    ListView listv;
    Button add, dlt;
    TextView txt;

    String data;
    LayoutInflater inf;

    ArrayList<MyData> list;
    CustomAdapter cstm, globalAdapt;

    String mykey;
    int count;

    MyData mydata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Data:");

        listv = (ListView) findViewById(R.id.list);
        txt = (TextView) findViewById(R.id.txt);
        add = (Button) findViewById(R.id.add);
        dlt = (Button) findViewById(R.id.dlt);

        list = new ArrayList<>();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = txt.getText().toString();
                count=1;

                mydata = new MyData(data, ref.push().getKey().toString(),false);

                //ref.push().setValue(new MyData(data,ref.getKey()));

                    ref.child(mydata.getKey()).setValue(mydata);

                txt.setText("");
            }
        });




try {


   ref.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            mydata = dataSnapshot.getValue(MyData.class);
            list.add(new MyData(dataSnapshot.child("data").getValue().toString(), dataSnapshot.getKey()  ,(Boolean)  dataSnapshot.child("flag").getValue()));


            cstm = new CustomAdapter(list, getApplicationContext());

            globalAdapt = cstm;

            listv.setAdapter(cstm);

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//jkjkk
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {


           for (int  i=0;i<list.size();i++) {
               if(dataSnapshot.getKey()==list.get(i).getKey()){
                   list.remove(i);
                   cstm.notifyDataSetChanged();

               }


            }

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

}catch (Exception e){

    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

}





    dlt.setOnClickListener(new View.OnClickListener() {
        boolean flag[];



        @Override
        public void onClick(View view) {
try{
//      flag = new boolean[list.size()];
//
//        for(int i=0;i<list.size();i++){
//
//            flag[i]=false;
//
//        }
//int count=0;

            for (int i = 0; i < globalAdapt.list.size(); i++) {

                if (globalAdapt.flag[i]) {
                    ref.child(globalAdapt.list.get(i).getKey()).removeValue();


                //    flag[i]=true;
                //
//                    if(count==0){
//                        list.remove(i);
//                        cstm.notifyDataSetChanged();
//
//                    }
//                    else {
//                        int tem =i;
//                        tem--;
//                        list.remove(tem);
//                        cstm.notifyDataSetChanged();
//
//                    }
//                count++;



                }
            }

//    for (int i=0;i<flag.length;i++){
//        if(flag[i]){
//            if(i==flag.length){
//                break;
//            }
//
//
//            list.remove(i);
//            cstm.notifyDataSetChanged();
//            continue;
//
//        }
//
//    }

        }catch(Exception e){
            //Toast.makeText(MainActivity.this, e.toString()+"", Toast.LENGTH_SHORT).show();

        }
        }//
    });




    }


    public  void remove(ArrayList<MyData> list,int i){


    }
}
