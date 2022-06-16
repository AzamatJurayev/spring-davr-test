package uz.pdp.springdavrtest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.springdavrtest.entity.Room;
import uz.pdp.springdavrtest.repository.RoomRepository;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoomRepository roomRepository;
    @Value("${spring.sql.init.mode}")
    String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            roomRepository.save(new Room(1L, "314", 20));
            roomRepository.save(new Room(2L, "313", 20));
            roomRepository.save(new Room(3L, "312", 20));
            roomRepository.save(new Room(4L, "311", 20));
        }
    }
}
