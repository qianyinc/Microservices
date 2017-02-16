package edu.cmu.mis.iccfb.model;
import org.springframework.stereotype.Service;



import org.hibernate.annotations.NaturalId;

@Service
public class Quote {

    private Long id;
    private String text;
    private String source;
    private Long authorId;


    public Quote() {}

    public Quote(String text, String source, Long authorId) {
        this.text = text;
        this.source = source;
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        //想想这里要怎么样让authorId可以直接query到author
        return String.format("Quote[id=%d, text='%s', by='%s']", this.id, this.text, this.authorId);
    }


    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }



    public Long getId() {
        return id;
    }
    
    
}