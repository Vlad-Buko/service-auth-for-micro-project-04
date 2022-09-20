package com.example.serviceauthformicroproject04.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Vladislav Domaniewski
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @Column(name = "person_id")
    private String personId;
    private String hash;
}
