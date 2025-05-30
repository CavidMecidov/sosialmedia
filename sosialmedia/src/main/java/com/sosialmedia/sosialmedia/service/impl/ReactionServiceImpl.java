package com.sosialmedia.sosialmedia.service.impl;

import com.sosialmedia.sosialmedia.entity.Post;
import com.sosialmedia.sosialmedia.entity.Reaction;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.enums.ReactionType;
import com.sosialmedia.sosialmedia.repository.PostRepository;
import com.sosialmedia.sosialmedia.repository.ReactionRepository;
import com.sosialmedia.sosialmedia.repository.UserRepository;
import com.sosialmedia.sosialmedia.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository postReactionRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Override
    public void likePost(Long postid) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();//username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(postid)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Optional<Reaction> existReaction = postReactionRepository.findByUserAndPost(user, post);
        if (existReaction.isPresent()) {
            Reaction reaction = existReaction.get();
            if (reaction.getReactionType() == ReactionType.LIKE)
                return;
            postReactionRepository.delete(reaction);
        }
        Reaction upReaction = new Reaction();
        upReaction.setUser(user);
        upReaction.setPost(post);
        upReaction.setReactionType(ReactionType.LIKE);
        postReactionRepository.save(upReaction);
    }

    @Override
    public void dislikePost(Long postid) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();//username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(postid)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Optional<Reaction> existReaction = postReactionRepository.findByUserAndPost(user, post);
        if (existReaction.isPresent()) {
            Reaction reaction = existReaction.get();
            if (reaction.getReactionType() == ReactionType.DISLIKE)
                return;
            postReactionRepository.delete(reaction);
        }
        Reaction upReaction = new Reaction();
        upReaction.setUser(user);
        upReaction.setPost(post);
        upReaction.setReactionType(ReactionType.DISLIKE);
        postReactionRepository.save(upReaction);
    }

    @Override
    public Map<String, Long> getReactions(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Long like = postReactionRepository.countByPostAndReactionType(post,ReactionType.LIKE);
        Long dislike = postReactionRepository.countByPostAndReactionType(post,ReactionType.DISLIKE);
        Map<String,Long> map =  new HashMap<>();
        map.put("likes:",like);
        map.put("dislikes:",dislike);
        return map;
}

}

