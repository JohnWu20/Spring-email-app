package org.asnworks.apis.springemailexample.repository;

import org.asnworks.apis.springemailexample.model.Email;
import org.asnworks.apis.springemailexample.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName EmailRepository
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-13 16:54
 * @Version 1.0
 **/

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {
//    List<Email> findAllByIsReadIsFalse();
//    List<Email> findAllByFromUserAndIsReadIsFalse(String userAddress);

}