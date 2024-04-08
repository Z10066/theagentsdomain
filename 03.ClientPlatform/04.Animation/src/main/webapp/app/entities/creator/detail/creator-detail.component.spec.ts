import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CreatorDetailComponent } from './creator-detail.component';

describe('Creator Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatorDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CreatorDetailComponent,
              resolve: { creator: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(CreatorDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load creator on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CreatorDetailComponent);

      // THEN
      expect(instance.creator).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
