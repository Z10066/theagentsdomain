import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PublishVideoComponent } from './publish_video.component';



describe('PublishVideoComponent', () => {
  let component: PublishVideoComponent;
  let fixture: ComponentFixture<PublishVideoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublishVideoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PublishVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
