import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoCircleComponent } from './video-circle.component';

describe('VideoCircleComponent', () => {
  let component: VideoCircleComponent;
  let fixture: ComponentFixture<VideoCircleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VideoCircleComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VideoCircleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
