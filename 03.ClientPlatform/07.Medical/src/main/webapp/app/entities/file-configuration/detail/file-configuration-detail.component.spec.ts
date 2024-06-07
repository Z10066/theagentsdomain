import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FileConfigurationDetailComponent } from './file-configuration-detail.component';

describe('FileConfiguration Management Detail Component', () => {
  let comp: FileConfigurationDetailComponent;
  let fixture: ComponentFixture<FileConfigurationDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FileConfigurationDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ fileConfiguration: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(FileConfigurationDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FileConfigurationDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load fileConfiguration on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.fileConfiguration).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
