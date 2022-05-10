import { Component, Input, OnInit } from '@angular/core';
import { Movie } from '../../../models/movie';

@Component({
  selector: 'app-movie',
  templateUrl: './app-movie.component.html',
  styleUrls: ['./app-movie.component.scss'],
})
export class AppMovieComponent {
  @Input() movie!: Movie;

  constructor() {

  }
}
