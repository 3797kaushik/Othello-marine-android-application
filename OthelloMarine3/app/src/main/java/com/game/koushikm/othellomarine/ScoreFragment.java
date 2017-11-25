package com.game.koushikm.othellomarine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {


    public ScoreFragment() {
        // Required empty public constructor
    }

    TextView tv,tv1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_score, container, false);
        tv=(TextView) v.findViewById(R.id.balck_score);
        tv1=(TextView) v.findViewById(R.id.white_score);

        ImageView iv1=(ImageView)v.findViewById(R.id.black_image);
        ImageView iv2=(ImageView)v.findViewById(R.id.white_image);
        //SharedPreferences sp=(SharedPreferences)get

        iv1.setImageResource(R.drawable.black3);
        iv2.setImageResource(R.drawable.brown3);

        return v;
    }

    public  void updateScoreB(int a){
        String b=a+"";
        tv.setText(b);
    }
    public  void updateScoreW(int a){
        String b=a+"";
        tv1.setText(b);
    }

}
