package LHB;

import javax.swing.*;
import java.awt.*;

public class TimerView extends JFrame {

    JLabel jlb_timer = new JLabel("현재시간", JLabel.CENTER);

    Font f = new Font("굴림체", Font.BOLD, 40);

    public TimerView(){
        initDisplay();
    }

    public void initDisplay(){
        jlb_timer.setFont(f);
        this.add("Center", jlb_timer);
        this.setSize(400,200);
        this.setVisible(true);


        TimeClient1 tc1 = new TimeClient1(jlb_timer);
        tc1.start();
    }

    public static void main(String[] args) {
        new TimerView();
    }



}
