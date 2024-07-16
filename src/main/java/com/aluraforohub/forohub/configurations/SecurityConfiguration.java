package com.aluraforohub.forohub.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    // La siguiente forma funciona pero debe quitarse los @PreAutorized en las clases controllers.
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        // HttpSecurity va pasando por todos los filtros
//        return httpSecurity
//                .csrf(csrf -> csrf.disable()) // no se necesita en el momento porque es un aplicacion web
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement((session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)))
//                .authorizeHttpRequests(http -> {
//                    // configurar los endpoints publicos
//                    //http.requestMatchers(HttpMethod.GET, "/auth/get").permitAll();
//
//                    http.requestMatchers(HttpMethod.GET, "/topicos").permitAll();
//                    http.requestMatchers(HttpMethod.POST, "/topicos").permitAll();
//                    http.requestMatchers(HttpMethod.PUT, "/topicos").permitAll();
//
//                    // configurar los endpoints privados
//                    //http.requestMatchers(HttpMethod.POST, "/auth/post").hasAnyRole("ADMIN","DEVELOPER");
//                    //http.requestMatchers(HttpMethod.POST, "/topicos").hasAnyRole("ADMIN","DEVELOPER");
//
//                    //http.requestMatchers(HttpMethod.POST, "/topicos").hasAnyRole("ADMIN","DEVELOPER");
//                    //http.requestMatchers(HttpMethod.POST, "/topicos").hasAnyAuthority("CREATE","READ");
//                    //http.requestMatchers(HttpMethod.PATCH, "/auth/patch").hasAnyAuthority("REFACTOR");
//
//                    //http.requestMatchers(HttpMethod.POST, "/auth/post").hasAuthority("READ");
//                    //http.requestMatchers(HttpMethod.POST, "/auth/post").hasAuthority("CREATE");
//                    // configurar para que demas endpoints sean denegados.
//                    //http.anyRequest().denyAll(); // Denegar todos mapping
//                    //configurar el resto de no especifidos, quiere decir pedira las credenciales.
//                    //http.anyRequest().authenticated(); // si los demas mapping tienen usuario y contraseña validos dejarlos pasar.
//                })
//                .build();
//    }


    // Para hacerlo de la siguiente manera se deben ubicar los @PreAuthorized en la clases controller.
    // para hacerlo en el controlador "UserController" se deba hacer uso de la anotacion @PreAuthorize()
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // HttpSecurity va pasando por todos los filtros
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // no se necesita en el momento porque es un aplicacion web
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean // NoOpPasswordEncoder ya est deprecado lo usaremos solo para esta practica
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("1234"));
//    }


}
