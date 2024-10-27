package LHB.S1;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class List1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        System.out.println(list instanceof ArrayList<String>);
        //List는 인터페이스이다.
        //List는 구현체 클래스를 가지고 있다.
        List<String> list1 = new ArrayList<>();
        System.out.println(list1 instanceof ArrayList<String>);
        System.out.println(list1 instanceof List<String>);
        //단독으로 인스턴스화가 불가하다.
        //인터페이스는 추상메소드만 가지고 있어서 오른쪽 사용불가함
        //반드시 구현체 클래스가 와야합니다.
        //구현체 클래스가 추상메소드를 구현했기 때문에 올 수 있음
        List<String> list2 = new Vector<>();
    }
}
