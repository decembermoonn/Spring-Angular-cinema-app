import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pager',
  templateUrl: './app-pager.component.html',
  styleUrls: ['./app-pager.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AppPagerComponent {
  @Input('totalPages') totalPages!: number;
  @Output('pageChange') pageChange = new EventEmitter<number>();

  page = 0;

  prevPageLimitReached = (): boolean => this.page === 0;
  nextPageLimitReached = (): boolean => this.page === this.totalPages - 1;

  getFirstPage(): void {
    this.page = 0;
    this.pageChange.next(this.page);
  }

  getPrevPage(): void {
    if (this.prevPageLimitReached()) return;
    this.page--;
    this.pageChange.next(this.page);
  }

  getNextPage(): void {
    if (this.nextPageLimitReached()) return;
    this.page++;
    this.pageChange.next(this.page);
  }

  getLastPage(): void {
    this.page = this.totalPages - 1;
    this.pageChange.next(this.page);
  }

  createPageNumbers(): number[] {
    if(this.totalPages <= 3) return [1,2,3].slice(0, this.totalPages);
    if(this.page === 0) return [1,2,3];
    if(this.page === this.totalPages - 1) return [this.totalPages - 2, this.totalPages - 1, this.totalPages];
    return [this.page, this.page + 1, this.page + 2];
  }
}
