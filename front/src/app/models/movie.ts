export interface Movie {
  title: string;
  year: number;
  imageUrl: string;
  crew: string;
  imDbRating: number;
  imDbRatingCount: number;
}

export interface MoviesWithMetadata {
  movieList: Movie[];
  totalPages: number;
}

export type MovieSort = 'a-z'|'z-a'|'ratingAsc'|'ratingDesc';

export interface MovieRequestData {
  sort?: MovieSort;
  query?: string;
  page: number;
}

export interface MovieRequestParams {
  direction?: string,
  sortProperty?: string;
  query?: string;
  page?: number;
}
