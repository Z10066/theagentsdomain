import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { FileConfigurationDetailComponent } from './file-configuration-detail.component';

describe('FileConfiguration Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FileConfigurationDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: FileConfigurationDetailComponent,
              resolve: { fileConfiguration: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FileConfigurationDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load fileConfiguration on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FileConfigurationDetailComponent);

      // THEN
      expect(instance.fileConfiguration).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
