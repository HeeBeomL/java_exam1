package LHB;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

public class TimeServer1 extends  Thread {

    private Socket socket;

    public TimeServer1(Socket s) {//s -> main메소드의 client
        //전역변수와 초기화를 시킴 - nullpointerException 방어함
        //얕은복사 즉 원본을 다른 메소드(run메소드)에서 사용하기 위해서 꼭 필요함
        //소켓변수를 run메소드에서 사용할때 NullpointerException이 발동함
        this.socket = s;
    }
    //run() 시작

    public void run() {//start()호출되면 JVM이 알아서 호출해줌 - 콜백함수
        System.out.println("run call....");
        try {
            //출력을 할때 말하기 할때 사용함 - Printwriter
            //printWriter와 socket 의존관계에 있다.
            //socket이 null이면 PrintWriter객체가 생성 안됨.
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //듣기할 때 필요한 클래스 입니다.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                out.println(getTimeStr());//현재 시간을 읽어와서 출력을 내보냄
                try {
                    sleep(1000);//1초
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("\nClient discoonnected....\n");
            try {
                socket.close();
            } catch (IOException e) {
            }

        }
    }
    //run() 종료

    // getTimeStr() 시작

    private String getTimeStr() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        //삼항 연산자

        return (hour < 10 ? "0" + hour : "" + hour) + ":" +
                (min < 10 ? "0" + min : "" + min) + ":" +
                (sec < 10 ? "0" + sec : "" + sec);

    }
    //getTimeStr() 종료
    //main() 시작

    public static void main(String[] args) {
        int port = 2008;//클라이언트가 접속할 포트 번호 결정
        //주의:서버소켓은 듣고 말하기에 전혀 관여하지 않음
        ServerSocket server = null;//동시에 여러 사용자를 받는 소켓임.
        Socket client = null;//듣고 말하기에 관여하는 소켓
        //이 소켓이 있어야 inputStream과 outputStream를 생성할 수 있음
        //즉 서로 의존관계에 있다(Null)이면 생성이 불가하다.
        //듣기와 말하기 모두 불가함.

        try {
            server = new ServerSocket(port);//파라미터로 2008번을 넘김
        } catch (IOException e) {//예외처리 - 안배움
            //프로그램의 임의 중다늘 막고 예외가 발생하더라도 반드시 해야할 후처리가 있다.
            //후처리를 위해 예외처리는 필수임
            System.out.println("Can't bind port: " + port);
            e.printStackTrace();
            try {
                //사용한 자원에 대해서는 반드시 명시적으로 반납처리함
                //open되어 있으면 해커 불순한 목적으로 위 변조를 할 수 잇음
                server.close();

            } catch (IOException i) {

            }
            System.exit(1);
        }
        System.out.println("Time Server strarted successfully...");
        while (true) {//반복문, 파라미터에 true가 있음 무한반복 서버이기 때문에
            try {


                // 이 지점에서 기다림 발생 사용자가 new Socket(ip,port)할때까지
                //client소켓은 서버측에서 선언하였지만 Timeclient에서 소켓 생성될때
                client = server.accept();//접속이 들어오면 낚아챈다.
                System.out.println("New client connected...");//누가 왔음
                //client는 Socket 타입이다 이것이 있어야 말하고 듣고 할 수 있음
                //생성자 파라미터로 넘기나요? - 다른 사용자 정의 메소드에서 쓰려고

                TimeServer1 tSever = new TimeServer1(client);//생성자 호출
                tSever.start();//스레드가 동작하는 run메소드를 호출해줌
                //사용자가 직접 스레드를 컨트롤 하지 못함. - 보호
                //결합도를 낮추기 위해서임. 결합도가 높으면 재사용성이 떨어진다.
                //결합도가 높으면 단위테스트와 통합테스트가 어려워짐.
                //코드의 양은 늘어나더라도 복잡도가 증가하지 않도록 코딩하기
                //3달후에 코드를 보게되면 코드가 복잡할때 분석할때 힘듬
                //시간 - 자동화,반복되는 코드 줄여서 일괄 처리
                System.out.println("New Time Server Thread started...");
            } catch (IOException e) {
                System.out.println("Can't start server thread!!");
                e.printStackTrace();
                try {
                    client.close();

                } catch (IOException i) {

                }
            }
        }
    }
}
  /*
    프로그램 동작 과정
    1. 서버는 2008번 포트에서 클라이언트 연결을 기다린다.
    server = new ServerSocket(2008); 1~65570사이의 숫자사용함
    1~1023번까지는 윈도우 운영체제가 사용하므로 피한다.
    2.클라이언트가 연결(new socket("172.16.90.45", 2008))되면
    새로운 스레드가 생성되어 해당 클라이언트에게 1초마다 현재 시간을 전송합니다.
    :객체를 생성할 때 서버소캣에 접속을 시도하게 됩니다. 다른 코드는 필요없음
    3.클라이언트가 연결을 종료하면 해당 스레드는 종료됩니다.
    스레드 클래스가 사용자에 대해서 라이프사이클 관리함(생성-활용-죽음)
    4.서버는 새로운 클라잉언트가 연결될때 까지 계속 대기하며
    연결될 때마다 새로운 스레드를 생성합니다.(깊은복사)
     */