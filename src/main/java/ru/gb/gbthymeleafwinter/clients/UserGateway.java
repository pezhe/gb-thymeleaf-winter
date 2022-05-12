package ru.gb.gbthymeleafwinter.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gb.gbapi.security.UserDto;

public interface UserGateway {

    @PostMapping
    ResponseEntity<UserDto> handlePost(@Validated @RequestBody UserDto userDto);

}
