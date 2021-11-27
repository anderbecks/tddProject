package com.example.tdd.entities;

import com.example.tdd.enums.Permissions;
import com.example.tdd.enums.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;


@Data
@AllArgsConstructor
public class User {
    private String userName;
    private byte [] salt;
    private String password;
    private HashMap<Resource, List<Permissions>> authorisations;

}
