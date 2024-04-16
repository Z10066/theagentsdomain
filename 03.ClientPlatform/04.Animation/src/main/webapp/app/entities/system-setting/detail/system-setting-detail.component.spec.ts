import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { SystemSettingDetailComponent } from './system-setting-detail.component';

describe('SystemSetting Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SystemSettingDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: SystemSettingDetailComponent,
              resolve: { systemSetting: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(SystemSettingDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load systemSetting on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', SystemSettingDetailComponent);

      // THEN
      expect(instance.systemSetting).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
