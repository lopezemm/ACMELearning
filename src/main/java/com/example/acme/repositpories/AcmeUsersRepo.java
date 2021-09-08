package com.example.acme.repositpories;

import com.example.acme.entities.AcmeUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AcmeUsersRepo extends JpaRepository<AcmeUsers, Long> {
    Optional<AcmeUsers> findByUserName(String userName);

}
