package swp391.learning.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/user/register",
                        "/api/v1/user/login",
                        "/api/v1/user/verify-otp",
                        "/api/v1/user/forgotPassword",
                        "/api/v1/user/changePassword",
                        "/api/v1/user/send-otp-forgot-password",
                        "/api/v1/user/verify-otp-forgotPass",
                        "/api/v1/user/change-profile",
                        "/api/v1/user/log-out",
                        "/api/v1/user/total-user",
                        "/api/v1/user/getOTP",
                        "/api/v1/user/resendOTP",
                        "/api/v1/user/get-user-by-username",
                        "/api/v1/user/set-role-user",
                        "/api/v1/user/get-all-user",
                        "/api-docs",
                        "/swagger-ui",
                        "/api/v1/category/get-category-by-id",
                        "/api/v1/category/add-category",
                        "/api/v1/category/update-category",
                        "/api/v1/category/delete-category",
                        "/api/v1/category/find-all-category",
                        "/api/v1/category/find-all-category-by-deleted",
                        "/swagger-ui/**"
                ).permitAll()
                .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
