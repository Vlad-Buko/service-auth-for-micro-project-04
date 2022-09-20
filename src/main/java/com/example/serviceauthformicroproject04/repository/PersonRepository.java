package com.example.serviceauthformicroproject04.repository;

import com.example.serviceauthformicroproject04.dao.PersonEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vladislav Domaniewski
 */

public interface PersonRepository extends CrudRepository<PersonEntity, String> {
}
