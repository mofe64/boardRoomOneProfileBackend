package com.example.boardroomoneprofiles;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class BgRemoveConfig {
    @Value("${bg_remove_secretkey}")
    private String secretKey;
}
