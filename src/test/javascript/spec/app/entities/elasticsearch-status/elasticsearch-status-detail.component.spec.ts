import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ElasticsearchStatusDetailComponent } from 'app/entities/elasticsearch-status/elasticsearch-status-detail.component';
import { ElasticsearchStatus } from 'app/shared/model/elasticsearch-status.model';

describe('Component Tests', () => {
  describe('ElasticsearchStatus Management Detail Component', () => {
    let comp: ElasticsearchStatusDetailComponent;
    let fixture: ComponentFixture<ElasticsearchStatusDetailComponent>;
    const route = ({ data: of({ elasticsearchStatus: new ElasticsearchStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticsearchStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ElasticsearchStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ElasticsearchStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load elasticsearchStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.elasticsearchStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
