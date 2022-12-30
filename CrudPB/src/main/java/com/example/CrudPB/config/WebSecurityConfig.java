package com.example.CrudPB.config;

import com.example.CrudPB.security.JWTAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        http.csrf()
                .disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/CrudPB/borrar/**")
                .hasAnyAuthority("ADMIN")
                .antMatchers(            "/CrudPB/crear/**", "/CrudPB/actualizar/**")
                .hasAnyAuthority("GERENTE")
                .antMatchers(            "/CrudPB/listar/**", "/CrudPB/buscar/**")
                .hasAnyAuthority("EMPLEADO")
                .antMatchers(HttpMethod.POST, "/CrudPB/login")
                .permitAll();

    }

 @Override
    public void configure ( WebSecurity web ) throws Exception {
        web.ignoring()
                .antMatchers("/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**");

        web.ignoring()
                .antMatchers(
                        "/h2-console/**");
    }

}
