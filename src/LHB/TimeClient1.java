package LHB;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient1 extends  Thread{
    //다른 클래스의 화면에 TimeClinet1이 청취한 현재 시분초 문자열을
    //출력하는 문제이므로 JLabel UI클래스를 선언했다(얕으복사)
    //private JLabel label;
    private JLabel jlb_timer = null;
    public TimeClient1(){}
    public TimeClient1(JLabel jlb_timer) {
        this.jlb_timer = jlb_timer;
        //this.jlb_timer = new JLabel("깊은복사했을때");
        //파라미터를 갖는 생성자 추가해야 합니다와 연결되어 있다.
        //파라미터에 자리에 필요한 타입도 결정할 수 있다면... + 파라미터 갯수도 결정해보기
    }

    public void run() {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String timeStr = null;

        try {
            socket = new Socket("192.168.0.6", 2008);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                System.out.println("while(true)");
                while ((timeStr = in.readLine()) != null) {
                    System.out.println(timeStr);
                    jlb_timer.setText(timeStr);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException i) {

                }
            }
        } catch (IOException i) {
            //label.setText("타임서버에 접속할 수 없습니다.");
            jlb_timer.setText("타임서버에 접속할 수 없습니다.");
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {

            }
        }
    }

        public static void main (String[]args){
            TimeClient1 tc = new TimeClient1();
            tc.start();
        }
    }
