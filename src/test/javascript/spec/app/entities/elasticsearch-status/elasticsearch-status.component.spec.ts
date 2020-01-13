import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ElasticsearchStatusComponent } from 'app/entities/elasticsearch-status/elasticsearch-status.component';
import { ElasticsearchStatusService } from 'app/entities/elasticsearch-status/elasticsearch-status.service';
import { ElasticsearchStatus } from 'app/shared/model/elasticsearch-status.model';

describe('Component Tests', () => {
  describe('ElasticsearchStatus Management Component', () => {
    let comp: ElasticsearchStatusComponent;
    let fixture: ComponentFixture<ElasticsearchStatusComponent>;
    let service: ElasticsearchStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticsearchStatusComponent],
        providers: []
      })
        .overrideTemplate(ElasticsearchStatusComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ElasticsearchStatusComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElasticsearchStatusService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ElasticsearchStatus(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.elasticsearchStatuses && comp.elasticsearchStatuses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
