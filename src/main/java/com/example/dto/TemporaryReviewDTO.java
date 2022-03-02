package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryReviewDTO {
    private long reviewId;
    private String country;
    private String city;
    private String reviewBody;
    private String advantages;
    private String disadvantages;
    private String conclusion;
    private String userLogin;
}
