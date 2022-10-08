package com.gestion.fibrolaser.seguridad;

import com.gestion.fibrolaser.servicios.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppSecurity extends WebSecurityConfigurerAdapter {

    private final UsuarioServicio usuarioServicio;
    private final BCryptPasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServicio).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers("/auth/sign-up", "/auth/register", "/css/*", "/js/*").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("username")
                .passwordParameter("pwd")
                .defaultSuccessUrl("/", true)
                .failureUrl("/auth/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login?logout=true")
                .permitAll()
                .and()
                .csrf()
                .disable();
        // @formatter:on


    }
}
