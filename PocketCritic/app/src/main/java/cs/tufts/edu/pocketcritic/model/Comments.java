package cs.tufts.edu.pocketcritic.model;

import org.w3c.dom.Comment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by junwang on 11/21/16.
 */

public class Comments {
    public String userId;
    public String author;
    public String title;
    public String content;

    public Comments() {}

    public Comments(String userId, String author, String title, String content) {
        this.userId = userId;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userId", userId);
        result.put("author", author);
        result.put("title", title);
        result.put("content", content);
        return result;

    }

}
