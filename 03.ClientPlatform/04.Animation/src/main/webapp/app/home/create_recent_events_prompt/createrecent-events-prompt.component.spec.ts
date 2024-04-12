import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreaterecentEventsPromptComponent } from './createrecent-events-prompt.component';

describe('CreaterecentEventsPromptComponent', () => {
  let component: CreaterecentEventsPromptComponent;
  let fixture: ComponentFixture<CreaterecentEventsPromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreaterecentEventsPromptComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreaterecentEventsPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
