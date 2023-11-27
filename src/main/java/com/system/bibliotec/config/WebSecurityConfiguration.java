//package com.system.bibliotec.config;
//
//import com.system.bibliotec.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import com.system.bibliotec.security.AppUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true,jsr250Enabled = true)
//public class WebSecurityConfiguration {
//
//
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private AppUserDetailsService userDetailsService;
//
//
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        if (passwordEncoder == null) {
//            passwordEncoder = DefaultPasswordEncoderFactories.createDelegatingPasswordEncoder();
//        }
//        return passwordEncoder;
//
//    }
//       @Bean
//      public UserDetailsService userDetailsService() {
//         if (userDetailsService == null) {
//             userDetailsService = new AppUserDetailsService(usuarioRepository);
//           // ((JdbcDaoImpl) userDetailsService).setDataSource(dataSource);
//       }
//        return userDetailsService;
//      }
//
//
////    @Bean
//    //   @Override
//    //  public UserDetailsService userDetailsServiceBean() throws Exception {
//    //      return new AppUserDetailsService();
//    // }
//
//
////    @Override
////    public void configure(WebSecurity web) throws Exception {
////        web.ignoring().requestMatchers("/v2/api-docs", "/configuration/**", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/api-docs/**");
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
////    }
//
//
//}
