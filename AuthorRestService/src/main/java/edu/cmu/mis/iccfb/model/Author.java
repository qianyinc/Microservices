package edu.cmu.mis.iccfb.model;

public class Author {


    private Long authorId;
    private String name;


    protected Author() {}

    public Author(String name, Long authorId) {
        this.name = name;
        this.authorId = authorId;

    }

    @Override
    public String toString() {
        return String.format("Author[id=%d, name='%s']", authorId, this.name);
    }

    public Long getAuthorId () {
        return this.authorId;
    }
    public void setAuthorId () {this.authorId = authorId;}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
