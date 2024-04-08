import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WprkspacesComponent } from './workspaces.component';

describe('WprkspacesComponent', () => {
  let component: WprkspacesComponent;
  let fixture: ComponentFixture<WprkspacesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WprkspacesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WprkspacesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
