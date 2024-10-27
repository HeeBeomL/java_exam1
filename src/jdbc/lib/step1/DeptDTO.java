package jdbc.lib.step1;

public class DeptDTO {
    int deptno;
    String dname;
    String loc;
    DeptDTO() {
        //0, null, null
    }
    DeptDTO(int deptno, String dname, String loc) {
        {
            this.deptno = deptno;
            this.dname = dname;
            this.loc = loc;
        }
    }
}
