package com.flixmovies.imdb.services;

import com.flixmovies.imdb.dto.CompactMovieDetailsDto;
import com.flixmovies.imdb.dto.FullMovieDetailsDto;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MovieServiceTest {
    private final MovieService movieService = new MovieService();

    @Test
    public void getAllMoviesTest() {
        List<CompactMovieDetailsDto> allMovies = movieService.getAllMovies();

        assertEquals(3, allMovies.size());

        assertEquals("The Godfather", allMovies.get(0).getTitle());
        assertEquals(1972, allMovies.get(0).getYear().intValue());
        assertEquals(5, allMovies.get(0).getRate().intValue());
    }

    @Test
    public void getMovieById() {
        FullMovieDetailsDto allMovies = movieService.getMovieById(123);

        assertEquals("Fight Club", allMovies.getTitle());
        assertEquals("An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more", allMovies.getDescription());
        assertEquals(1999, allMovies.getYear().intValue());
        assertEquals(5, allMovies.getRate().intValue());
        assertTrue(allMovies.getDirectors().equals(List.of("David Fincher")));
        assertTrue(allMovies.getCast().equals(List.of("Edward Norton", "Brad Pitt", "Helena Bonham Carter")));
        assertEquals("Thriller", allMovies.getGenre());
    }
}
