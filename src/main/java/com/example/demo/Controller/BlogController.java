package com.example.demo.Controller;

import com.example.demo.Api.ApiResponse;
import com.example.demo.DTO.IN.BlogDTOIN;
import com.example.demo.Model.Blog;
import com.example.demo.Model.User;
import com.example.demo.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllBlogs() {
        return ResponseEntity.ok().body(blogService.getAllBlogs());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid BlogDTOIN blog){
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.ok().body(new ApiResponse("blog added successfully"));
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity<?> updateBlog(@AuthenticationPrincipal User user, @PathVariable Integer blogId, @RequestBody @Valid BlogDTOIN blog) {
        blogService.updateBlog(user.getId(), blogId, blog);
        return ResponseEntity.ok().body(new ApiResponse("blog updated successfully"));
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<?> deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer blogId) {
        blogService.deleteBlog(user.getId(), blogId);
        return ResponseEntity.ok().body(new ApiResponse("blog delete successfully"));
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getAllUserBlogs(@PathVariable Integer id) {
        return ResponseEntity.ok().body(blogService.getBlogsByUser(id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(blogService.getBlogById(id));
    }

    @GetMapping("/get-title/{title}")
    public ResponseEntity<?> getBlogByTitle(@PathVariable String title) {
        return ResponseEntity.ok().body(blogService.getBlogByTitle(title));
    }
}
