package com.itwill.spring2.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwill.spring2.domain.Post;

public interface PostDao {
    List<Post> selectOrderByIdDesc();
    
    Post selectById(long id);
    
    // TODO: 이거 xml완성하고 커멘트 지우기
    List<Post> selectByKeyword(@Param("category") String category, @Param("keyword") String keyword);
    
    int insert(Post post);
    
    int update(Post post);
    
    int delete(long id);
    
    
}
