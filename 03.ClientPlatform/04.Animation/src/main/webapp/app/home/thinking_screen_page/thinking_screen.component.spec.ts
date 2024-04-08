import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ThinkingScreenComponent } from './thinking_screen.component';



describe('ThinkingScreenComponent', () => {
  let component: ThinkingScreenComponent;
  let fixture: ComponentFixture<ThinkingScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ThinkingScreenComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ThinkingScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
