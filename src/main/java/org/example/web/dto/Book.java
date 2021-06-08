package org.example.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;
    private String author;
    private String title;
    private String pages;

    public boolean hasAnyAttribute() {
        return (title != null && !title.isEmpty())
                || (author != null && !author.isEmpty())
                || (pages != null && !pages.isEmpty());
    }

}
