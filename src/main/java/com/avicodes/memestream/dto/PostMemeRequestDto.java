package com.avicodes.memestream.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostMemeRequestDto {
    public String name;
    public String url;
    public String caption;
}
