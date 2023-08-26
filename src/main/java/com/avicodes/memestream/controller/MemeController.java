package com.avicodes.memestream.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.avicodes.memestream.dto.PostMemeRequestDto;
import com.avicodes.memestream.dto.PostMemeResponseDto;
import com.avicodes.memestream.entities.MemeEntity;
import com.avicodes.memestream.services.MemesService;

@Controller
public class MemeController{
    @Autowired
    private MemesService memeService;

    public static final String XMEME_API_ENDPOINT = "/memes";

    @PostMapping(XMEME_API_ENDPOINT)
    public ResponseEntity<PostMemeResponseDto> addMeme(@RequestBody @Validated PostMemeRequestDto requestBody){
      System.out.println(requestBody);
      if(requestBody.caption==null||requestBody.caption==""||requestBody.name==null||requestBody.name==""||requestBody.url==null||requestBody.url==""){
            return ResponseEntity.badRequest().body(null);
      }

       PostMemeResponseDto rv=memeService.postMeme(requestBody);
       if(rv==null){
         return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
       }
       return ResponseEntity.ok(rv); 
    }






    @GetMapping(XMEME_API_ENDPOINT)
    public ResponseEntity<List<MemeEntity>> getMeme(){
          List<MemeEntity> rv= memeService.getMeme();
          return ResponseEntity.ok(rv);
    }






    @GetMapping(XMEME_API_ENDPOINT+"/{id}")
    public ResponseEntity<MemeEntity> getMemeById(@PathVariable("id") Long memeId){
          MemeEntity rv= memeService.getMemeById(memeId);
          if(rv==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
          }
          return  ResponseEntity.ok(rv);
    }
}