package com.example.demo.Service;

import com.example.demo.Api.ApiException;
import com.example.demo.DTO.IN.BlogDTOIN;
import com.example.demo.Model.Blog;
import com.example.demo.Model.User;
import com.example.demo.Repository.AuthRepository;
import com.example.demo.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    //CRUD
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public void addBlog(Integer userId, BlogDTOIN blogDTOIN) {
        Blog blog = convertToBlog(blogDTOIN);
        User user = authRepository.findUserById(userId);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Integer blogId, BlogDTOIN blog) {
        Blog oldBlog = blogRepository.findBlogById(blogId);
        User user = authRepository.findUserById(userId);

        if(oldBlog == null)
            throw new ApiException("Blog not found");
        else if(!oldBlog.getUser().getId().equals(user.getId()))
            throw new ApiException("Sorry, you are not have the authority to update this blog!");

        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer userId, Integer todoId) {
        Blog oldBlog = blogRepository.findBlogById(todoId);
        User user = authRepository.findUserById(userId);

        if(oldBlog == null)
            throw new ApiException("Todo not found");
        else if(!oldBlog.getUser().getId().equals(user.getId()))
            throw new ApiException("Sorry, you are not have the authority to delete this todo!");

        blogRepository.delete(oldBlog);
    }


    //EXTRA ENDPOINTS
    public List<Blog> getBlogsByUser(Integer userId) {
        User user = authRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }

        List<Blog> blogs = blogRepository.findBlogByUser(user);
        if (blogs.isEmpty()) {
            throw new ApiException("No blogs found");
        }
        return blogs;
    }

    public Blog getBlogById(Integer id) {
        Blog blog = blogRepository.findBlogById(id);

        if (blog == null) {
            throw new ApiException("Blog not found");
        }
        return blog;
    }

    public List<Blog> getBlogByTitle(String title) {
        List<Blog> blogs = blogRepository.findBlogByTitle(title);
        if (blogs.isEmpty()) {
            throw new ApiException("No blogs found");
        }
        return blogs;
    }


    //HELPER METHODS
    public Blog convertToBlog(BlogDTOIN blogDTOIN) {
        Blog blog = new Blog();
        blog.setTitle(blogDTOIN.getTitle());
        blog.setBody(blogDTOIN.getBody());
        return blog;
    }

}
