package com.cb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.cb.model.Customer;
import com.cb.model.MlcCard;
import com.cb.repository.CustomerRepository;
import com.google.common.base.Predicates;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BootLuncher {


    public static void main(String[] args) {
        SpringApplication.run(BootLuncher.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return e -> repository.saveAll(getCustList());
    }

    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }


    private List<Customer> getCustList() {
        List<Customer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Customer cust = new Customer();
            cust.setCId(UUID.randomUUID().toString());
            cust.setPhoneNumber(String.valueOf(new Random().nextInt(2147483647)));
            cust.setRoles(new String[]{"PRO", "DIY"});

            List<MlcCard> cards = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                MlcCard card = new MlcCard();
                card.setMlcCardNo(String.valueOf(new Random().nextInt(2147483647)));
                card.setActive(new Random().nextBoolean());
                cards.add(card);
            }
            cust.setMlcCards(cards);
            list.add(cust);
        }

        return list;
    }
}
