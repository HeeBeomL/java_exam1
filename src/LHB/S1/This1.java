package LHB.S1;
class M{
    This1 this1 = null;
    //인스턴스화를 멤버위치에 하는 것은 위험하다.
    //This this2 = new This1() 핑퐁 - 왔다갔다 함 재귀호출

    public M(This1 this1){
        System.out.println("M1 : 멤버" + this.this1 + "지역 : "+this1);
        this.this1 = this1;
        System.out.println("M2 : 멤버" + this.this1 + "지역 : "+this1);
    }
}
public class This1 {
    M m = new M(this);

    public static void main(String[] args) {
        //변수 이름은 같지만 new 생성자 호출을 두 번 했으므로
        //둘은 서로 다른 객체를 참조합니다 (서로 타입은 같다)
        //인스턴스화를 할 때 마다 14번이 호출 된다.
        This1 t1 = new This1();
        System.out.println("main 1 : "+ t1);
        t1 = new This1();//인스턴스화를 할때마다 깊은복사가 이뤄짐
        System.out.println("main 2 : "+ t1);
    }
}
