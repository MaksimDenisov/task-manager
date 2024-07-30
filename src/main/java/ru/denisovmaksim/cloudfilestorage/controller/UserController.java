package ru.denisovmaksim.cloudfilestorage.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.denisovmaksim.cloudfilestorage.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
}
