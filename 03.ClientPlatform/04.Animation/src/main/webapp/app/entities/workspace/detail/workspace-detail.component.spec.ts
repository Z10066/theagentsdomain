import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { WorkspaceDetailComponent } from './workspace-detail.component';

describe('Workspace Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkspaceDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: WorkspaceDetailComponent,
              resolve: { workspace: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(WorkspaceDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load workspace on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', WorkspaceDetailComponent);

      // THEN
      expect(instance.workspace).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
