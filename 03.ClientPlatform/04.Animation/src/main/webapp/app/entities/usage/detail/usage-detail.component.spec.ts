import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { UsageDetailComponent } from './usage-detail.component';

describe('Usage Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UsageDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: UsageDetailComponent,
              resolve: { usage: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(UsageDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load usage on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', UsageDetailComponent);

      // THEN
      expect(instance.usage).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
