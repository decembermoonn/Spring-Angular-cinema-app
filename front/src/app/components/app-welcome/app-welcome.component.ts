import { Component, OnInit } from '@angular/core';
import { MoviesService } from '../../services/movies.service';
import {Movie, MovieRequestData } from '../../models/movie';

@Component({
  selector: 'app-welcome',
  templateUrl: './app-welcome.component.html',
  styleUrls: ['./app-welcome.component.scss'],
})
export class AppWelcomeComponent implements OnInit {
  movies: Movie[] = [];
  totalPages = 0;
  moviesRequestData: MovieRequestData = {
    page: 0,
  };

  constructor(private movieService: MoviesService) {
    movieService.currentMovies.subscribe((val) => {
      this.movies = val.movieList;
      this.totalPages = val.totalPages;
    });
  }

  ngOnInit(): void {
    if (!this.movieService.currentMoviesValue.movieList.length) {
      this.requestMovies();
    }
  }

  handleSettingsRedefinition(movieRequestData: MovieRequestData): void {
    const { page } = this.moviesRequestData;
    movieRequestData.page = page;
    this.moviesRequestData = movieRequestData;
    this.requestMovies();
  }

  requestMovies(): void {
    this.movieService.getMovies(this.moviesRequestData).subscribe();
  }

  handlePageChange(page: number): void {
    this.moviesRequestData.page = page;
    this.requestMovies();
  }
}
