package hytorc_solutions_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuración de seguridad HTTP

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests().requestMatchers(
                "/api/usuarios/login",
                "/api/usuarios/listarUsu", "/api/empleados/listar", "/api/usuarios/guardar",
                "/api/usuarios/modificar/**",
                "/api/usuarios/eliminar/**", "/api/archivos/**", "/api/formularios/editar",
                "/api/formularios/ultimos-campos", "/api/formularios/registrar", "/api/formularios/listar/**",
                "/api/formularios/filtrar", "/api/formulario/plano/**", "/api/formularios/buscar","/api/formularios/detalle"


        ).permitAll().anyRequest()
                .authenticated().and()
                .httpBasic();

        return http.build();
    }

    /*
     * @Bean
     * public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     * http.csrf().disable().authorizeHttpRequests()
     * .anyRequest().permitAll();
     * return http.build();
     * }
     */
}