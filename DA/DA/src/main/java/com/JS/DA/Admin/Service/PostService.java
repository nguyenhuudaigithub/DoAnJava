package com.JS.DA.Admin.Service;

import com.JS.DA.Admin.Model.Post;
import com.JS.DA.Admin.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public Post getPostById(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()){
            return  optionalPost.get();
        }else{
            throw new RuntimeException("Post not found");
        }
    }
    public void addPost(Post post) {
        postRepository.save(post);
    }
    public void updatePost(Post post) {
        postRepository.save(post);
    }
    public Post savePost(Post post){
        return  postRepository.save(post);
    }

    public void deletePost (Long id){
        postRepository.deleteById(id);
    }
}