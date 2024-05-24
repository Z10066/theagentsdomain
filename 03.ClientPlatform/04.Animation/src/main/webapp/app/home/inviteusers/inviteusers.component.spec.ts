import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InviteusersComponent } from './inviteusers.component';

describe('InviteusersComponent', () => {
  let component: InviteusersComponent;
  let fixture: ComponentFixture<InviteusersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InviteusersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InviteusersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
