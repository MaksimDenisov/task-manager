package ru.denisovmaksim.cloudfilestorage;


import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.denisovmaksim.cloudfilestorage.model.User;
import ru.denisovmaksim.cloudfilestorage.repository.UserRepository;


@Profile("dev")
@Component
@AllArgsConstructor
public class SampleDataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = User.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ivanov@mail.com")
                .password("password")
                .build();
        userRepository.save(user);
    }
}
