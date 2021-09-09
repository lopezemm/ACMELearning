package com.example.acme.conf;

import com.example.acme.configuration.AcmeSecurityUser;
import com.example.acme.entities.AcmeUsers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {
    @Bean
    @Primary
    public UserDetailsService userDetailsService() {

        AcmeUsers acmeUser = new AcmeUsers();
        acmeUser.setUserName("emm");
        acmeUser.setPassword("pass");
        acmeUser.setRole("INSTR");
        AcmeSecurityUser managerActiveUser;
        managerActiveUser = new AcmeSecurityUser(acmeUser);

        return new InMemoryUserDetailsManager(Arrays.asList(
                managerActiveUser
        ));
    }
}
