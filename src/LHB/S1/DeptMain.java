package LHB.S1;

class DeptDTO{
    int deptno;
    String dname;
    String loc;

    DeptDTO(){}

    DeptDTO(int deptno, String dname, String loc){
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }
}
public class DeptMain {
    public static void main(String[] args) {
        DeptDTO[] depts = new DeptDTO[3];
        DeptDTO dto = new DeptDTO(10, "총무부", "인천");
        System.out.println(dto);
        //주소번지는 언제 바뀌나?
        depts[0] = dto;
        System.out.println(depts[0].deptno);
        System.out.println(depts[0].dname);
        System.out.println(depts[0].loc);
        //주소번지가 계속 같은 번지를 유지하고 있다.
        dto = new DeptDTO(20,"영업부","서울");
        //여기서 주소번지가 바뀜.
        depts[1] = dto;
        System.out.println(dto);
        System.out.println(depts[1].deptno);
        System.out.println(depts[1].dname);
        System.out.println(depts[1].loc);

        dto = new DeptDTO(30,"인사과", "부산");
        depts[2] = dto;
        System.out.println(dto);
        System.out.println(depts[2].deptno);
        System.out.println(depts[2].dname);
        System.out.println(depts[2].loc);

    }


}
