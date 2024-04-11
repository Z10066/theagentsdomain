import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatetiktokVideoPromptComponent } from './createtiktok-video-prompt.component';

describe('CreatetiktokVideoPromptComponent', () => {
  let component: CreatetiktokVideoPromptComponent;
  let fixture: ComponentFixture<CreatetiktokVideoPromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatetiktokVideoPromptComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreatetiktokVideoPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
