package com.example.controller.controllers;

import com.example.dto.TemporaryReviewDTO;
import com.example.exception.UserNotFoundException;
import com.example.service.interfaces.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/temporary")
public class TemporaryReviewController {
    private final ReviewService reviewService;

    @Autowired
    public TemporaryReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity createReview(@RequestBody TemporaryReviewDTO dto) throws UserNotFoundException {
        reviewService.saveNewTemporaryReview(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity getTemporaryReviews(@RequestParam(name = "id") Long administratorId){
        List<TemporaryReviewDTO> temporaryReviews = reviewService.getAllTemporaryReviews(administratorId);
        if(temporaryReviews.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.ok(temporaryReviews);
    }

    @PutMapping(path = "/update", produces = "application/json")
    public ResponseEntity updateTemporaryReview(@RequestBody TemporaryReviewDTO dto){
        reviewService.updateTemporaryReview(dto);
        return ResponseEntity.ok().build();
    }
}
