import { AppArrayRangePipe } from './app-array-range.pipe';

describe('AppArrayRangePipe', () => {
  it('create an instance', () => {
    const pipe = new AppArrayRangePipe();
    expect(pipe).toBeTruthy();
  });

  it('should return [0,1,2,3,4] array', () => {
    // GIVEN
    const pipe = new AppArrayRangePipe();
    // WHEN
    const range = pipe.transform(5);
    // THEN
    expect(range).toEqual([0, 1, 2, 3, 4]);
  });
});
