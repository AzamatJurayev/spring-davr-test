package uz.pdp.springdavrtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springdavrtest.entity.Day;

public interface DayRepository extends JpaRepository<Day,Integer> {
}
