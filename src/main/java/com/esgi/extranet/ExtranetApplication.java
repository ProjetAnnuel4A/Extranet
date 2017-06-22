package com.esgi.extranet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
//@EnableNeo4jRepositories("com.esgi.extranet.login.users")
@EnableTransactionManagement
public class ExtranetApplication {
    /*@Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "com.esgi.extranet.login.users");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        org.neo4j.ogm.config.Configuration configuration = new Configuration();
        configuration.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI("http://neo4j:root@localhost:7474");

        return configuration;
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }*/

	public static void main(String[] args) {
		SpringApplication.run(ExtranetApplication.class, args);
	}
}
