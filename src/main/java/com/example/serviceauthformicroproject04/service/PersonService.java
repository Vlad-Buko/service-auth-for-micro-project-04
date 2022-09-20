package com.example.serviceauthformicroproject04.service;

public interface PersonService {
    void register(String personId, String personSecret);
    void checkCredentials(String personId,String personSecret);
}
