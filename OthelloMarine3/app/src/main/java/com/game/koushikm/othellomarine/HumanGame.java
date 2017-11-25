package com.game.koushikm.othellomarine;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class HumanGame extends AppCompatActivity implements FooterFragment.newGameListener {

   static GridView gv;

    MenuInflater menuInflater;
   static ArrayList<Game> ar;
    static ScoreFragment f2;
    static int turn=0;
    static Myadapter adapter;
    static int backup[][][];
    static int position_1=-1;

    void mapping1()
    {
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                Game temp=new Game();
                temp.setX(i);
                temp.setY(j);
                if(GamePlayer.array1[i][j]==4){
                    temp.setNum(GamePlayer.array1[i][j]);
                }
                else{
                    backup[0][i][j]=GamePlayer.array[i][j];
                    temp.setNum(GamePlayer.array[i][j]);
                }
                //      temp.setNum(1);
                ar.add(temp);
            }
        }
    }


    ////  mapping1 is invoked only when initialized
    void mapping()
    {
        int k=0;
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                Game temp=new Game();
                temp.setX(i);
                temp.setY(j);
                if(GamePlayer.array1[i][j]==4){
                    temp.setNum(GamePlayer.array1[i][j]);
                }
                else{
                    temp.setNum(GamePlayer.array[i][j]);
                }
                //      temp.setNum(1);
                ar.set(k,temp);
                k++;
            }
        }
    }
    @Override
    public void newgame(int data) {
        if(data==100){
            //Toast.makeText(HumanGame.this,"new game",Toast.LENGTH_SHORT).show();
            GamePlayer.initialize();
            mapping1();
            turn=0;
            red();
            position_1=0;

            int scoreB=GamePlayer.getScoreBlack();
            f2.updateScoreB(scoreB);
            int scoreW=GamePlayer.getScoreWhite();
            f2.updateScoreW(scoreW);
        }
        else if(data==200){
            if(position_1<=0){
                return;
            }

           position_1--;
            GamePlayer.toDefault();
            for(int i1=0;i1<8;i1++){
               for(int j1=0;j1<8;j1++){
                    if(backup[position_1][i1][j1]==4){
                        GamePlayer.array1[i1][j1]=4 ;
                        GamePlayer.array[i1][j1]=1;
                        continue;
                    }
                    GamePlayer.array[i1][j1]=backup[position_1][i1][j1];
                }
             }
            turn--;
            if(turn%2==0){
                int b=GamePlayer.nextMove(2);
            }
            else{
                int b=GamePlayer.nextMove(3);
            }
            int scoreB=GamePlayer.getScoreBlack();
            f2.updateScoreB(scoreB);
            int scoreW=GamePlayer.getScoreWhite();
            f2.updateScoreW(scoreW);
            mapping();
            gv.setAdapter(adapter);
        }
        else if(data==300){
            Intent i=new Intent(HumanGame.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
public void red(){
    ar=new ArrayList<Game>();
    GamePlayer.initialize();
    position_1++;
    for(int i=0;i<8;i++){
        for(int j=0;j<8;j++){
            if(GamePlayer.array1[i][j]==4){
                backup[position_1][i][j]=4;
                continue;
            }
            backup[position_1][i][j]=GamePlayer.array[i][j];
        }
    }

    int l=GamePlayer.nextMove(2);
    turn=0;
    GamePlayer.setContext(HumanGame.this);
    mapping1();

    //  4  => the only buttons can be clicked   ------>green
    //  1=> by default
    //  2=> black
    //  3=>white

    f2.updateScoreB(2);
    f2.updateScoreW(2);
    displayMain();
}

public void displayMain(){
    adapter =new Myadapter(this,ar);
    gv.setAdapter(adapter);

    gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Game temp=ar.get(position);
            if(temp.getNum()!=4  )  //emp.getNum()!=1 &&
            {
                return;
            }
            int x=temp.getX(),y=temp.getY();
            if(turn%2==0)
            {
                GamePlayer.array[x][y]=2;
                GamePlayer.array1[x][y]=0;
                GamePlayer.colorChanged(x,y);

                it1.setIcon(R.drawable.brown3);
            }
            else
            {
                GamePlayer.array[x][y]=3;
                it1.setIcon(R.drawable.black3);

                GamePlayer.array1[x][y]=0;
                GamePlayer.colorChanged(x,y);
            }


            int scoreB=GamePlayer.getScoreBlack();
            f2.updateScoreB(scoreB);
            int scoreW=GamePlayer.getScoreWhite();
            f2.updateScoreW(scoreW);


            GamePlayer.toDefault();
            if(GamePlayer.array[x][y]==2){
                int a=GamePlayer.nextMove(3);

                if (a<0)
                {
                    turn++;
                    AlertDialog.Builder alert = new AlertDialog.Builder(HumanGame.this);
                    int b=GamePlayer.nextMove(2);
                    if(b<0)
                    {
                        //  draw

                      gameOver();
                        return;
                    }

                    alert.setTitle("white is loosing turn");
                    alert.setIcon(R.drawable.brown3);
                    alert.setPositiveButton("Ok",null);alert.show();
                    //   Toast.makeText(HumanGame.this,"black loosing turn -----------\n",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                int a=  GamePlayer.nextMove(2);
                if (a<0)
                {
                    turn++;
                    AlertDialog.Builder alert = new AlertDialog.Builder(HumanGame.this);
                    int b=GamePlayer.nextMove(3);
                    if(b<0)
                    {
                        //  draw  or game over
                       gameOver();
                        return;
                    }

                    alert.setTitle("black is loosing turn");
                    alert.setIcon(R.drawable.black3);
                    alert.setPositiveButton("Ok",null);
                    alert.show();
                    //Toast.makeText(HumanGame.this,"white loosing turn -----------\n",Toast.LENGTH_SHORT).show();
                }
            }
            mapping();
            position_1++;
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(GamePlayer.array1[i][j]==4){
                        backup[position_1][i][j]=4;
                        continue;
                    }
                    backup[position_1][i][j]=GamePlayer.array[i][j];
                }
            }

            //GamePlayer.printArray();
            Myadapter adapter =new Myadapter(getApplicationContext(),ar);
            gv.setAdapter(adapter);
            turn++;
        }
    });

}

public void gameOver(){

    mapping();
    position_1++;
    for(int i=0;i<8;i++){
        for(int j=0;j<8;j++){
            if(GamePlayer.array1[i][j]==4){
                backup[position_1][i][j]=4;
                continue;
            }
            backup[position_1][i][j]=GamePlayer.array[i][j];
        }
    }

    Myadapter adapter =new Myadapter(getApplicationContext(),ar);
    gv.setAdapter(adapter);

    //AlertDialog.Builder builder = new AlertDialog.Builder(HumanGame.this);

    //builder.setView(R.layout.game_finish);
    final Dialog alert=new Dialog(this);
    alert.setContentView(R.layout.game_finish);
    //View v=findViewById(R.id.gamef);
    //alert.findViewById()
    TextView tv1=(TextView) alert.findViewById(R.id.balck_score);
    TextView tv2=(TextView) alert.findViewById(R.id.white_score);
    TextView tv3=(TextView) alert.findViewById(R.id.judge);
    int a=GamePlayer.getScoreBlack();
    tv1.setText(String.valueOf(a));

    int b=GamePlayer.getScoreWhite();
    tv2.setText(b+" ");
    if(a==b){
        tv3.setText("No one ");
    }
    else if(a<b){
        tv3.setText("White ");
    }
    else{
        tv3.setText("Black");
    }
    alert.show();


    updateScoreFun(a,b);
}
public void updateScoreFun(int a,int b){
    int diff= Math.abs(b-a);

    SharedPreferences sp=getSharedPreferences("score",MODE_PRIVATE);
    int blacks=sp.getInt("black",0);
    int whites=sp.getInt("white",0);
    int comp_diff= Math.abs(blacks-whites);
//    Toast.makeText(HumanGame.this,diff+"-"+comp_diff,Toast.LENGTH_SHORT).show();
    if(comp_diff<diff){
        SharedPreferences.Editor spe=sp.edit();
        spe.putInt("black",a);
        spe.putInt("white",b);
        spe.commit();
    }

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_game);

        MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.music);
        mediaPlayer.start();        mediaPlayer.setVolume(1,1);

        mediaPlayer.setLooping(true);
        turn=0;
        backup =new int[65][8][8];
        f2=(ScoreFragment) getSupportFragmentManager().findFragmentById(R.id.frag1);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        gv=(GridView)findViewById(R.id.gridView);

        // gv.hei
        //gv.setColumnWidth(height/8);
        red();
        //menuInflater =getMenuInflater(R.menu.my_menu,me);
    }
    static MenuItem it1;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        it1=menu.getItem(0);
        //menu.
        it1.setIcon(R.drawable.black3);
        it1.setVisible(true);
        //menu.//
        return true;
    }
}
