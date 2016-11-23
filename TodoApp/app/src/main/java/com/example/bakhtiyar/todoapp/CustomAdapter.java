package com.example.bakhtiyar.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 11/18/2016.
 */
public class CustomAdapter extends BaseAdapter {

    CheckBox chk;
    TextView txt,key;
    Boolean flag[];

    ArrayList<MyData> list;
    Context cont;
    LayoutInflater inf;
    int temp=1;

    public CustomAdapter(ArrayList<MyData> list, Context cont) {
        this.list = list;
        this.cont = cont;
        flag = new Boolean[list.size()];
        for(int i=0;i<flag.length;i++){
            flag[i]=false;
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inf.from(cont).inflate(R.layout.custom_layout,viewGroup,false);
        txt = (TextView) view.findViewById(R.id.rdtxt);
        key = (TextView) view.findViewById(R.id.key);
        chk = (CheckBox) view.findViewById(R.id.check);

        txt.setText(list.get(i).getData());
        key.setText(list.get(i).getKey());

        //temp = i;


        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){


//                    Toast.makeText(cont, list.get(i).getKey(), Toast.LENGTH_SHORT).show();
                    list.get(i).setFlag(b);
                    boolean ab = list.get(i).getFlag();
                    FirebaseDatabase.getInstance().getReference("Data:").
                            child(list.get(i).getKey()).child("flag").
                            setValue(ab);


                    flag[i] = true;





                    Toast.makeText(cont, "Check", Toast.LENGTH_SHORT).show();

                }
                else {
                    list.get(i).setFlag(b);
                    FirebaseDatabase.getInstance().getReference("Data:").
                            child(list.get(i).getKey()).child("flag").
                            setValue(list.get(i).getFlag());


                    flag[i] = false;









                    Toast.makeText(cont, "not check Check", Toast.LENGTH_SHORT).show();
                }
            }
        });



//        chk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//               // list.get(i).setFlag();
//
//
//
//
//
//
//        //        funct(i,chk);
//
//
////                ++temp;
////                if(chk.isChecked()) {
////                    Toast.makeText(cont, list.get(i).getKey(), Toast.LENGTH_SHORT).show();
////                    list.get(i).setFlag(true);
////                    FirebaseDatabase.getInstance().getReference("Data:").
////                            child(list.get(i).getKey()).child("flag").
////                            setValue(list.get(i).getFlag());
////
////
////                    flag[i] = true;
////
////                    Toast.makeText(cont, chk.isChecked() +
////                            "", Toast.LENGTH_SHORT).show();
////                }else if(!chk.isChecked()) {
////                    Toast.makeText(cont, list.get(i).getKey(), Toast.LENGTH_SHORT).show();
////                    list.get(i).setFlag(false);
////                    FirebaseDatabase.getInstance().getReference("Data:").
////                            child(list.get(i).getKey()).child("flag").
////                            setValue(list.get(i).getFlag());
////
////
////                    flag[i] = false;
////
////                    Toast.makeText(cont, chk.isChecked() +
////                            "", Toast.LENGTH_SHORT).show();
////
////                }
//            }
//        });



        if (chk.isChecked()){
            list.get(i).setFlag(true);
            Toast.makeText(cont,list.get(i).getFlag() +" checked", Toast.LENGTH_SHORT).show();
        }


        return view;
    }



    public void funct(int i, CheckBox chk){

        if(chk.isChecked()==false) {
            Toast.makeText(cont, list.get(i).getKey(), Toast.LENGTH_SHORT).show();
            list.get(i).setFlag(true);
            FirebaseDatabase.getInstance().getReference("Data:").
                    child(list.get(i).getKey()).child("flag").
                    setValue(list.get(i).getFlag());


            flag[i] = true;

            Toast.makeText(cont, chk.isChecked() +
                    "", Toast.LENGTH_SHORT).show();
        }



    }


}
