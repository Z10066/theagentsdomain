import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateinstagramReelPromptComponent } from './createinstagram-reel-prompt.component';

describe('CreateinstagramReelPromptComponent', () => {
  let component: CreateinstagramReelPromptComponent;
  let fixture: ComponentFixture<CreateinstagramReelPromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateinstagramReelPromptComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateinstagramReelPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
