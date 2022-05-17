import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppScreeningsSettingsComponent } from './app-screenings-settings.component';

describe('AppScreeningsSettingsComponent', () => {
  let component: AppScreeningsSettingsComponent;
  let fixture: ComponentFixture<AppScreeningsSettingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppScreeningsSettingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppScreeningsSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
