package com.sosialmedia.sosialmedia.controller;

import com.sosialmedia.sosialmedia.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/post_reactions")
public class ReactionController {
    private final ReactionService reactionService;

    @PostMapping("/{postid}/like")
    public ResponseEntity<String> likePosts(@PathVariable("postid") Long postid) {
        reactionService.likePost(postid);
        return ResponseEntity.ok("Post  liked successfully ");
    }

    @PostMapping("/{postid}/dislike")
    public ResponseEntity<String> dislikePosts(@PathVariable("postid") Long postid) {
        reactionService.dislikePost(postid);
        return ResponseEntity.ok("Post disliked successfully");
    }

    @GetMapping("/{postid}/reactions")
    public ResponseEntity<Map<String, Long>> getReactions(@PathVariable("postid") Long postid) {
        return ResponseEntity.ok(reactionService.getReactions(postid));
    }

}
