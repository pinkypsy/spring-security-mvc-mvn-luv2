package ua.alvin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import static org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        add users for in-memory authentication
        UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("john").password("q").roles("EMPLOYEE"))
                .withUser(users.username("mary").password("q").roles("EMPLOYEE", "MANAGER"))
                .withUser(users.username("silvia").password("q").roles("EMPLOYEE", "ADMIN"));

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//with landing page
        http.authorizeRequests()
                .antMatchers("/").permitAll()  // allow public access to home page
//                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/employees").hasRole("EMPLOYEE")
                .antMatchers("/leaders/**").hasRole("MANAGER")
                .antMatchers("/systems/**").hasRole("ADMIN")
                .and()
                .formLogin()//customization the form login process
                .loginPage("/showMyLoginPage")//show custom form on request mapping (need controller)
                .loginProcessingUrl("/authenticateTheUser")//login form POST data to this URL for processing (ie check login and password) - no controller request method required
                .permitAll()//allow everyone to see login page. No need to be logged in
                .and()
                .logout()
                .logoutSuccessUrl("/")  // after logout  redirect to this page
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

        //without landing page
       /* http.authorizeRequests()//restrict access based on HttpServletRequest
                .anyRequest().authenticated()//any request must be authenticated (ie logged in)
                .and()
                .formLogin()//customization the form login process
                .loginPage("/showMyLoginPage")//show custom form on request mapping (need controller)
                .loginProcessingUrl("/authenticateTheUser")//login form POST data to this URL for processing (ie check login and password) - no controller request method required
                .permitAll()
                .and()
                .logout().permitAll();*/

    }
}
