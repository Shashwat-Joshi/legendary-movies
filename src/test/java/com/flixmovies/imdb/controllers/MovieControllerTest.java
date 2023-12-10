package com.flixmovies.imdb.controllers;

import com.flixmovies.imdb.dto.CompactMovieDetailsDto;
import com.flixmovies.imdb.dto.FullMovieDetailsDto;
import com.flixmovies.imdb.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class MovieControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private MovieController movieController;

    @MockBean
    private MovieService movieService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void testAllMoviesEndpoint() throws Exception {
        // given
        CompactMovieDetailsDto compactMovieDetailsDto = CompactMovieDetailsDto.builder()
                .title("The Godfather")
                .year(1972)
                .rate(5)
                .build();

        List<CompactMovieDetailsDto> mockResponse = List.of(compactMovieDetailsDto, compactMovieDetailsDto, compactMovieDetailsDto);

        // when
        when(movieService.getAllMovies()).thenReturn(mockResponse);

        // then
        mockMvc.perform(MockMvcRequestBuilders
                    .get("/movies")
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$[0].title").value("The Godfather"))
                .andExpect(jsonPath("$[0].year").value(1972))
                .andExpect(jsonPath("$[0].rate").value(5));
    }

    @Test
    public void testSingleMovieEndpoint() throws Exception {
        // given
        FullMovieDetailsDto mockResponse = FullMovieDetailsDto.builder()
                .title("Fight Club")
                .description("An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more")
                .year(1999)
                .rate(5)
                .directors(List.of("David Fincher"))
                .cast(List.of("Edward Norton", "Brad Pitt", "Helena Bonham Carter"))
                .genre("Thriller")
                .build();

        // when
        when(movieService.getMovieById(any())).thenReturn(mockResponse);

        // then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/movies/{id}", 101)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.title").value("Fight Club"))
                .andExpect(jsonPath("$.description").value("An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more"))
                .andExpect(jsonPath("$.year").value(1999))
                .andExpect(jsonPath("$.rate").value(5))
                .andExpect(jsonPath("$.directors", containsInAnyOrder("David Fincher")))
                .andExpect(jsonPath("$.cast", containsInAnyOrder("Edward Norton", "Brad Pitt", "Helena Bonham Carter")))
                .andExpect(jsonPath("$.genre").value("Thriller"));
    }
}
