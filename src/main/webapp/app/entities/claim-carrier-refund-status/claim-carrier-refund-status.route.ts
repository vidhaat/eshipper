import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClaimCarrierRefundStatus, ClaimCarrierRefundStatus } from 'app/shared/model/claim-carrier-refund-status.model';
import { ClaimCarrierRefundStatusService } from './claim-carrier-refund-status.service';
import { ClaimCarrierRefundStatusComponent } from './claim-carrier-refund-status.component';
import { ClaimCarrierRefundStatusDetailComponent } from './claim-carrier-refund-status-detail.component';
import { ClaimCarrierRefundStatusUpdateComponent } from './claim-carrier-refund-status-update.component';

@Injectable({ providedIn: 'root' })
export class ClaimCarrierRefundStatusResolve implements Resolve<IClaimCarrierRefundStatus> {
  constructor(private service: ClaimCarrierRefundStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClaimCarrierRefundStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((claimCarrierRefundStatus: HttpResponse<ClaimCarrierRefundStatus>) => {
          if (claimCarrierRefundStatus.body) {
            return of(claimCarrierRefundStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClaimCarrierRefundStatus());
  }
}

export const claimCarrierRefundStatusRoute: Routes = [
  {
    path: '',
    component: ClaimCarrierRefundStatusComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimCarrierRefundStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ClaimCarrierRefundStatusDetailComponent,
    resolve: {
      claimCarrierRefundStatus: ClaimCarrierRefundStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimCarrierRefundStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ClaimCarrierRefundStatusUpdateComponent,
    resolve: {
      claimCarrierRefundStatus: ClaimCarrierRefundStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimCarrierRefundStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ClaimCarrierRefundStatusUpdateComponent,
    resolve: {
      claimCarrierRefundStatus: ClaimCarrierRefundStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimCarrierRefundStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
