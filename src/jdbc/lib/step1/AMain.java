package jdbc.lib.step1;
class A{

}
public class AMain {
    public static void main(String[] args) {
        A[] a = new A[5];
        System.out.println(a);
        System.out.println(a.length);
        for (A a1 : a)
        {
            System.out.println(a1);//5번 출력되지만 null 이 출력
        }

        A a2 = new A();
        System.out.println(a2);//주소번지와 a[0] 주소번지는 같다.
        a[0] = a2;
        System.out.println(a[0]);
        a[0] = new A();
        System.out.println(a[0]);//여기서는 주소번지가 달라짐
    }
}
