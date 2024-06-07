import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewuploadfileComponent } from './newuploadfile.component';

describe('NewuploadfileComponent', () => {
  let component: NewuploadfileComponent;
  let fixture: ComponentFixture<NewuploadfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewuploadfileComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NewuploadfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
