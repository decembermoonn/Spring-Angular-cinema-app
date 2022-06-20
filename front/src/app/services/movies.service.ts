import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { BehaviorSubject, map, Observable } from 'rxjs';

import {Movie, MovieRequestData, MovieRequestParams, MovieSort, MoviesWithMetadata} from '../models/movie';

@Injectable({ providedIn: 'root' })
export class MoviesService {
  private currentMoviesSubject: BehaviorSubject<MoviesWithMetadata>;
  public currentMovies: Observable<MoviesWithMetadata>;

  public get currentMoviesValue(): MoviesWithMetadata {
    return this.currentMoviesSubject.value;
  }

  constructor(private http: HttpClient) {
    this.currentMoviesSubject = new BehaviorSubject<MoviesWithMetadata>({movieList: [], totalPages: 0});
    this.currentMovies = this.currentMoviesSubject.asObservable();
  }

  getMovies(movieRequestData: MovieRequestData): Observable<MoviesWithMetadata> {
    const { cinemaApiUrl } = environment;
    const { sort, query, page } = movieRequestData;
    const [sortDirection, sortField] = MoviesService.getSortDirectionAndField(sort);

    const params: MovieRequestParams = {};
    if (sortDirection && sortField) {
      params['direction'] = sortDirection;
      params['sortProperty'] = sortField;
    }
    if (query) params['query'] = query;
    if (page) params['page'] = page;

    return this.http
      .get(`${cinemaApiUrl}/movies`, {
        params: params as Record<string, string | number>,
      })
      .pipe(
        map((response) => {
          const resp = response as MoviesWithMetadata;
          this.currentMoviesSubject.next(resp);
          return resp;
        })
      );
  }

  private static getSortDirectionAndField(sort?: MovieSort): string[] {
    switch (sort) {
      case 'a-z':
        return ['ASC', 'title'];
      case 'z-a':
        return ['DESC', 'title'];
      case 'ratingAsc':
        return ['ASC', 'imDbRating'];
      case 'ratingDesc':
        return ['DESC', 'imDbRating'];
      default:
        return ['', ''];
    }
  }
}
