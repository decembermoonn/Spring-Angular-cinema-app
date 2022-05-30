import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppSeatPanelComponent } from './app-seat-panel.component';

describe('AppSeatPanelComponent', () => {
  let component: AppSeatPanelComponent;
  let fixture: ComponentFixture<AppSeatPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppSeatPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppSeatPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
