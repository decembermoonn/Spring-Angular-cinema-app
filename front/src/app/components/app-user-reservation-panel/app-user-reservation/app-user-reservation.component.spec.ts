import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppUserReservationComponent } from './app-user-reservation.component';

describe('AppUserReservationComponent', () => {
  let component: AppUserReservationComponent;
  let fixture: ComponentFixture<AppUserReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppUserReservationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppUserReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
