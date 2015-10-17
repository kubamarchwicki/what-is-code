package com.example;

import com.example.audit.TransactionLogRepository;
import com.example.dictionary.Dictionary;
import com.example.dictionary.DictionaryWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.List;

@EnableAutoConfiguration
@EnableSwagger2
@RestController
@ComponentScan
public class App {

    private Dictionary dict;

    @Autowired
    public App(Dictionary dict) {
        this.dict = dict;
    }


    @RequestMapping("/translate/{word}")
    public List<DictionaryWord> getTranslations(@PathVariable String word) throws IOException {
        return dict.getTranslations(word);
    }

    @Bean
    public Docket exampleApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .paths(PathSelectors.any())
                .build()
            .pathMapping("/");
    }

    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }
}
