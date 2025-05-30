package com.sosialmedia.sosialmedia.repository;

import com.sosialmedia.sosialmedia.entity.Post;
import com.sosialmedia.sosialmedia.entity.Reaction;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.enums.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
     //void save(Reaction Reaction);

    Optional<Reaction> findByUserAndPost(User user, Post post);

    Long countByPostAndReactionType(Post post, ReactionType reactionType);

    void deleteByUserAndPost(User user, Post Post);

}
