package swp391.learning.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import swp391.learning.security.CustomAuthorizationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthorizationFilter customAuthorizationFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/user/login", "/api/auth/register", "/api/auth/refreshToken").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers(
//                "/api/v1/user/register",
//                "/api/v1/user/login",
//                "/api/v1/user/verify-otp",
//                "/api/v1/user/forgotPassword",
//                "/api/v1/user/changePassword",
//                "/api/v1/user/send-otp-forgot-password",
//                "/api/v1/user/verify-otp-forgotPass",
//                "/api/v1/user/change-profile",
//                "/api/v1/user/log-out",
//                "/api/v1/user/total-user",
//                "/api/v1/user/getOTP",
//                "/api/v1/user/resendOTP",
//                "/api/v1/user/get-user-by-username",
//                "/api/v1/user/set-role-user",
//                "/api/v1/user/get-all-user",
//                "/api-docs").permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
//
//    }
//}
