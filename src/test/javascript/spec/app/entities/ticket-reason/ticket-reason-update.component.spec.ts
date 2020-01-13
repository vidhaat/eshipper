import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { TicketReasonUpdateComponent } from 'app/entities/ticket-reason/ticket-reason-update.component';
import { TicketReasonService } from 'app/entities/ticket-reason/ticket-reason.service';
import { TicketReason } from 'app/shared/model/ticket-reason.model';

describe('Component Tests', () => {
  describe('TicketReason Management Update Component', () => {
    let comp: TicketReasonUpdateComponent;
    let fixture: ComponentFixture<TicketReasonUpdateComponent>;
    let service: TicketReasonService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [TicketReasonUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TicketReasonUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TicketReasonUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TicketReasonService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TicketReason(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TicketReason();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
