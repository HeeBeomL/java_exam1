package LHB.S1;

import java.util.ArrayList;
import java.util.List;

class A{

}

public class List2 {
    public static void main(String[] args) {
        List<A> list = new ArrayList<>();
        System.out.println(list.size());//0
        A a= new A();
        list.add(a);
        a = new A();
        list.add(a);
        for (A a1 : list){
            System.out.println(a1);//주소번지가 달라짐
        }
    }
}
