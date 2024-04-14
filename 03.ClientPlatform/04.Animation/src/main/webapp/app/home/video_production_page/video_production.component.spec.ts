import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoProductionsComponent } from './video_production.component';

describe('VideoProductionsComponent', () => {
  let component: VideoProductionsComponent;
  let fixture: ComponentFixture<VideoProductionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VideoProductionsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VideoProductionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
