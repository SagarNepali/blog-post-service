package edu.miu.blogpost.controller;

import edu.miu.blogpost.domain.Post;
import edu.miu.blogpost.domain.dto.PostDTO;
import edu.miu.blogpost.exception.PostNotFoundException;
import edu.miu.blogpost.exception.UserNotFoundException;
import edu.miu.blogpost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostRestController {

    @Autowired
    PostService postService;

    @GetMapping
    public List<Post> getAll(){
        return postService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post insert(@RequestBody @Valid Post p) throws UserNotFoundException {
        return postService.add(p);
    }

    @PutMapping("/{id}")
    public Post update(@RequestBody @Valid Post p,@PathVariable Long id) throws PostNotFoundException,UserNotFoundException {
        return postService.update(p,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws PostNotFoundException,UserNotFoundException {
        postService.delete(id);
    }

    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable("id") Long id) throws PostNotFoundException {
        return postService.getByIdDto(id);
    }

    @GetMapping("/users/{userId}")
    public List<Post> getAllPostsOfAUser(@PathVariable("userId") Long id){
        return postService.getAllPostsByUserId(id);
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllPostsOfAUser(@PathVariable("userId") Long id){
        postService.deleteAllPostsByUserId(id);
    }
}
