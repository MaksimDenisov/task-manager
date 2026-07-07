package ru.denisovmaksim.taskmanager.backend.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtConfig {
    private static final String HMAC_ALGORITHM = "HmacSHA256";

    @Bean
    JwtEncoder jwtEncoder(JwtProperties properties) {
        SecretKey secretKey = new SecretKeySpec(
                properties.secret().getBytes(StandardCharsets.UTF_8),
                HMAC_ALGORITHM
        );

        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey));
    }

    @Bean
    JwtDecoder jwtDecoder(JwtProperties properties) {
        SecretKey secretKey = new SecretKeySpec(
                properties.secret().getBytes(StandardCharsets.UTF_8),
                HMAC_ALGORITHM
        );

        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}
