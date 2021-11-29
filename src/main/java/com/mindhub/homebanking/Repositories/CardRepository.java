package com.mindhub.homebanking.Repositories;

import com.mindhub.homebanking.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {

}
