package com.example.inhamonchallenge.domain.comment.service;

import com.example.inhamonchallenge.domain.comment.domain.Comment;
import com.example.inhamonchallenge.domain.comment.dto.CommentResponse;
import com.example.inhamonchallenge.domain.comment.dto.SaveCommentRequest;
import com.example.inhamonchallenge.domain.comment.dto.SaveCommentResponse;
import com.example.inhamonchallenge.domain.comment.exception.NotFoundCommentException;
import com.example.inhamonchallenge.domain.comment.repository.CommentRepository;
import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.habit.exception.NotFoundHabitException;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import com.example.inhamonchallenge.domain.record.domain.Record;
import com.example.inhamonchallenge.domain.record.exception.NotFoundRecordException;
import com.example.inhamonchallenge.domain.record.repository.RecordRepository;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.exception.NotFoundUserException;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import com.example.inhamonchallenge.global.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.inhamonchallenge.domain.common.FeedType.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final RecordRepository recordRepository;

    public SaveCommentResponse addComment(SaveCommentRequest request) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        if (request.getFeedType() == HABIT) {
            Habit habit = habitRepository.findById(request.getFeedId()).orElseThrow(NotFoundHabitException::new);
            return SaveCommentResponse.from(commentRepository.save(request.toEntity(request, user)));
        } else if (request.getFeedType() == RECORD) {
            Record record = recordRepository.findById(request.getFeedId()).orElseThrow(NotFoundRecordException::new);
            return SaveCommentResponse.from(commentRepository.save(request.toEntity(request, user)));
        } else {
            throw new IllegalArgumentException("FeedType is not valid");
        }
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
