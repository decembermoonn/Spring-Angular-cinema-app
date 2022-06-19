import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppUserReservationPanelComponent } from './app-user-reservation-panel.component';

describe('AppUserReservationPanelComponent', () => {
  let component: AppUserReservationPanelComponent;
  let fixture: ComponentFixture<AppUserReservationPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppUserReservationPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppUserReservationPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
