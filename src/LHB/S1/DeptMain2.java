package LHB.S1;

import java.util.ArrayList;
import java.util.List;

public class DeptMain2 {
    public static void main(String[] args) {
        List<DeptDTO> list = new ArrayList<>();
        DeptDTO dtos2 = new DeptDTO();
        System.out.println(list.size());
        list.add(dtos2);
        System.out.println(list.size());
        dtos2 = new DeptDTO();
        list.add(dtos2);
        dtos2 = new DeptDTO();
        list.add(dtos2);
        for (DeptDTO dto:list){
            System.out.println(dto);
        }

    }
}
