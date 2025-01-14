package com.mahin.restful.restfulApi.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository  extends JpaRepository<Post, Integer> {
}
