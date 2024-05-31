import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { NovelDetailComponent } from './novel-detail.component';

describe('Novel Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NovelDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: NovelDetailComponent,
              resolve: { novel: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(NovelDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load novel on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', NovelDetailComponent);

      // THEN
      expect(instance.novel).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
