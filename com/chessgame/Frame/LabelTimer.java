package com.chessgame.Frame;

import com.chessgame.Game.Game;

import javax.swing.*;
import java.awt.*;

public class LabelTimer extends JLabel {
    public int time;
    public Timer timer;

    public LabelTimer(int time_in_minutes , int time_in_seconds){

        this.setFont(new Font("mv boli",Font.BOLD,24));
        this.setForeground(new Color(250,210,180));

        time = time_in_minutes*60 + time_in_seconds;
        time = time(time);
        timer = new Timer(1000 , e -> {
            time = time(--time);
            Game.board.continueGame(Game.player);
            if(time==0) {
                timer.stop();
                Game.timeOut();
            }
        });

    }


    public LabelTimer(){}

    //returns time in seconds
    public int time(int time){
        this.setText(time/60+" : "+ (time%60<10 ? "0"+time%60 : time%60));
        return time;
    }
}


