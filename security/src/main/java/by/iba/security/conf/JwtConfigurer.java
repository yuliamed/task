package by.iba.security.conf;

import by.iba.entity.enam.RoleEnum;
import by.iba.security.jwt.EntryPointJwt;
import by.iba.security.jwt.JwtTokenFilter;
import by.iba.security.service.JwtUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class JwtConfigurer extends WebSecurityConfigurerAdapter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final EntryPointJwt entryPointJwt;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(jwtUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
//                .cors()
//                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(entryPointJwt)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/mail/**").permitAll()
                .antMatchers("/api/v1/orders/**")
                .hasAnyAuthority(RoleEnum.USER.name(), RoleEnum.AUTO_PICKER.name(), RoleEnum.ADMIN.name())
                .antMatchers("/api/v1/admin/**").hasAnyAuthority(RoleEnum.ADMIN.name())
                .antMatchers("/api/v1/auto-picker/**").hasAnyAuthority(RoleEnum.AUTO_PICKER.name())
                .antMatchers("/api/v1/users/**").hasAnyAuthority("USER")
                .anyRequest()
                .authenticated();

        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*");
//    }
}
