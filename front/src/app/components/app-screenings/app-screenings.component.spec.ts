import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppScreeningsComponent } from './app-screenings.component';

describe('AppScreeningsComponent', () => {
  let component: AppScreeningsComponent;
  let fixture: ComponentFixture<AppScreeningsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppScreeningsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppScreeningsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
