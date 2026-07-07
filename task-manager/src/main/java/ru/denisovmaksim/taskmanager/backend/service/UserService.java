package ru.denisovmaksim.taskmanager.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.denisovmaksim.taskmanager.backend.dto.UserRequest;
import ru.denisovmaksim.taskmanager.backend.exception.UserAlreadyExistException;
import ru.denisovmaksim.taskmanager.backend.model.CustomUserDetails;
import ru.denisovmaksim.taskmanager.backend.model.User;
import ru.denisovmaksim.taskmanager.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(UserRequest userRequest) {
        try {
            String encodedPassword = encoder.encode(userRequest.password());
            User createdUser = userRepository.save(new User(userRequest.email(), encodedPassword));
            return jwtService.generateToken(new CustomUserDetails(createdUser));
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistException("This email is already taken");
        }
    }

    public String login(UserRequest userRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userRequest.email(),
                                userRequest.password()
                        )
                );

        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        return  jwtService.generateToken(user);
    }
}
