import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { SubscriptionServiceDetailComponent } from './subscription-service-detail.component';

describe('SubscriptionService Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubscriptionServiceDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: SubscriptionServiceDetailComponent,
              resolve: { subscriptionService: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(SubscriptionServiceDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load subscriptionService on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', SubscriptionServiceDetailComponent);

      // THEN
      expect(instance.subscriptionService).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
