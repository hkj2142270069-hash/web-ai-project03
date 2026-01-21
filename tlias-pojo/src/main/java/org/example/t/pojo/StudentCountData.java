package org.example.t.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCountData {
    private List<String> clazzList;
    private List<Integer> dataList;
}
