package uz.pdp.springdavrtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springdavrtest.entity.Course;
import uz.pdp.springdavrtest.entity.Status;


public interface StatusRepository extends JpaRepository<Status,Long> {
}
