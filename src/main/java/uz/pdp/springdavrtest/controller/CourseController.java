package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.Course;
import uz.pdp.springdavrtest.repository.CourseRepository;

import java.util.Optional;


@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/all")
    public ApiResponse getAll(){
        return ApiResponse.builder().message("mana").success(true).data(courseRepository.findAll()).build();
    }

    @PostMapping
    public ApiResponse saveCourse(@RequestBody Course course){
        Course save = courseRepository.save(course);
        if (save!=null){
            return ApiResponse.builder().data(save).success(true).message("Added!").build();
        }else {
            return ApiResponse.builder().data(save).success(false).message("Failed!").build();
        }
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Long id ,@RequestBody Course course){
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isPresent()){
            Course course1 = byId.get();
            course1.setName(course.getName());
            courseRepository.save(course1);
            return ApiResponse.builder().message("Edited!").success(true).data(course1).build();
        }
        return ApiResponse.builder().message("Error").success(false).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id){
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isPresent()){
            courseRepository.deleteById(id);
            return ApiResponse.builder().success(true).data(byId.get()).message("Deleted").build();
        }
        return ApiResponse.builder().success(false).message("Not Foundet").build();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Long id){
        if (courseRepository.findById(id).isPresent()) {
            return ApiResponse.builder().data(courseRepository.getOne(id)).message("Mana").success(true).build();
        }
        return ApiResponse.builder().message("Not Fount").success(false).build();

    }
}
