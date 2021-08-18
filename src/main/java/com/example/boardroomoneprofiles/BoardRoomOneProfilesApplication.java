package com.example.boardroomoneprofiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BoardRoomOneProfilesApplication {
    @GetMapping("")
    public String startUp(){
        return "Backend serve up and running .... please view read me at https://github.com/mofe64/boardRoomOneProfileBackend/blob/main/README.md";
    }

    public static void main(String[] args) {
        SpringApplication.run(BoardRoomOneProfilesApplication.class, args);
    }

}
