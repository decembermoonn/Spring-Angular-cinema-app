import { Component, OnInit } from '@angular/core';
import { MoviesService } from '../../services/movies.service';
import { Observable } from 'rxjs';
import { Movie, MovieRequestData } from '../../models/movie';

@Component({
  selector: 'app-welcome',
  templateUrl: './app-welcome.component.html',
  styleUrls: ['./app-welcome.component.scss'],
})
export class AppWelcomeComponent implements OnInit {
  movies: Observable<Movie[]>;
  moviesRequestData: MovieRequestData = {};

  constructor(private movieService: MoviesService) {
    this.movies = movieService.currentMovies;
  }

  ngOnInit(): void {
    if (!this.movieService.currentMoviesValue.length) {
      this.requestMovies();
    }
  }

  handleSettingsRedefinition(movieRequestData: MovieRequestData): void {
    this.moviesRequestData = movieRequestData;
    this.requestMovies();
  }

  requestMovies(): void {
    this.movieService.getMovies(this.moviesRequestData).subscribe();
  }
}
