package uz.pdp.springdavrtest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    private Teacher teacher;

    private Date startDate;
    private Date endDate;

    @ManyToOne
    private Room room;

    @OneToOne
    private Status status;

    @OneToMany
    @JoinColumn(name = "group_student")
    private List<Student> students;
}
