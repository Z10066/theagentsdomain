import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WaitnovelComponent } from './waitnovel.component';

describe('WaitnovelComponent', () => {
  let component: WaitnovelComponent;
  let fixture: ComponentFixture<WaitnovelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WaitnovelComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WaitnovelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
