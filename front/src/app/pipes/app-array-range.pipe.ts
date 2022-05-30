import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'appArrayRange',
})
export class AppArrayRangePipe implements PipeTransform {
  transform(value: number): number[] {
    return [...Array(value).keys()];
  }
}
