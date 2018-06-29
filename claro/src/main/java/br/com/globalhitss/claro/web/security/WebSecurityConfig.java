package br.com.globalhitss.claro.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;

import br.com.globalhitss.claro.exception.handler.UnauthorizationEntryPoint;
import br.com.globalhitss.claro.web.security.auth.AccountAuthenticationProvider;
import br.com.globalhitss.claro.web.security.filter.CorsFilter;
import br.com.globalhitss.claro.web.security.filter.LoginFilter;
import br.com.globalhitss.claro.web.security.filter.RestFilter;
import br.com.globalhitss.claro.web.security.handler.LoginFailureHandler;
import br.com.globalhitss.claro.web.security.handler.LoginSuccessHandler;
import br.com.globalhitss.claro.web.security.handler.LogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AccountAuthenticationProvider authenticationProvider;

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Autowired
	private LoginFailureHandler loginFailureHandler;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	private UnauthorizationEntryPoint entrypoint;

	@Autowired
	private RestFilter filter;

	@Autowired
	private CorsFilter corsFilter;

	private LoginFilter loginFilter;

	@Bean
	public RestFilter filter() {
		filter.noRequiresAuthentication(HttpMethod.POST, "/login");
		filter.noRequiresAuthentication(HttpMethod.POST, "/account");
		filter.noRequiresAuthentication(HttpMethod.OPTIONS, "/**");
		return filter;
	}

	@Bean
	public LoginFilter loginFilter() {
		loginFilter = new LoginFilter(loginSuccessHandler, loginFailureHandler);
		return loginFilter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().httpBasic().disable()

				.cors().disable()

				.formLogin().disable()

				.logout().logoutSuccessHandler(logoutSuccessHandler).permitAll().and()

				.requestCache().requestCache(new NullRequestCache()).and()

				.exceptionHandling().authenticationEntryPoint(entrypoint).and()

				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().sessionManagement()
				.disable()

				.addFilterBefore(filter(), BasicAuthenticationFilter.class)
				.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterAfter(corsFilter, SwitchUserFilter.class)

				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/account").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll()

				.anyRequest().authenticated();

	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
