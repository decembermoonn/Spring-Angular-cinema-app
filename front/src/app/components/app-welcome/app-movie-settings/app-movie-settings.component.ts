import { Component, EventEmitter, OnDestroy, Output } from '@angular/core';
import { MovieRequestData } from '../../../models/movie';
import { FormControl } from '@angular/forms';
import { debounceTime, Subscription } from 'rxjs';

@Component({
  selector: 'app-movie-settings',
  templateUrl: './app-movie-settings.component.html',
  styleUrls: ['./app-movie-settings.component.scss'],
})
export class AppMovieSettingsComponent implements OnDestroy {
  @Output() onSettingsRedefine = new EventEmitter<MovieRequestData>();

  movieSort = new FormControl();
  movieSortValueChangeSub: Subscription;
  movieQuery = new FormControl();
  movieQueryValueChangeSub: Subscription;

  constructor() {
    this.movieSortValueChangeSub = this.movieSort.valueChanges.subscribe(() => this.emitSettings());
    this.movieQueryValueChangeSub = this.movieQuery.valueChanges
      .pipe(debounceTime(1000))
      .subscribe(() => this.emitSettings());
  }

  ngOnDestroy(): void {
    this.movieSortValueChangeSub.unsubscribe();
    this.movieQueryValueChangeSub.unsubscribe();
  }

  emitSettings(): void {
    const movieRequestData: MovieRequestData = {
      page: 0
    };
    const sort = this.movieSort.value;
    const query = this.movieQuery.value;
    if (sort) movieRequestData['sort'] = sort;
    if (query) movieRequestData['query'] = query;

    this.onSettingsRedefine.emit(movieRequestData);
  }
}
