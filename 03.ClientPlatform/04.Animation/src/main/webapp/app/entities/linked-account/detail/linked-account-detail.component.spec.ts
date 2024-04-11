import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { LinkedAccountDetailComponent } from './linked-account-detail.component';

describe('LinkedAccount Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LinkedAccountDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: LinkedAccountDetailComponent,
              resolve: { linkedAccount: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(LinkedAccountDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load linkedAccount on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', LinkedAccountDetailComponent);

      // THEN
      expect(instance.linkedAccount).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
