package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/javascript/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// アクセス権限の設定
	    http.authorizeRequests()
	        // 制限なし
	        .antMatchers("/login*", "/logout").permitAll()
	        // '/admin'は、'ADMIN'ロールのみアクセス可
	        .antMatchers("/admin").hasRole("ADMIN")
	        // 他は制限あり
	        .anyRequest().authenticated();
	    // ログイン処理の設定
	    http.formLogin()
	        // ログイン処理のURL
	        .loginPage("/login")
	        // usernameのパラメータ名
	        .usernameParameter("username")
	        // passwordのパラメータ名
	        .passwordParameter("password")
	        // ログイン失敗時の遷移先URL
	        .failureForwardUrl("/login-error");
	    // ログアウト処理の設定
	    http.logout()
	        // ログアウト処理のURL
	        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        // ログアウト成功時の遷移先URL
	        .logoutSuccessUrl("/login")
	        // ログアウト時に削除するクッキー名
	        .deleteCookies("JSESSIONID")
	        // ログアウト時のセッション破棄を有効化
	        .invalidateHttpSession(true);
	    http.exceptionHandling().accessDeniedPage("/error");
	  }
		
		
		
		
//		http.authorizeRequests()
//			.antMatchers("/", "/login*", "/logout").permitAll()
//			.anyRequest()
//			.authenticated();
//		http.formLogin()
//			.loginPage("/login")
//			.loginProcessingUrl("/sign_in")
//			.usernameParameter("username") //リクエストパラメータのname属性を明示
//            .passwordParameter("password")
//            .successForwardUrl("/index")
//            .failureUrl("/login?error");
//            .permitAll()
            
//         http.logout()
//         	.logoutUrl("/logout")
//         	.logoutSuccessUrl("/login?logout")
//         	.permitAll();
		
//		http.authorizeHttpRequests()
//			.antMatchers("/admin").hasRole("ADMIN")
//			.anyRequest().authenticated();
//		
//		http.formLogin();
		
//	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
//    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	http.authorizeRequests()
//    		.anyRequest()
//    		.authenticated();
//    	http.formLogin()
//    		.loginPage("/login")
//    		.defaultSuccessUrl("/")
//    		.permitAll();
//    	http.logout()
//    		.permitAll();
//    }
}
