package com.mindhub.homebanking.Repositories;

import com.mindhub.homebanking.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RepositoryRestResource

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByNumber(String number);
}
