import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginRegisterNavBarComponent } from './login-register-nav-bar.component';

describe('LoginRegisterNavBarComponent', () => {
  let component: LoginRegisterNavBarComponent;
  let fixture: ComponentFixture<LoginRegisterNavBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginRegisterNavBarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginRegisterNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
