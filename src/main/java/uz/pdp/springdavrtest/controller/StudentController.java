package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.Student;
import uz.pdp.springdavrtest.repository.StudentRepository;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/all")
    public ApiResponse getAll(){
        return ApiResponse.builder().message("mana").success(true).data(studentRepository.findAll()).build();
    }

    @PostMapping
    public ApiResponse saveStudent(@RequestBody Student student){
        Student save = studentRepository.save(student);
        if (save!=null){
            return ApiResponse.builder().data(save).success(true).message("Added!").build();
        }else {
            return ApiResponse.builder().data(save).success(false).message("Failed!").build();
        }
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Long id ,@RequestBody Student student){
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()){
            Student student1 = byId.get();
            student1.setFullName(student.getFullName());
            student1.setPhone(student.getPhone());
            studentRepository.save(student1);
            return ApiResponse.builder().message("Edited!").success(true).data(student1).build();
        }
        return ApiResponse.builder().message("Error").success(false).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id){
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()){
            studentRepository.deleteById(id);
            return ApiResponse.builder().success(true).data(byId.get()).message("Deleted").build();
        }
        return ApiResponse.builder().success(false).message("Not Foundet").build();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Long id){
        if (studentRepository.findById(id).isPresent()) {
            return ApiResponse.builder().data(studentRepository.getOne(id)).message("Mana").success(true).build();
        }
        return ApiResponse.builder().message("Not Fount").success(false).build();

    }
}
