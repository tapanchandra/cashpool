package com.invia.interview.cashpoolapplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEncryptableProperties
@EnableJpaRepositories
@EnableTransactionManagement
@EnableJpaAuditing
public class CashpoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashpoolApplication.class, args);
	}
}
