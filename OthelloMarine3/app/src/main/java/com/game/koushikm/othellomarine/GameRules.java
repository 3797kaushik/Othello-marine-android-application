package com.game.koushikm.othellomarine;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class GameRules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules);

        MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.music);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        String detail[]={"\n" +
                "Each of the disks' two sides corresponds to one player; they are referred to here as light and dark after the sides of Othello pieces, but any counters with distinctive faces are suitable. The game may for example be played with a chessboard and Scrabble pieces, with one player letters and the other backs.\n" +
                "\n" +
                "For the specific game of Othello (as technically differing from the historical Reversi), the rules state that the game begins with four disks placed in a square in the middle of the grid, two facing white side up, two pieces with the dark side up, with same-colored disks on a diagonal with each other. Convention has initial board position such that the disks with dark side up are to the north-east and south-west (from both players' perspectives), though this is only marginally meaningful to play (where opening memorization is an issue, some players may benefit from consistency on this). If the disks with dark side up are to the north-west and south-east, the board may be rotated by 90° clockwise or counterclockwise. The dark player moves first.\n",
                "Dark must place a piece with the dark side up on the board, in such a position that there exists at least one straight (horizontal, vertical, or diagonal) occupied line between the new piece and another dark piece, with one or more contiguous light pieces between them. In the below situation, dark has the following options indicated by translucent pieces:",
                "After placing the piece, dark turns over (flips, captures) all light pieces lying on a straight line between the new piece and any anchoring dark pieces. All reversed pieces now show the dark side, and dark can use them in later moves—unless light has reversed them back in the meantime. In other words, a valid move is one where at least one piece is reversed.\n" +
                        "\n" +
                        "If dark decided to put a piece in the topmost location (all choices are strategically equivalent at this time), one piece gets turned over, so that the board appears thus:","Now light plays. This player operates under the same rules, with the roles reversed: light lays down a light piece, causing a dark piece to flip. Possibilities at this time appear thus (indicated by transparent pieces):","Light takes the bottom left option and reverses one piece:","Players take alternate turns. If one player can not make a valid move, play passes back to the other player. When neither player can move, the game ends. This occurs when the grid has filled up or when neither player can legally place a piece in any of the remaining squares. This means the game may end before the grid is completely filled. This possibility may occur because one player has no pieces remaining on the board in that player's color. In over-the-board play this is generally scored as if the board were full (64–0).\n" +
                "\n" +
                "Example where the game ends before the grid is completely filled:","The player with the most pieces on the board at the end of the game wins. An exception to this is that if a clock is employed then if one player defaults on time that player's opponent wins regardless of the board configuration, with varying methods to determine the official score where one is required."};

        int imagesid[]={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6,R.mipmap.ic_launcher};
        ListView lv=(ListView)findViewById(R.id.lv);
        ArrayList<Person> al=new ArrayList<Person>();
        for(int i=0;i<7;i++)
        {
            Person p=new Person();
            p.setDetail(detail[i]);
            p.setImage(imagesid[i]);
            al.add(p);
        }
        MyAdapter1 mad= new MyAdapter1(al,this);
        lv.setAdapter(mad);
    }


}
