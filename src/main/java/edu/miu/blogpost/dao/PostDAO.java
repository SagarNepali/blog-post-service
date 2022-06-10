package edu.miu.blogpost.dao;

import edu.miu.blogpost.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO extends JpaRepository<Post,Long> {
}
