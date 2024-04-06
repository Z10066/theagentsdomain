import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { KeywordDetailComponent } from './keyword-detail.component';

describe('Keyword Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KeywordDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: KeywordDetailComponent,
              resolve: { keyword: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(KeywordDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load keyword on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', KeywordDetailComponent);

      // THEN
      expect(instance.keyword).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
