package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.Day;
import uz.pdp.springdavrtest.repository.DayRepository;

import javax.persistence.PostUpdate;
import java.util.Optional;

@RestController
@RequestMapping("/day")
public class DayController {
    @Autowired
    DayRepository dayRepository;

    @GetMapping("/all")
    public ApiResponse getAll(){
        return ApiResponse.builder().message("mana").success(true).data(dayRepository.findAll()).build();
    }

    @PostMapping
    public ApiResponse saveDay(@RequestBody Day day){
        Day save = dayRepository.save(day);
        if (save!=null){
            return ApiResponse.builder().data(save).success(true).message("Added!").build();
        }else {
            return ApiResponse.builder().data(save).success(false).message("Failed!").build();
        }
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id ,@RequestBody Day day){
        Optional<Day> byId = dayRepository.findById(id);
        if (byId.isPresent()){
            Day day1 = byId.get();
            day1.setName(day.getName());
            dayRepository.save(day1);
            return ApiResponse.builder().message("Edited!").success(true).data(day1).build();
        }
        return ApiResponse.builder().message("Error").success(false).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        Optional<Day> byId = dayRepository.findById(id);
        if (byId.isPresent()){
            dayRepository.deleteById(id);
            return ApiResponse.builder().success(true).data(byId.get()).message("Deleted").build();
        }
        return ApiResponse.builder().success(false).message("Not Foundet").build();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Integer id){
        if (dayRepository.findById(id).isPresent()) {
            return ApiResponse.builder().data(dayRepository.getOne(id)).message("Mana").success(true).build();
        }
        return ApiResponse.builder().message("Not Fount").success(false).build();

    }
}
