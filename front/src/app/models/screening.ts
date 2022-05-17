import { Movie } from './movie';

export interface ScreeningFromApi {
  id: number;
  movie: Movie;
  beginning: string;
}

export interface MovieWithScreenings {
  movie: Movie;
  screenings: Screening[];
}

interface Screening {
  id: number;
  beginning: string;
}
