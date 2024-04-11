import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatepromptComponent } from './createprompt.component';

describe('CreatepromptComponent', () => {
  let component: CreatepromptComponent;
  let fixture: ComponentFixture<CreatepromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatepromptComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreatepromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
