package com.fanhunoo.clover.config;

import com.fanhunoo.clover.security.MyAccessDeniedHandler;
import com.fanhunoo.clover.security.MyAuthenticationProcessingFilterEntryPoint;
import com.fanhunoo.clover.security.MyFilterSecurityInterceptor;
import com.fanhunoo.clover.security.MyUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

	/**
	 * 注册 403 处理器
	 */
	@Autowired
	private MyAccessDeniedHandler accessDeniedHandler;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService myUserDetailService() {
		return new MyUserDetailServiceImpl();
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(myUserDetailService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());//使用security提供的BCryptPasswordEncoder加密
		authenticationProvider.setHideUserNotFoundExceptions(false);
		return authenticationProvider;
	}

	@Bean
	public SessionRegistry sessionRegistry(){
		return new SessionRegistryImpl();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher(){
	    return new HttpSessionEventPublisher();
    }


	@Override
	public void configure(WebSecurity web) throws Exception {
		// 设置不拦截规则
		web.ignoring().antMatchers("/plugins/**","/statics/**","/css/**","/layui/**","/js/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin();//X-Frame-Options = SAMEORIGIN：frame页面的地址只能为同源域名下的页面
		http
//				.exceptionHandling().authenticationEntryPoint(new MyAuthenticationProcessingFilterEntryPoint("/login")).and()
				.authorizeRequests()//该方法所返回的对象的方法来配置请求级别的安全细节
            .antMatchers("/","/login").permitAll()//对于登录路径不进行拦截
			.anyRequest().authenticated()//其他所有路径都是需要认证/登录后才能访问
			.and()
			.formLogin()
			.loginPage("/login")// 指定登录页面的请求路径 default is /login with an HTTP get
			.failureUrl("/login?error=true")//登录失败后跳转的路径 default is /login?error
            .defaultSuccessUrl("/home")//登录成功后默认跳转的路径
            .and()
            .logout()//用户退出操作
            .logoutUrl("/logout")//用户退出所访问的路径，需要使用Post方式
            .logoutSuccessUrl("/login?logout=true")
			.invalidateHttpSession(true)

		// 配置被拦截时的处理
				.and().exceptionHandling()
		        .accessDeniedPage("/error/403")
//				.authenticationEntryPoint(this.unauthorizedHandler)   // 添加 token 无效或者没有携带 token 时的处理
//				.accessDeniedHandler(this.accessDeniedHandler)      //添加无权限时的处理


//            .and()
//            .authorizeRequests()
//			.anyRequest().permitAll()
//          .and()
//          .rememberMe()//启用记住我功能
//          .tokenValiditySeconds(2419200)
            .and().addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器
				.csrf().disable();//启用防跨站伪请求攻击，默认启用

		// session管理
				http.sessionManagement().sessionFixation().changeSessionId()
				.maximumSessions(1).expiredUrl("/login").sessionRegistry(sessionRegistry());
	}

}