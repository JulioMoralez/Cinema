package pack.model;

import javax.persistence.*;

@Entity
@Table(name = "t_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    private String picPath;

    public Message() {
    }

    public Message(String text, User author) {
        this.text = text;
        this.author=author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", Text='" + text + '\'' +
                '}';
    }
}
