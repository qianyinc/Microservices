package edu.cmu.mis.iccfb.controller;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.cmu.mis.iccfb.model.Author;
import edu.cmu.mis.iccfb.model.Quote;
import edu.cmu.mis.iccfb.service.AuthorService;
import edu.cmu.mis.iccfb.service.QuoteService;

@RestController
public class ApiGatewayController {
    
    @Autowired
    private QuoteService quoteService;
    
    @Autowired
    private AuthorService authorService;

    @Value("${service.author.uri}")
    private String authorServerUri;

    @Value("${service.quote.uri}")
    private String quoteServerUri;

    @Value("${service.api.uri}")
    private String gatewayServerUri;



    @RequestMapping(value = "/api/quote/random", method = RequestMethod.GET)
    public Quote random() {
        return quoteService.randomQuote();
    }
    
    @RequestMapping(value = "/api/quote/by")
    public List<Quote> findByName(@RequestParam(required = true)String name) {
        return quoteService.findByAuthor_Name(name);
    }



//    RestTemplate restTemplate = new RestTemplate();
//    String uri = authorServerUri+"/" + id;
//    Author author = restTemplate.getForObject(uri, Author.class);
//    RestTemplate restTemplate = new RestTemplate();
//    String uri = authorServerUri;
//
//
//    ResponseEntity st = restTemplate.postForEntity(uri, a, Long.class);
//        return st.getBody();





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
    
    
    
    public Quote fallback() {
        Quote q = new Quote();
        Author a = new Author("Confucius");
        q.setText("The superior man is modest in his speech, but exceeds in his actions.");

        return q; 
    }
}
