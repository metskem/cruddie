package nl.computerhok.cruddie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.RequestRejectedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER"); // ... etc.
    }

    // TODO the following disables form-login
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .and()
                .httpBasic();
    }

    HttpFirewall httpFirewall = new HttpFirewall() {
        @Override
        public FirewalledRequest getFirewalledRequest(HttpServletRequest httpServletRequest) throws RequestRejectedException {
            LOG.debug("firewalledRequest: " + httpServletRequest);
            return new FirewalledRequest(httpServletRequest) {
                @Override
                public void reset() {
                    // @TODO what can we do to block a request for example ?
                }
            };
        }

        @Override
        public HttpServletResponse getFirewalledResponse(HttpServletResponse httpServletResponse) {
            LOG.debug("firewalledResponse: " + httpServletResponse);
            // @TODO what can we do to block a response for example ?
            return httpServletResponse;
        }
    };


    @Override
    public void configure(WebSecurity web) {
        web.httpFirewall(httpFirewall);
    }
    // ... other stuff for application security

}
