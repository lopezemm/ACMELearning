package com.example.acme.services;

import com.example.acme.configuration.AcmeSecurityUser;
import com.example.acme.entities.AcmeUsers;
import com.example.acme.repositpories.AcmeUsersRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcmeUserService  implements UserDetailsService {

    AcmeUsersRepo acmeUsersRepo;

    public AcmeUserService(AcmeUsersRepo acmeUsersRepo) {
        this.acmeUsersRepo = acmeUsersRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<AcmeUsers> user = acmeUsersRepo.findByUserName(userName);
        user.orElseThrow(()-> new UsernameNotFoundException("User not found : " + userName));
        return user.map(AcmeSecurityUser::new).get();
    }

    public void suscribe(AcmeUsers acmeUsers){
        acmeUsersRepo.save(acmeUsers);
    }
}
