package com.game.koushikm.othellomarine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FooterFragment extends Fragment {

    newGameListener n;
    Button b;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            n=(newGameListener) activity;
        }catch (Exception e){}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_footer, container, false);

        Button b=(Button) v.findViewById(R.id.newGameBut);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n.newgame(100);            }
        });
        Button b1=(Button) v.findViewById(R.id.undoGame);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n.newgame(200);
            }
        });
        Button b2=(Button) v.findViewById(R.id.redoGame);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n.newgame(300);
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    public interface newGameListener{
        void newgame(int data);
    }
}
