package com.nhnacademy.sessionproject.session;


import com.nhnacademy.sessionproject.entity.Data;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("mysql")
public interface DataBaseRepository extends JpaRepository<Data,String> {

    void deleteByKeyAndField(String key,String field);
    Optional<Data> findByKeyAndField(String key, String field);
    void deleteAllByKey(String key);

    boolean existsByKeyAndField(String key, String field);
}
