package ru.denisovmaksim.taskmanager.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.denisovmaksim.taskmanager.backend.model.CustomUserDetails;
import ru.denisovmaksim.taskmanager.backend.model.User;
import ru.denisovmaksim.taskmanager.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new CustomUserDetails(user);
    }
}
