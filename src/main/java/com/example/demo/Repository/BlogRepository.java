package com.example.demo.Repository;

import com.example.demo.Model.Blog;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    public Blog findBlogById(Integer id);

    public List<Blog> findBlogByUser(User user);

    public  List<Blog> findBlogByTitle(String title);
}
