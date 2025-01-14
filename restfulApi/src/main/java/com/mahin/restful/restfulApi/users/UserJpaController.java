package com.mahin.restful.restfulApi.users;

import com.mahin.restful.restfulApi.posts.Post;
import com.mahin.restful.restfulApi.posts.PostJpaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {
    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostJpaRepository postJpaRepository;

    public UserJpaController(UserDaoService userDaoService, UserRepository userRepository, PostJpaRepository postJpaRepository){
        this.userDaoService = userDaoService;
        this.userRepository = userRepository;
        this.postJpaRepository = postJpaRepository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> getUserById(@PathVariable Integer id){
        Optional<User> retrievedUser = userRepository.findById(id);
        if (retrievedUser.isEmpty()){

            throw new UserNotFoundException("id: "+id);
        }
        else{
            EntityModel<User> entityModel = EntityModel.of(retrievedUser.get());
            WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser());
            entityModel.add(linkBuilder.withRel("all-users"));
            return entityModel;
        }
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> getPostOfUSer(@PathVariable Integer id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new UserNotFoundException("Id: "+id);
        }
        else {
            return user.get().getPosts();
        }
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostOfUSer(@PathVariable Integer id, @Valid @RequestBody Post post){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new UserNotFoundException("Id: "+id);
        }
        else {
            post.setUser(user.get());
            System.out.println(post.getId());
            Post createdPost = postJpaRepository.save(post);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                    path("/{id}").buildAndExpand(createdPost.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

//    @DeleteMapping(path = "/jpa/users/{id}/posts/{id}")
//    public ResponseEntity<Object> createPostOfUSer(@PathVariable Integer userId, @PathVariable Integer postId,
//                                                   @Valid @RequestBody Post post){
//        Optional<User> user = userRepository.findById(id);
//        if (user.isEmpty()){
//            throw new UserNotFoundException("Id: "+id);
//        }
//        else {
//            post.setUser(user.get());
//            System.out.println(post.getId());
//            Post createdPost = postJpaRepository.save(post);
//            URI location = ServletUriComponentsBuilder.fromCurrentRequest().
//                    path("/{id}").buildAndExpand(createdPost.getId()).toUri();
//            return ResponseEntity.created(location).build();
//        }
//    }
}
