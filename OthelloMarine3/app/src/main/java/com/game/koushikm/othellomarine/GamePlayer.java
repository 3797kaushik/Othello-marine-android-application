package com.game.koushikm.othellomarine;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Koushik M on 3/15/2017.
 */

public class GamePlayer {
    static Context ct;

    public static  void setContext(Context ct1){ct =ct1;}
    public   static int array[][]=new int[8][8];
    public   static int array1[][]=new int[8][8];
    GamePlayer()
    {
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                array[i][j]=1;
                array1[i][j]=0;
            }
        }
    }
    static void initialize()
    {
        ArrayList<Integer> al=new ArrayList<Integer>();
        int k=0;
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                array[i][j]=1;
                array1[i][j]=0;
            }
        }
        array[3][4]=2;
        array[3][3]=3;
        array[4][4]=3;
        array[4][3]=2;
    }
    static  int  nextMove(int key)    {
        int i,j,k=-1;
        boolean t=false;
        for(i=0;i<8;i++){
            for(j=0;j<8;j++){
                if(array[i][j]==1){

                    t=_verticalUp(key,i,j) || _verticalDown(key,i,j) || _horizontalLeft(key,i,j)  || _horizontalRight(key,i,j);
                    t= t || _upLeft(key,i,j) || _upRight(key,i,j)  || _downLeft(key,i,j) || _downRight(key,i,j) ;
                    if(t==true){
                        array1[i][j]=4;
                        k++;
                    }
                }
            }
        }
        return k;
    }


    //  4  => the only buttons can be licked   ------>green
    //  1 => by default
    //  2 => black
    //  3 => white

    static boolean _downRight(int key,int i,int j){
        if(i==0 ||j==7){return false;}
        else {
            int x=i-1,y=j+1;
            if(array[x][y]==key || array[x][y]==1){
                return false;
            }
            while (x>=0 && y<=7 && array[x][y]!=key && array[x][y]!=1 ){
                x--;y++;
            }
            if(x<0){
                return false;
            }
            if(y>7){
                return false;
            }
            return !(array[x][y] == 1 || array[x][y] != key);
        }
    }
    static boolean _downLeft(int key,int i,int j){
        if (i==7 || j==0){return false;}
        else {
            int x=i+1,y=j-1;
            if(array[x][y]==key ||array[x][y]==1){
                return false;
            }
            while ((x<=7 && y>=0) && array[x][y]!=key && array[x][y]!=1){
                x++;y--;
            }
            if(x>7){
                return false;
            }
            if(y<0){
                return false;
            }
            if(array[x][y]==1){return false;}
            return array[x][y] == key;
        }
    }

    static  boolean _upRight(int key,int i,int j){
        if(i==0 || j==0){ return false;}
        else {
            int x=i-1,y=j-1;
            if(array[x][y]==key || array[x][y]==1){return false;}
            while(x>=0 && y>=0 && array[x][y]!=key  && array[x][y]!=1){
                x--;y--;
            }
            if(y<0 ||x<0){return false;}
            return !(array[x][y] == 1 || array[x][y] != key);
        }
    }

    static  boolean _upLeft(int key,int i,int j){
        if(i==7 || j==7){            return false;        }
        else {
            int x=i+1,y=j+1;
            if(array[x][y]==key ||array[x][y]==1 ){
                return false;
            }
            while (  ((x<=7 && y<=7))&& array[x][y]!=key && array[x][y]!=1){
                x++;y++;
                if(x>7 || y>7){
                    return false;
                }
            }
            if(x>7 || y>7){
                return false;
            }
            if(array[x][y]==1 ){  return false;            }
            return array[x][y] == key;
        }
    }

    static  boolean _horizontalRight(int key,int i, int j){
        if(j==0){return false;}
        else{
            int x=i,y=j-1;
            if (array[x][y]==key || array[x][y]==1){
                return false;
            }
            while (y>=0 && array[x][y]!=key && array[x][y]!=1 ){
                y--;
            }
            if(y<0){
                return false;
            }
            if(array[x][y]==1) {return false;}
            return array[x][y] == key;
        }
    }
    static  boolean _horizontalLeft(int key,int i,int j){
        if(j==7){
            return false;
        }
        else{
            int x=i,y=j+1;
            if(array[x][y]==key || array[x][y]==1 || y>=7){
                return false;
            }
            while (  y<=7 && array[x][y]!=key && array[x][y]!=1 ){
                y++;
            }
            if(y>7){
                return false;
            }
            if(array[x][y]==1){
                return false;
            }
            return array[x][y] == key;
        }
    }

    static  boolean _verticalUp(int key,int i,int j){
        if(i==0){
            return false;
        }
        else{
            int x=i-1,y=j;
            if(array[x][y]==key || array[x][y]==1 ){
                return false;
            }
            while (array[x][y]!=key && array[x][y]!=1){
                if (x<=0){return false;}
                x--;
            }
            if(array[x][y]==1 ){
                return false;
            }
            return !(x < 0 || array[x][y] != key);
        }
    }

    static boolean _verticalDown(int key,int i,int j){
        if(i==7){
            return false;
        }
        else {
            int x=i+1,y=j;
            if(x>=7){
                return false;
            }
            if(array[x][y]==key ||  array[x][y]==1 ){
                return false;
            }
            while (array[x][y]!=key && array[x][y]!=1 && x<=7 ) {
                x++;
                if (x > 7) {
                    return false;
                }
            }
                if(array[x][y]==1){
                    return false;
                }

            return !(x >= 7 && array[x][y] != key);
        }
//        return false;
    }

    public static  void toDefault()
    {
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                array1[i][j]=0;
            }
        }
    }

    static  void printArray(){
        String s="";

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
               s=s+array[i][j]+" ";
            }
            s=s+"\n";
        }
       Toast.makeText(ct,s, Toast.LENGTH_SHORT).show();
        Log.d("1---",s);
        s="";

        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                s=s+array1[i][j]+" ";
            }
            s=s+"\n";
        }
      Toast.makeText(ct,s, Toast.LENGTH_SHORT).show();
        Log.d("2==---",s);
    }
    static void colorChanged(int i,int j)
    {
        int key=array[i][j];
        horizontalRight(key,i,j);
        horizontalLeft(key,i,j);
        verticalUp(key,i,j);
        verticalDown(key,i,j);
        /////////////////////////////////
        upLeft(key,i,j);
        upRight(key,i,j);
        downLeft(key,i,j);
        downRight(key,i,j);

    }
    public  static  void downRight(int key,int a,int b){
        if (a==0 ||b==7 ||array[a-1][b+1]==key || array[a-1][b+1]==1){return;}
        int x=a-1,y=b+1,i=0;
        while(x>0 && y<7 &&array[x][y]!=key) {x--;y++;i++;
            if(array[x][y]==1) {return;}}
        if((y>7 || x<0)){return;}
        if(array[x][y]==1) {return;}
       if(key!=array[x][y]){return;}

        while(i>0){
            i--;x++;y--;
            array[x][y]=key;
        }
    }

    public  static void downLeft(int key,int a,int b){
        if(a==7 || b==0 || array[a+1][b-1]==key || array[a+1][b-1]==1) {return; }
        int x=a+1,y=b-1,i=0;
        while ((x<7 && y>0) && array[x][y]!=key) {x++;y--;i++;}
        if(array[x][y]==1) {return;}

        if((x>7)||(y<0) ){return;}
        if(key!=array[x][y] ){return;}

        while(i>0){
            i--;x--;y++;
            array[x][y]=key;
        }
    }

    public static int getScoreBlack() {
        int i, j, k = 0;
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if (array[i][j] == 2) {
                    k++;
                }
            }
        }
        return k;

    }
    public static int getScoreWhite(){
        int i,j,k=0;
        for(i=0;i<8;i++)
        {
            for(j=0;j<8;j++)
            {
                if(array[i][j]==3)
                {
                    k++;
                }
            }
        }
        return k;
    }

    public static void upRight(int key,int a,int b)    {
        if(a==7 || b==7 ||array[a+1][b+1]==1 ||array[a+1][b+1]==key) { return;}
        int x=a+1,y=b+1,i=0;
        while((x<7 && y<7) && array[x][y]!=key ) {x++;y++;i++; if(array[x][y]==1) {return;}}
        if(array[x][y]==1) {return;}

        if((y>=7 || x>=7) && key!=array[x][y]){return;}
        while (i>0){
            i--;x--;y--;
            array[x][y]=key;
        }
    }

    public static void upLeft(int key,int a,int b) {
        if(a==0 || b==0 ||array[a-1][b-1]==key ||array[a-1][b-1]==1) { return;}

        int x=a-1,y=b-1,i=0;
        while((x>0 && y>0) && array[x][y]!=key)
        {
            x--;y--;i++;
            if(array[x][y]==1)
            {
                return;
            }
        }
        if(array[x][y]==1)
        {
            return;
        }
        if(((y<=0 || x<=0 )&& key!=array[x][y])  ){return;}

        while (i>0){
            i--;x++;y++;
            array[x][y]=key;
        }
    }

    public static void verticalDown(int key,int a,int b){
        int x=a+1,i=0,y=b;
        if(a==7 || array[a+1][b]==key || array[a+1][b]==1) {return;}

        while(x<7 && array[x][y]!=key){
            x++;i++;
            if(array[x][y]==1){ return;}
        }
        if(x>=7 && key!=array[x][y]){return;}

        while (i>0){
            i--;x--;
            array[x][y]=key;
        }
    }

    public static void verticalUp(int key,int a,int b){
        int x=a-1,y=b,i=0;
        if(a==0 ||(array[a-1][b]==key) || array[a-1][b]==1) {return;}

        while(x>0 && array[x][y]!=1 && array[x][y]!=key){
            x--;i++;
            if(array[x][y]==1){
                return;
            }
        }
        if(array[x][y]==1 || (x<=0 && key!=array[x][y])){  return;  }
        while(i>0){
            x++;i--;
            array[x][y]=key;
        }
    }

    public static void horizontalLeft(int key,int a,int b){
        int x=a,y=b+1,i=0;
        if(b==7 ||array[a][b+1]==key || array[a][b+1]==1) {return;}

        while(y<7 && array[x][y]!=key){
            y++;i++;
            if(array[x][y]==1) {return;}
        }
        if(array[x][y]==1 || (y>=7 && key !=array[x][y])) {return;}

        while (i>0)
        {
            i--;y--;
            array[x][y]=key;
        }
    }

    public static void horizontalRight(int key, int a, int b) {
     int i=0,y=b-1,x=a;
        if(b==0) {  return; }
        if(array[a][b-1]==key || array[a][b-1]==1) {return ;}

        while(y>0 && array[x][y]!=key)
        {
            y--;i++;
            if(array[x][y]==1)
            {
                return;
            }
        }
        if(array[x][y]==1 || (y<=0 && key!=array[x][y]))
        {
            return;
        }
        while(i>0)
        {
            i--;y++;
            array[x][y]=key;
        }
    }
}
