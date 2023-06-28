package com.example.config;

import javax.sql.DataSource;

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


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	// データソース
	@Autowired
	private DataSource dataSource;

	// ユーザーIDとパスワードを取得するSQL文
	private static final String USER_SQL = "SELECT"
			+ " email,"
			+ " password,"
			+ " true"
			+ " FROM"
			+ " user"
			+ " WHERE"
			+ " email = ?";

	// ユーザーのロールを取得するSQL文
	private static final String ROLE_SQL = "SELECT"
			+ " email,"
			+ " role"
			+ " FROM"
			+ " user"
			+ " WHERE"
			+ " email = ?";

	@Override
	public void configure(WebSecurity web) throws Exception {

		//静的リソースへのアクセスには、セキュリティを適用しない
		web.ignoring().antMatchers("/webjars/∗∗", "/css/∗∗", "/h2-console/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

        // ログイン不要ページの設定
        http
            .authorizeRequests()
                .antMatchers("/login").permitAll() //直リンクOK
                .antMatchers("/home").permitAll() //直リンクOK
                .antMatchers("/css/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN") // 権限制御
                .anyRequest().authenticated(); // それ以外は直リンクNG
        
		// ログイン処理
		http
			.formLogin()
				.loginProcessingUrl("/login") //ログイン処理のパス
				.loginPage("/login") //ログインページの指定
				.failureUrl("/login?error") //ログイン失敗時の遷移先
				.usernameParameter("email") //ログインページのemail
				.passwordParameter("password") //ログインページのパスワード
				.defaultSuccessUrl("/session", true); //ログイン成功後の遷移先
		
		// ログアウト処理
        http
        .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout");
		
//		CSRF対策を無効に設定（一時的）
		http.csrf().disable();

		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USER_SQL)
		.authoritiesByUsernameQuery(ROLE_SQL).passwordEncoder(passwordEncoder());
	}
}