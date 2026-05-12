package com.example.quoteservicecqrs.quote.intrastructure.persistence.repository;

import com.example.quoteservicecqrs.quote.intrastructure.persistence.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteJpaRepository extends JpaRepository<QuoteEntity, String> {
}
