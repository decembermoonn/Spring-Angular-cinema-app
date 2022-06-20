import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppPagerComponent } from './app-pager.component';
import {By} from "@angular/platform-browser";

describe('AppPagerComponent', () => {
  let component: AppPagerComponent;
  let fixture: ComponentFixture<AppPagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppPagerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppPagerComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set next page correctly', () => {
    // GIVEN
    component.page = 0;
    component.totalPages = 3;
    // WHEN
    component.getNextPage();
    // THEN
    expect(component.page).toEqual(1);
  });

  it('should set previous page correctly', () => {
    // GIVEN
    component.page = 3;
    component.totalPages = 3;
    // WHEN
    component.getPrevPage();
    // THEN
    expect(component.page).toEqual(2);
  });

  it('should have buttons with proper aria labels', () => {
    // GIVEN
    component.page = 2;
    component.totalPages = 6;
    // WHEN
    fixture.detectChanges();
    const elements = fixture.debugElement.queryAll(By.css('.page-link'));
    const labels = elements.map(e => e.nativeElement.getAttribute('aria-label').toLocaleLowerCase());
    // THEN
    expect(labels).toEqual(['first', 'previous', '2', '3', '4', 'next', 'last']);
  });
});
