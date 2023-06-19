package com.JS.DA.Admin.Controller;

import com.JS.DA.Admin.Model.Post;
import com.JS.DA.Admin.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/admin/posts")
    public String PostsList(Model model) {
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts", posts);
        return "/admin/post/postsList";
    }

    @GetMapping("/admin/posts/add")
    public String addPostForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "/admin/post/addPostForm";
    }

    @PostMapping("/admin/posts/add")
    public String addPost(@ModelAttribute("post") Post post) {
        postService.addPost(post);
        return "redirect:/admin/posts";
    }
    @GetMapping("/admin/posts/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/edit/{id}")
    public String editPostForm(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "/admin/post/editPostForm";
    }

    @PostMapping("/admin/posts/edit/{id}")
    public String editPost(@PathVariable("id") Long id, @ModelAttribute("post") Post updatedPost) {
        Post existingPost = postService.getPostById(id);
        existingPost.setName(updatedPost.getName());
        existingPost.setImage(updatedPost.getImage());
        existingPost.setDescription(updatedPost.getDescription());
        existingPost.setDetail(updatedPost.getDetail());
        existingPost.setActivate_home(updatedPost.getActivate_home());
        existingPost.setIs_activate(updatedPost.getIs_activate());
        postService.updatePost(existingPost);
        return "redirect:/admin/posts";
    }
}
