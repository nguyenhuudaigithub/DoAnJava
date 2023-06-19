package com.JS.DA.User.Controller;

import com.JS.DA.Admin.Model.Post;
import com.JS.DA.Admin.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Component("userPostController")
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public String PostsList(Model model) {
        List<Post> posts = postService.getAllPost();
        List<Post> filteredPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getIs_activate()) {
                filteredPosts.add(post);
            }
        }
        model.addAttribute("posts", filteredPosts);
        return "/user/post/postsList";
    }

    @GetMapping("/posts/{id}")
    public String readPostForm(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);


        List<Post> posts = postService.getAllPost();
        List<Post> filteredPosts = new ArrayList<>();
        for (Post postl : posts) {
            if (postl.getIs_activate()) {
                filteredPosts.add(postl);
            }
        }
        model.addAttribute("posts", filteredPosts);
        return "user/post/readPostForm";
    }
}
