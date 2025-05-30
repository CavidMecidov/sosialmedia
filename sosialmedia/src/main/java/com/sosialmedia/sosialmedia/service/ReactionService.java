package com.sosialmedia.sosialmedia.service;

import java.util.Map;

public interface ReactionService {
    void likePost(Long postid);

    void dislikePost(Long postid);

    Map<String, Long> getReactions(Long postId);
}
