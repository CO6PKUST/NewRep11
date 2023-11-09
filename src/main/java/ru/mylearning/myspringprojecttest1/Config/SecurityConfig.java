package ru.mylearning.myspringprojecttest1.Config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.mylearning.myspringprojecttest1.Services.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
@Slf4j
public class SecurityConfig {

    private final UserService userService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoderConfiguration passwordEncoderConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/secured").authenticated()
                        .requestMatchers("/info").authenticated()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().permitAll())
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .exceptionHandling(exception -> exception.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoderConfiguration.passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

}
