package edu.miu.blogpost.service;

import edu.miu.blogpost.dao.PostDAO;
import edu.miu.blogpost.domain.Post;
import edu.miu.blogpost.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
@Transactional
public class PostService {

    @Autowired
    PostDAO postDAO;

    @Transactional(readOnly = true)
    public List<Post> getAll(){
        return postDAO.findAll();
    }

    public Post getById(Long id) throws PostNotFoundException {
        Post p = postDAO.findById(id).get();
        if(p==null) throw new PostNotFoundException("Post Not found");
        return p;
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

}
