package com.game.koushikm.othellomarine;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ComputerGame extends AppCompatActivity implements FooterFragment.newGameListener {

    static GridView gv;
    static ArrayList<Game> ar;
    static ScoreFragment f2;
    static int turn=0;
    static Myadapter adapter;
    static String level="",computerRole="";
    static int backup[][][];
    static AlertDialog alertDialog,alertDialog1;
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
                ar.add(temp);
            }
        }
    }


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
           Intent i=new Intent(this,ComputerGame.class);
            startActivity(i);
            finish();
        }
        else if(data==200){
            if(position_1-1<=0){
                return;
            }

            position_1--;
            position_1--;
            GamePlayer.toDefault();
            for(int i1=0;i1<8;i1++){
                for(int j1=0;j1<8;j1++){
                    if(backup[position_1][i1][j1]==4){
                        GamePlayer.array1[i1][j1]=4 ;
                        GamePlayer.array[i1][j1]=1 ;
                        continue;
                    }
                    GamePlayer.array[i1][j1]=backup[position_1][i1][j1];
                }
            }
            turn--;
            int scoreB=GamePlayer.getScoreBlack();
            f2.updateScoreB(scoreB);
            int scoreW=GamePlayer.getScoreWhite();
            f2.updateScoreW(scoreW);
            mapping();
            gv.setAdapter(adapter);
        }
        else if(data==300){
            Intent i=new Intent(ComputerGame.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
    public void red(){
        gv=(GridView)findViewById(R.id.gridView);
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
        GamePlayer.setContext(ComputerGame.this);
        mapping1();

        //  4  => the only buttons can be licked   ------>green
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
                if(computerRole.equals("white"))
                {
                    GamePlayer.array[x][y]=2;
                    GamePlayer.array1[x][y]=0;
                    GamePlayer.colorChanged(x,y);
                    it1.setIcon(R.drawable.brown3);
                    //nextMove();
                    int r=8000;
                    RunAfterGame(x,y,r);
                    if(GamePlayer.nextMove(3)>=0){
                    computersTurn(3);}
                }
                else
                {
                    GamePlayer.array[x][y]=3;
                    it1.setIcon(R.drawable.black3);
                    GamePlayer.array1[x][y]=0;
                    GamePlayer.colorChanged(x,y);
                    int r=8000;
                    RunAfterGame(x,y,r);
                    //computersTurn(2);

                    if(GamePlayer.nextMove(2)>=0){
                        computersTurn(2);}
                }
            }
        });
    }
    public void RunAfterGame(int x,int y,int flag){
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
                AlertDialog.Builder alert = new AlertDialog.Builder(ComputerGame.this);
                int b=GamePlayer.nextMove(2);

                if(b<0)
                {
                    gameOver();
                    return;
                }
                alert.setTitle("white is loosing turn");
                alert.setIcon(R.drawable.brown3);
                alert.setPositiveButton("Ok",null);alert.show();

                if (!computerRole.equals("white")) {
                    computersTurn(3);
                 //   RunAfterGame(x,y,0);
                }
            }
            //computersTurn(2);
        }
        else{
            int a=  GamePlayer.nextMove(2);
            if (a<0)
            {
                turn++;
                AlertDialog.Builder alert = new AlertDialog.Builder(ComputerGame.this);
                int b=GamePlayer.nextMove(3);
                if(b<0)
                {
                    gameOver();
                    return;
                }
                alert.setTitle("black is loosing turn");
                alert.setIcon(R.drawable.black3);
                alert.setPositiveButton("Ok",null);
                alert.show();


                if (!computerRole.equals("black")) {
                    computersTurn(2);
                 //   RunAfterGame(x,y,0);
                }
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
        Myadapter adapter =new Myadapter(getApplicationContext(),ar);
        gv.setAdapter(adapter);
        turn++;
    }
    //////////////////////////////////////
    public void mediumLevel(int key){
        int i,j,x=0,y=0;
        int flag=0;
        for(i=0;i<8;i++){
            for(j=0;j<8;j++){
                if(GamePlayer.array1[i][j]==4){
                    GamePlayer.array1[i][j]=0;
                    GamePlayer.array[i][j]=key;
                    putIcon(key);
                    GamePlayer.colorChanged(i,j);
                    x=i;y=j;
                    flag=1;
                    break;
                }
            }
            if(flag==1){
                break;
            }
        }
        RunAfterGame(x,y,0);
    }
public class easyGame{
    int i,j;
}
////////////////////////////////////////
    public void hardLevel(int key){
        int i=0,j=0,x=0,y=0;
        int flag=0;
        while(i<8){
            j=0;
            while(j<8){
                int i1=7-i,j1=7-j;
                if(GamePlayer.array1[i][j]==4){
                    GamePlayer.array1[i][j]=0;
                    GamePlayer.array[i][j]=key;
                    putIcon(key);
                    GamePlayer.colorChanged(i,j);
                    x=i;y=j;
                    flag=1;
                    break;
                }
                else if(GamePlayer.array1[i1][j1]==4){
                    i=i1;j=j1;
                    GamePlayer.array1[i][j]=0;
                    GamePlayer.array[i][j]=key;
                    putIcon(key);
                    GamePlayer.colorChanged(i,j);
                    x=i;y=j;
                    flag=1;
                    break;
                }
                j++;
            }
            if(flag==1){
                break;
            }
            i++;
        }
        RunAfterGame(x,y,0);
    }
    public void putIcon(int key){
        if(key==2){
            it1.setIcon(R.drawable.brown3);
        }
        else{
            it1.setIcon(R.drawable.black3);
        }
    }

    //////////////////////////////////////
    public void easyLevel(int key){
        int i,j,x=0,y=0;
        ArrayList<easyGame> al=new ArrayList<easyGame>();
        for(i=0;i<8;i++){
            for(j=0;j<8;j++){
                if(GamePlayer.array1[i][j]==4){
                         easyGame temp =new easyGame();
                    temp.i=i;
                    temp.j=j;
                    al.add(temp);
                }
            }
        }
        /*----------------------------------------*/
        int ind=(int) Math.random()*al.size();
        easyGame e=al.get(ind);
        i=e.i;
        j=e.j;
        GamePlayer.array1[i][j]=0;
        GamePlayer.array[i][j]=key;
        putIcon(key);
        GamePlayer.colorChanged(i,j);
        x=i;y=j;
        /*-----------------------------------------*/
        RunAfterGame(x,y,0);
    }
///////////////////////////////////////////////////////////////////////////
    public void computersTurn(int key){
        if(level.equals("medium")){
            mediumLevel(key);
        }
        else if(level.equals("easy")){
            easyLevel(key);
        }
        else{
            hardLevel(key);
        }
        /////////////////////////////------------------------
    }
    static MenuItem it1;
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        it1=menu.getItem(0);
        //menu.
        it1.setIcon(R.drawable.black3);
        it1.setVisible(true);
        //menu.//
        return true;
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_game);
        backup =new int[65][8][8];
        level="";
            turn=0;

            MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.music);
            mediaPlayer.start();        mediaPlayer.setVolume(1,1);

            mediaPlayer.setLooping(true);
        computerRole="";
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(R.layout.choose_level);
        alertDialog=builder.create();
        alertDialog.show();
        alertDialog.setOnCancelListener( new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if(level.equals(""))
                alertDialog.show();
                alertDialog.cancel();
            }
        });

        f2=(ScoreFragment) getSupportFragmentManager().findFragmentById(R.id.frag1);
        red();
    }
    public void choose_player(){
        alertDialog.dismiss();
        alertDialog.cancel();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(R.layout.choose_player);
        alertDialog1=builder.create();
        alertDialog1.show();
        alertDialog1.setOnCancelListener( new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if(computerRole.equals(""))
                    alertDialog1.show();
                alertDialog1.cancel();
            }
        });
        /**********************************************/
        alertDialog1.show();

       }
    public void  easy_but(View v){
        level="easy";
        choose_player();
    }
    public void  medium_but(View v){
        level="medium";
        choose_player();
    }
    public void  hard_but(View v){
        level="hard";
        choose_player();
    }
    public void black_but(View v){
        computerRole="black";
        alertDialog1.dismiss();
        alertDialog1.cancel();
           Toast.makeText(ComputerGame.this,level+"-------"+computerRole, Toast.LENGTH_SHORT).show();
        computersTurn(2);
    }
    public void white_but(View v){
        computerRole="white";
     //   Toast.makeText(ComputerGame.this,"white",Toast.LENGTH_SHORT).show();
        alertDialog1.dismiss();
        alertDialog1.cancel();
        Toast.makeText(ComputerGame.this,level+"-------"+computerRole, Toast.LENGTH_SHORT).show();

    }

}
