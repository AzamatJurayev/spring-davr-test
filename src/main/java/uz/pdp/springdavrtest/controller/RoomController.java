package uz.pdp.springdavrtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springdavrtest.dto.ApiResponse;
import uz.pdp.springdavrtest.entity.Room;
import uz.pdp.springdavrtest.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

//@Controller //bu faqat web va page qaytaradi
//Api yozish -> rest API
@RestController //json qaytaradi
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;


    @PostMapping
    public ApiResponse saveRoom(@RequestBody Room room) {
        Room save = roomRepository.save(room);
        if (save != null) {
            return ApiResponse.builder().data(save).message("Saved!").success(true).build();
        } else {
            return ApiResponse.builder().data(save).message("Error!").success(false).build();
        }
    }

    @GetMapping("/all")
    public ApiResponse getAll() {
        List<Room> roomList = roomRepository.findAll();
        return ApiResponse.builder().success(true).message("mana").data(roomList).build();
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable(name = "id") Long ketmon) {
        Optional<Room> optionalRoom = roomRepository.findById(ketmon);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            return ApiResponse.builder().data(room).success(true).message("Mana").build();
        } else {
            return ApiResponse.builder().success(false).message("Adashdin!").build();
        }
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Long id, @RequestBody Room room) {
//        roomRepository.findById(id).ifPresent(eski -> {
//            eski.setName(room.getName());
//            eski.setCapacity(room.getCapacity());
//            Room save = roomRepository.save(eski);
//        });
//        return ApiResponse.builder().data(save).success(true).message("Mana").build();
        Room room1 = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("room not found"));
        //
        room1.setCapacity(room.getCapacity());
        room1.setName(room.getName());
        Room save = roomRepository.save(room1);
        return ApiResponse.builder().data(save).success(true).message("Mana").build();
    }


    @DeleteMapping
    public ApiResponse delete(@PathVariable Long id) {
        roomRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Mana").build();
    }
}
