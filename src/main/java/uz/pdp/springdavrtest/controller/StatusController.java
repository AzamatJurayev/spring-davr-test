package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.Status;
import uz.pdp.springdavrtest.repository.StatusRepository;

import java.util.Optional;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/all")
    public ApiResponse getAll(){
        return ApiResponse.builder().message("mana").success(true).data(statusRepository.findAll()).build();
    }

    @PostMapping
    public ApiResponse saveStatus(@RequestBody Status status){
        Status save = statusRepository.save(status);
        if (save!=null){
            return ApiResponse.builder().data(save).success(true).message("Added!").build();
        }else {
            return ApiResponse.builder().data(save).success(false).message("Failed!").build();
        }
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Long id ,@RequestBody Status status){
        Optional<Status> byId = statusRepository.findById(id);
        if (byId.isPresent()){
            Status status1 = byId.get();
            status1.setName(status.getName());
            status1.setDescription(status.getDescription());
            statusRepository.save(status1);
            return ApiResponse.builder().message("Edited!").success(true).data(status1).build();
        }
        return ApiResponse.builder().message("Error").success(false).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id){
        Optional<Status> byId = statusRepository.findById(id);
        if (byId.isPresent()){
            statusRepository.deleteById(id);
            return ApiResponse.builder().success(true).data(byId.get()).message("Deleted").build();
        }
        return ApiResponse.builder().success(false).message("Not Foundet").build();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Long id){
        if (statusRepository.findById(id).isPresent()) {
            return ApiResponse.builder().data(statusRepository.getOne(id)).message("Mana").success(true).build();
        }
        return ApiResponse.builder().message("Not Fount").success(false).build();

    }
}
