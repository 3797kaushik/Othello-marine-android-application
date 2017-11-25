package com.game.koushikm.othellomarine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Koushik M on 3/28/2017.
 */

public class MyAdapter1 extends BaseAdapter {

    //Person p;
    ArrayList<Person> al1;
    Context ct;
    MyAdapter1(ArrayList<Person> al, Context c)
    {
        al1=al;
        ct=c;
    }
    @Override
    public int getCount() {
        return al1.size();
    }

    @Override
    public Object getItem(int position) {
        return al1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AppCompatActivity ac =(AppCompatActivity) ct;
        LayoutInflater lf =ac.getLayoutInflater();

        Person p1 =(Person) getItem(position);

        View v1= lf.inflate(R.layout.my_layout,null);

        ImageView iv=(ImageView) v1.findViewById(R.id.iv1);
        TextView t1 =(TextView) v1.findViewById(R.id.tv1);

        iv.setImageResource(p1.getImage());
        t1.setText(p1.getDetail());
        if(position==6){
            iv.setVisibility(View.GONE);
        }
        return v1;
    }
}

