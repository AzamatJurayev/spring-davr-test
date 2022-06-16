package uz.pdp.springdavrtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springdavrtest.entity.Course;
import uz.pdp.springdavrtest.entity.Student;


public interface StudentRepository extends JpaRepository<Student,Long> {
}
