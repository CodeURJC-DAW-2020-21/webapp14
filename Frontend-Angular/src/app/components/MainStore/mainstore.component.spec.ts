import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { MainStoreComponent } from './mainstore.component';

describe('MainStoreComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        MainStoreComponent
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(MainStoreComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'Frontend-Angular'`, () => {
    const fixture = TestBed.createComponent(MainStoreComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('Frontend-Angular');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(MainStoreComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('Frontend-Angular app is running!');
  });
});
