package com.flixmovies.imdb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompactMovieDetailsDto {
    private String title;
    private Integer year;
    private Integer rate;
}
