package com.avicodes.memestream.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = "memes")
@NoArgsConstructor
@Getter @Setter
@AllArgsConstructor
public class MemeEntity {
    @Id 
    private long id;
    
    @NonNull
    private String name;
    
    @Indexed(unique = true)
    @NonNull
    private String url;
     
    @NonNull
    private String caption;
}
