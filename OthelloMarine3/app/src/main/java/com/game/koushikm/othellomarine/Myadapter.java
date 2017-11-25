package com.game.koushikm.othellomarine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Koushik M on 3/12/2017.
 */

public class Myadapter extends BaseAdapter {
    ArrayList<Game> ar;
    Context ct;

    public Myadapter(Context ct, ArrayList<Game> al) {
        this.ar = al;
        this.ct = ct;
    }

    @Override
    public int getCount() {
        return ar.size();
    }

    @Override
    public Object getItem(int position) {
        return ar.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            LayoutInflater inflater =(LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.player,null);
        }
        ImageView img=(ImageView) convertView.findViewById(R.id.imageView1);
        Game temp= ar.get(position);
        int num=temp.getNum();
        if (num==1)
        {
            img.setImageResource(R.drawable.grey);
        }
        else if(num==2)
        {
            img.setImageResource(R.drawable.black);
        }
        else if(num==3)
        {
            img.setImageResource(R.drawable.brown);
        }
        else if(num==4)
        {
            img.setImageResource(R.drawable.green);
        }

        return convertView;
    }

}
