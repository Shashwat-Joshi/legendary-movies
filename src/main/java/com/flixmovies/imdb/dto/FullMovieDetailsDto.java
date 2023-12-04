package com.flixmovies.imdb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class FullMovieDetailsDto {
    private String title;
    private String description;
    private Integer year;
    private Integer rate;
    private List<String> directors;
    private List<String> cast;
    private String genre;
}
