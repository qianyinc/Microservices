package edu.cmu.mis.iccfb.controller;
import edu.cmu.mis.iccfb.service.AuthorService;
import edu.cmu.mis.iccfb.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@RestController
public class AuthorRestController {
    @Autowired
    private AuthorService authorService;

    @Value("${service.author.uri}")
    private String authorServerUri="http://localhost:8081/api/author";



    @RequestMapping(value = "/api/author", method = RequestMethod.GET)
    public Author findByAuthorId(@RequestParam(required = true)Long authorId) {

        return authorService.findAuthorbyId(authorId);
    }



    @RequestMapping(value = "/api/author/by", method = RequestMethod.GET)
    public Author findByName(@RequestParam(required = true)String name) {

        return authorService.findByName(name);
    }


    @RequestMapping(value = "/api/author", method = RequestMethod.POST)
    public Object saveAuthor(@RequestBody Author author) {
//        System.out.println(author);
//
//        Author a = authorService.findByName(author.getName());
//
//        if (a == null) {
//            System.out.println("Saving author");
//            authorService.save(author);
//        }
//        else{
//            System.out.println("Author already saved.");
//        }
//        authorService.save(author);
        RestTemplate restTemplate = new RestTemplate();
        String uri = authorServerUri;

        ResponseEntity st = restTemplate.postForEntity(uri, author, Long.class);
        return st.getBody();

    }


}
