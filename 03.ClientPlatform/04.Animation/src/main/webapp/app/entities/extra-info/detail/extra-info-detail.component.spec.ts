import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ExtraInfoDetailComponent } from './extra-info-detail.component';

describe('ExtraInfo Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExtraInfoDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ExtraInfoDetailComponent,
              resolve: { extraInfo: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ExtraInfoDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load extraInfo on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ExtraInfoDetailComponent);

      // THEN
      expect(instance.extraInfo).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
