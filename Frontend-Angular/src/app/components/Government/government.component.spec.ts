import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { GovernComponent } from './government.component';

describe('GovernComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        GovernComponent
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(GovernComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'Frontend-Angular'`, () => {
    const fixture = TestBed.createComponent(GovernComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('Frontend-Angular');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(GovernComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('Frontend-Angular app is running!');
  });
});
