import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatevideopromptComponent } from './createvideoprompt.component';

describe('CreatevideopromptComponent', () => {
  let component: CreatevideopromptComponent;
  let fixture: ComponentFixture<CreatevideopromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatevideopromptComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreatevideopromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
