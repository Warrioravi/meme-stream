package com.avicodes.memestream.services;




import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.avicodes.memestream.dto.PostMemeRequestDto;
import com.avicodes.memestream.dto.PostMemeResponseDto;
import com.avicodes.memestream.entities.MemeEntity;
import com.avicodes.memestream.repositories.MemeRepository;

@Service
public class MemesService {
  
  @Autowired
  private MemeRepository memeRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  public PostMemeResponseDto  postMeme(PostMemeRequestDto req) {
    //check if there is any duplicate data in the db already
    List<MemeEntity> dup=memeRepository.findMemes(req.getName(),req.getUrl(), req.getCaption());
    if(dup==null||dup.size()==0){
      long currId=memeRepository.count()+1;
      memeRepository.save(new MemeEntity(currId,req.getName(),req.getUrl(),req.getCaption()));
      return new PostMemeResponseDto(currId);
    }
    else return null;
    

   
  }



  public List<MemeEntity>  getMeme() {
    

    // List<MemeEntity> rv= memeRepository.findAll();
    // long currId=memeRepository.count();
    // List<MemeEntity> recent=new ArrayList<>();
    // for (MemeEntity memeEntity : rv) {
    //    if(memeEntity.getId()>currId-100){
    //     recent.add(memeEntity);
    //    }
    // } 
    Query query=new Query();
    query.limit(100);
    query.with(Sort.by(Sort.Direction.DESC, "id"));
    return mongoTemplate.find(query, MemeEntity.class);


   
  }


  
  public MemeEntity getMemeById(Long id){
    Optional<MemeEntity> val= memeRepository.findById(id);
    if(val.isPresent()){
      return val.get();
    }
    else return null;
  }
     
 
}


