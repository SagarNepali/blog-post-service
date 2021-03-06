package edu.miu.blogpost.service;

import edu.miu.blogpost.dao.PostDAO;
import edu.miu.blogpost.domain.Post;
import edu.miu.blogpost.domain.dto.PostDTO;
import edu.miu.blogpost.exception.PostNotFoundException;
import edu.miu.blogpost.util.UserServiceRestClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    @Autowired
    PostDAO postDAO;

    @Autowired
    UserServiceRestClient userServiceRestClient;

    @Autowired
    ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<Post> getAll(){
        return postDAO.findAll();
    }

    public Post getById(Long id) throws PostNotFoundException {
        Optional<Post> p = postDAO.findById(id);
        if(!p.isPresent()) throw new PostNotFoundException("Post Not found");
        return p.get();
    }

    public PostDTO getByIdDto(Long id) throws PostNotFoundException {
        Optional<Post> p = postDAO.findById(id);
        if(!p.isPresent()){
            throw  new PostNotFoundException("Post not found of id: "+id);
        }
        PostDTO postDTO = modelMapper.map(p.get(),PostDTO.class);
        postDTO.setUser(userServiceRestClient.getById(p.get().getUserId()));
        return postDTO;

    }


    public Post add(Post p){
        return postDAO.save(p);
    }

    public Post update(Post p,Long id) throws PostNotFoundException {
        Post p1 = getById(id);
        if(p1!=null){
            p1.setContent(p.getContent());
            return postDAO.save(p1);
        }
        throw new PostNotFoundException("Cannot find Post.");
    }

    public void delete(Long id) throws PostNotFoundException{
        Post p = getById(id);
        if(p == null) throw new PostNotFoundException("Post couldnot be deleted.");

        postDAO.delete(p);
    }

    public List<Post> getAllPostsByUserId(Long id){
        return postDAO.findAllByUserId(id);
    }

    public void deleteAllPostsByUserId(Long id) {
        postDAO.deleteAllByUserId(id);
    }
}
