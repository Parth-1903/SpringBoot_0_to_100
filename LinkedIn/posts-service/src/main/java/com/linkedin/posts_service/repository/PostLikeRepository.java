package com.linkedin.posts_service.repository;

import com.linkedin.posts_service.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

	boolean existsByUserIdAndPostId(Long userId, Long postId);

	void deleteByUserIdAndPostId(long userId, Long postId);
}
