package com.example.inhamonchallenge.domain.comment.service;

import com.example.inhamonchallenge.domain.comment.domain.Comment;
import com.example.inhamonchallenge.domain.comment.dto.CommentResponse;
import com.example.inhamonchallenge.domain.comment.dto.SaveCommentRequest;
import com.example.inhamonchallenge.domain.comment.dto.SaveCommentResponse;
import com.example.inhamonchallenge.domain.comment.exception.NotFoundCommentException;
import com.example.inhamonchallenge.domain.comment.repository.CommentRepository;
import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.exception.NotFoundUserException;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import com.example.inhamonchallenge.global.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public SaveCommentResponse addComment(SaveCommentRequest request) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        return SaveCommentResponse.from(commentRepository.save(SaveCommentRequest.toEntity(request, user)));
    }

    public CommentResponse getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);
        return CommentResponse.from(comment);
    }

    public Result<List<CommentResponse>> getCommentList(FeedType feedType, Long feedId) {
        List<Comment> comments = commentRepository.findAllByFeedIdAnAndFeedType(feedId, feedType);
        List<CommentResponse> response = comments.stream().map(comment -> CommentResponse.from(comment))
                .collect(Collectors.toList());
        return new Result<>(response);
    }
}
