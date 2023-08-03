package com.telkomedika.telkomedikaapi.repository;

import com.telkomedika.telkomedikaapi.entity.accountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface accountRepository extends JpaRepository<accountEntity, Integer> {

    Optional<accountEntity> findByUsername(String username);
    Optional<accountEntity> findById(int id);
}
