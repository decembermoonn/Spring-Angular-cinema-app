import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppReservationComponent } from './app-reservation.component';

describe('AppReservationComponent', () => {
  let component: AppReservationComponent;
  let fixture: ComponentFixture<AppReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
