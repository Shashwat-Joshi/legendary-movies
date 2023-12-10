package com.flixmovies.imdb.services;

import com.flixmovies.imdb.dto.CompactMovieDetailsDto;
import com.flixmovies.imdb.dto.FullMovieDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    public List<CompactMovieDetailsDto> getAllMovies() {
        return List.of(
                new CompactMovieDetailsDto(
                        "The Godfather",
                        1972,
                        5
                ),
                new CompactMovieDetailsDto(
                        "The Godfather",
                        1972,
                        5
                ),
                new CompactMovieDetailsDto(
                        "The Godfather",
                        1972,
                        5
                )
        );
    }

    public FullMovieDetailsDto getMovieById(Integer movieId) {
        return new FullMovieDetailsDto(
                "Fight Club",
                "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more",
                1999,
                5,
                List.of("David Fincher"),
                List.of("Edward Norton", "Brad Pitt", "Helena Bonham Carter"),
                "Thriller"
        );
    }
}
