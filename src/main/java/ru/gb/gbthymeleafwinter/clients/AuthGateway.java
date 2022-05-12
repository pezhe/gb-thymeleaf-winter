package ru.gb.gbthymeleafwinter.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gb.gbapi.security.AuthenticationUserDto;

import java.util.Map;

public interface AuthGateway {

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthenticationUserDto authDto);

}
