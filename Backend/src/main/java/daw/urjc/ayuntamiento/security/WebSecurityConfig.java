package daw.urjc.ayuntamiento.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/gob").permitAll();
        http.authorizeRequests().antMatchers("/events").permitAll();
        http.authorizeRequests().antMatchers("/locals").permitAll();


        http.authorizeRequests().antMatchers("/userlogin").permitAll();
        http.authorizeRequests().antMatchers("/errorLogin").permitAll();

        http.authorizeRequests().antMatchers("/comment/*").permitAll();

        http.authorizeRequests().antMatchers("/error").permitAll();


        http.authorizeRequests().antMatchers("/event/*").permitAll();
        http.authorizeRequests().antMatchers("/event/*/image").permitAll();

        http.authorizeRequests().antMatchers("/local/*").permitAll();
        http.authorizeRequests().antMatchers("/event/*/image1").permitAll();
        http.authorizeRequests().antMatchers("/event/*/image2").permitAll();

        http.authorizeRequests().antMatchers("/user/*/image").permitAll();

        // Private pages
        http.authorizeRequests().antMatchers("/profile").hasAnyRole("USER");;


        http.authorizeRequests().antMatchers("/subscribe/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/removeEvent/*").hasAnyRole("USER");


        http.authorizeRequests().antMatchers("/FormEvent").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/FormLocal").hasAnyRole("ADMIN");


        http.authorizeRequests().antMatchers("/removeLocal/*").hasAnyRole("ADMIN");

        http.authorizeRequests().antMatchers("/removeEvent/*").hasAnyRole("ADMIN");


        http.authorizeRequests().antMatchers("/editProfile").hasAnyRole("USER");




        // Login form
        http.formLogin().loginPage("/userlogin");
        http.formLogin().usernameParameter("name");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/profile");
        http.formLogin().failureUrl("/errorLogin");


        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
    }
}
