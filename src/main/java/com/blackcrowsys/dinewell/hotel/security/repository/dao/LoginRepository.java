package com.blackcrowsys.dinewell.hotel.security.repository.dao;

import com.blackcrowsys.dinewell.hotel.security.repository.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);
}
