package edu.cmu.mis.iccfb.service;
import edu.cmu.mis.iccfb.model.Quote;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface QuoteService extends CrudRepository<Quote, Long>, QuoteServiceCustom {
    List<Quote> findByAuthor_Name(String name);
    List<Quote> findByAuthor_Id(Long id);
}
