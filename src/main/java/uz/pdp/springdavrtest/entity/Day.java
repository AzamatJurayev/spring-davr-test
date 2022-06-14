package uz.pdp.springdavrtest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "day")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial
    private Integer id;

    private String name;

}
