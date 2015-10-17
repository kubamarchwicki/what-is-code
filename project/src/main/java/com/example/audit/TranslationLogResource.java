package com.example.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TranslationLogResource {

    private TransactionLogRepository repository;

    @Autowired
    public TranslationLogResource(TransactionLogRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/log")
    public List<TranslationLog> log() {
        return repository.findAll();
    }
}
