import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatVideoComponent } from './creat-video.component';

describe('CreatVideoComponent', () => {
  let component: CreatVideoComponent;
  let fixture: ComponentFixture<CreatVideoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatVideoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreatVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
