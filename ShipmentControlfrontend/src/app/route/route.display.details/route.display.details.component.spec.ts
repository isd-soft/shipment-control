import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RouteDisplayDetailsComponent } from './route.display.details.component';

describe('RouteDisplayDetailsComponent', () => {
  let component: RouteDisplayDetailsComponent;
  let fixture: ComponentFixture<RouteDisplayDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RouteDisplayDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RouteDisplayDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
