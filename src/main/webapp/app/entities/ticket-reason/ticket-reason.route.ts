import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITicketReason, TicketReason } from 'app/shared/model/ticket-reason.model';
import { TicketReasonService } from './ticket-reason.service';
import { TicketReasonComponent } from './ticket-reason.component';
import { TicketReasonDetailComponent } from './ticket-reason-detail.component';
import { TicketReasonUpdateComponent } from './ticket-reason-update.component';

@Injectable({ providedIn: 'root' })
export class TicketReasonResolve implements Resolve<ITicketReason> {
  constructor(private service: TicketReasonService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITicketReason> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ticketReason: HttpResponse<TicketReason>) => {
          if (ticketReason.body) {
            return of(ticketReason.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TicketReason());
  }
}

export const ticketReasonRoute: Routes = [
  {
    path: '',
    component: TicketReasonComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ticketReason.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TicketReasonDetailComponent,
    resolve: {
      ticketReason: TicketReasonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ticketReason.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TicketReasonUpdateComponent,
    resolve: {
      ticketReason: TicketReasonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ticketReason.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TicketReasonUpdateComponent,
    resolve: {
      ticketReason: TicketReasonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ticketReason.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
