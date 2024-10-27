package LHB.S1;
class T{
    int a= 1;
    T(){
        System.out.println("T()디폴트 생성자 호출 성공");
    }
    T(Test1 t1){
        System.out.println("파라미터가 있는 생성자 호출 성공");
        System.out.println(this.a);
    }
}
public class Test1 {
    T t = new T(this);
    public static void main(String[] args) {

        Test1 t2 = new Test1();
        System.out.println(t2.t.a);
        t2.t.a = 10;
        System.out.println(t2.t.a);
        //생성자 두번 출력
        t2 = new Test1();
        System.out.println(t2.t.a);

    }
}
