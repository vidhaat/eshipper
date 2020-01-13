import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimStatusDetailComponent } from 'app/entities/claim-status/claim-status-detail.component';
import { ClaimStatus } from 'app/shared/model/claim-status.model';

describe('Component Tests', () => {
  describe('ClaimStatus Management Detail Component', () => {
    let comp: ClaimStatusDetailComponent;
    let fixture: ComponentFixture<ClaimStatusDetailComponent>;
    const route = ({ data: of({ claimStatus: new ClaimStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ClaimStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClaimStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load claimStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.claimStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
