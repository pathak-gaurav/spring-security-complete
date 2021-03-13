package github.pathakgaurav.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static github.pathakgaurav.security.ApplicationPermission.COURSE_WRITE;
import static github.pathakgaurav.security.ApplicationUserRole.ADMIN;
import static github.pathakgaurav.security.ApplicationUserRole.ADMIN_TRAINEE;
import static github.pathakgaurav.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.
               csrf().disable().
               authorizeRequests().antMatchers("/","/api/**").hasRole(ADMIN.name())
               .antMatchers(HttpMethod.GET,"/management/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
               .antMatchers(HttpMethod.PUT).hasAuthority(COURSE_WRITE.name())
               .antMatchers(HttpMethod.POST).hasAuthority(COURSE_WRITE.name())
               .antMatchers(HttpMethod.DELETE).hasAuthority(COURSE_WRITE.name())
               .anyRequest().authenticated().and().httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userZack = User.builder().username("zack")
                .password(passwordEncoder.encode("password"))
//                .roles(STUDENT.name())
                .authorities(STUDENT.grantedAuthorities())
                .build();
        UserDetails userCody = User.builder().username("cody")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.grantedAuthorities())
                .build();
        UserDetails userGaurav = User.builder().username("gaurav")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN_TRAINEE.name())
                .authorities(ADMIN_TRAINEE.grantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(userZack,userCody,userGaurav);
    }
}
