package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.PayType;
import uz.pdp.springdavrtest.repository.PayTypeRepository;

import java.util.Optional;

@RestController
@RequestMapping("/paytype")
public class PayTypeController {
    @Autowired
    PayTypeRepository payTypeRepository;

    @GetMapping("/all")
    public ApiResponse getAll(){
        return ApiResponse.builder().message("mana").success(true).data(payTypeRepository.findAll()).build();
    }

    @PostMapping
    public ApiResponse savePayType(@RequestBody PayType payType){
        PayType save = payTypeRepository.save(payType);
        if (save!=null){
            return ApiResponse.builder().data(save).success(true).message("Added!").build();
        }else {
            return ApiResponse.builder().data(save).success(false).message("Failed!").build();
        }
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Long id ,@RequestBody PayType payType){
        Optional<PayType> byId = payTypeRepository.findById(id);
        if (byId.isPresent()){
            PayType payType1 = byId.get();
            payType1.setName(payType.getName());
            payTypeRepository.save(payType1);
            return ApiResponse.builder().message("Edited!").success(true).data(payType1).build();
        }
        return ApiResponse.builder().message("Error").success(false).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id){
        Optional<PayType> byId = payTypeRepository.findById(id);
        if (byId.isPresent()){
            payTypeRepository.deleteById(id);
            return ApiResponse.builder().success(true).data(byId.get()).message("Deleted").build();
        }
        return ApiResponse.builder().success(false).message("Not Foundet").build();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Long id){
        if (payTypeRepository.findById(id).isPresent()) {
            return ApiResponse.builder().data(payTypeRepository.getOne(id)).message("Mana").success(true).build();
        }
        return ApiResponse.builder().message("Not Fount").success(false).build();

    }
}
