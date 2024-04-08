import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RenderingVideoComponent } from './rendering_video.component';



describe('RenderingVideoComponent', () => {
  let component: RenderingVideoComponent;
  let fixture: ComponentFixture<RenderingVideoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RenderingVideoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RenderingVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
