package uz.pdp.springdavrtest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName, phone;
    private Double salary;

    @OneToMany(mappedBy = "teacher")
    private List<Group> groupList;


}
