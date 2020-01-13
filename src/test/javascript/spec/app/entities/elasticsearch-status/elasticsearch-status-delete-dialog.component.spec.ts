import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EshipperTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ElasticsearchStatusDeleteDialogComponent } from 'app/entities/elasticsearch-status/elasticsearch-status-delete-dialog.component';
import { ElasticsearchStatusService } from 'app/entities/elasticsearch-status/elasticsearch-status.service';

describe('Component Tests', () => {
  describe('ElasticsearchStatus Management Delete Component', () => {
    let comp: ElasticsearchStatusDeleteDialogComponent;
    let fixture: ComponentFixture<ElasticsearchStatusDeleteDialogComponent>;
    let service: ElasticsearchStatusService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticsearchStatusDeleteDialogComponent]
      })
        .overrideTemplate(ElasticsearchStatusDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ElasticsearchStatusDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElasticsearchStatusService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.clear();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
