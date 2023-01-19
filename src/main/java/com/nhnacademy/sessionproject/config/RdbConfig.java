package com.nhnacademy.sessionproject.config;

import com.nhnacademy.sessionproject.repository.MemberRepository;
import com.nhnacademy.sessionproject.session.DataBaseRepository;
import com.nhnacademy.sessionproject.session.DataBaseSession;
import com.nhnacademy.sessionproject.session.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("mysql")
@Configuration
public class RdbConfig {

    private final DataBaseRepository dataBaseRepository;

    public RdbConfig(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }

    @Bean
    public Session session() {
        return new DataBaseSession(dataBaseRepository);
    }
}