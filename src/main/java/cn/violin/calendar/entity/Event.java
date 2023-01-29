package cn.violin.calendar.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "t_blog")
@Builder
public class Event implements Serializable {
    @Id
    private String bid;
    private String owner;
    private String title;
    private String btId;
    private String contentType;
    private Binary content;
    private long size;
    private String updateDateTime;
    private int order;
}