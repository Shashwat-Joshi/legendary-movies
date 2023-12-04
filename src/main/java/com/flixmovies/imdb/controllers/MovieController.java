package com.flixmovies.imdb.controllers;

import com.flixmovies.imdb.dto.CompactMovieDetailsDto;
import com.flixmovies.imdb.dto.FullMovieDetailsDto;
import com.flixmovies.imdb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<CompactMovieDetailsDto>> getAllMovies() {
        List<CompactMovieDetailsDto> allMovies = movieService.getAllMovies();
        return ResponseEntity.ok(allMovies);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<FullMovieDetailsDto> getMovieById(@PathVariable("id") Integer id) {
        FullMovieDetailsDto movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }
}
