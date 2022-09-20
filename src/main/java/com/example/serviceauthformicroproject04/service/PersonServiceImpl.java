package com.example.serviceauthformicroproject04.service;

import com.example.serviceauthformicroproject04.dao.PersonEntity;
import com.example.serviceauthformicroproject04.exception.LoginException;
import com.example.serviceauthformicroproject04.exception.RegistrationException;
import com.example.serviceauthformicroproject04.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Vladislav Domaniewski
 */

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    private final PersonRepository userPerson;
    @Override
    public void register(String personId, String personSecret) {
        if(userPerson.findById(personId).isPresent())
            throw new RegistrationException(
                    "Client with id: " + personId + " already registered");
        String hash = BCrypt.hashpw(personSecret, BCrypt.gensalt());
        userPerson.save(new PersonEntity(personId, hash));
    }

    @Override
    public void checkCredentials(String personId, String personSecret) {
        Optional<PersonEntity> optionalUserEntity = userPerson
                .findById(personId);
        if (optionalUserEntity.isEmpty())
            throw new LoginException(
                    "Client with id: " + personId + " not found");

        PersonEntity personEntity = optionalUserEntity.get();

        if (!BCrypt.checkpw(personSecret, personEntity.getHash()))
            throw new LoginException("Secret is incorrect");
    }
}
