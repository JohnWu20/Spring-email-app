package org.asnworks.apis.springemailexample.repository;

import org.asnworks.apis.springemailexample.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName User
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-12 21:12
 * @Version 1.0
 **/

@Repository
public interface UserRepository extends JpaRepository<Login, Integer> {
    boolean existsAllByPassWordAndUserAddress(String password, String userAddress);
    boolean existsAllByUserAddress(String userAddress);
    Login findAllByUserAddress(String userAddress);
}