import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatexplainerPromptComponent } from './createxplainer-prompt.component';

describe('CreatexplainerPromptComponent', () => {
  let component: CreatexplainerPromptComponent;
  let fixture: ComponentFixture<CreatexplainerPromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatexplainerPromptComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreatexplainerPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
