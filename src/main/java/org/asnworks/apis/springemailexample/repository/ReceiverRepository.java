package org.asnworks.apis.springemailexample.repository;

import org.asnworks.apis.springemailexample.model.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName ReceiverRepository
 * Description:
 * Author: Jialiang Wu
 *
 * @Date 2019-12-13 22:01
 * @Version 1.0
 **/

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Integer> {
//    List<Receiver> findAllByEmailID(){}
}