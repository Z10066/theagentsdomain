import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { VideoProductionDetailComponent } from './video-production-detail.component';

describe('VideoProduction Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VideoProductionDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: VideoProductionDetailComponent,
              resolve: { videoProduction: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(VideoProductionDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load videoProduction on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', VideoProductionDetailComponent);

      // THEN
      expect(instance.videoProduction).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
