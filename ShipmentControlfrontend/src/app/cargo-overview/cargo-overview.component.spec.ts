
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CargoOverviewComponent } from './cargo-overview.component';

describe('CargoOverviewComponent', () => {
  let component: CargoOverviewComponent;
  let fixture: ComponentFixture<CargoOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CargoOverviewComponent ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CargoOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
