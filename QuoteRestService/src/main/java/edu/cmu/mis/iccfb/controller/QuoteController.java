package edu.cmu.mis.iccfb.controller;
import edu.cmu.mis.iccfb.service.QuoteService;
import edu.cmu.mis.iccfb.service.AuthorService;
import edu.cmu.mis.iccfb.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import edu.cmu.mis.iccfb.model.Author;
import org.springframework.web.client.RestTemplate;


public class QuoteController {

    @Autowired
    private QuoteService quoteService;
    @Autowired
    private AuthorService authorService;

    @Value("${service.QuoteService.uri}")
    private String quoteServerUri;

    private int id;


    RestTemplate restTemplate = new RestTemplate();
    String uri = quoteServerUri+"/" + id;
    Author author = restTemplate.getForObject(uri, Author.class);

//    RestTemplate restTemplate = new RestTemplate();
//    String uri = quoteServerUri;


//    ResponseEntity st = restTemplate.postForEntity(uri, a, Long.class);
//        return st.getBody( );



    @RequestMapping(value = "/api/quote/random", method = RequestMethod.GET)
    public Quote random() {
        return quoteService.randomQuote();
    }


    @RequestMapping(value = "/api/quote", method = RequestMethod.POST)
    public void saveQuote(@RequestBody Quote quote) {
        System.out.println(quote);

        Author a = authorService.findAuthorbyId(quote.getAuthorId());

        if (a == null) {
            System.out.println("Saving author");
            authorService.save(authorService.findAuthorbyId(quote.getAuthorId()));
        }
//        else {
//            quote.setAuthor(a);
//        }


        System.out.println("Saving quote");
        quoteService.save(quote);
    }
    @RequestMapping(value = "/api/quote/author", method = RequestMethod.GET)
    public List<Quote> findByAuthorId(@RequestParam(required = true)Long id) {
        return quoteService.findByAuthor_Id(id);
    }

    @RequestMapping(value = "/api/quote", method = RequestMethod.GET)
    public List<Quote> listQuotes() {
        List<Quote> quotes = new ArrayList<>();
        quoteService.findAll().iterator().forEachRemaining(quotes::add);
        return quotes;
    }





}
