import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClaimCarrierRefund, ClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';
import { ClaimCarrierRefundService } from './claim-carrier-refund.service';
import { ClaimCarrierRefundComponent } from './claim-carrier-refund.component';
import { ClaimCarrierRefundDetailComponent } from './claim-carrier-refund-detail.component';
import { ClaimCarrierRefundUpdateComponent } from './claim-carrier-refund-update.component';

@Injectable({ providedIn: 'root' })
export class ClaimCarrierRefundResolve implements Resolve<IClaimCarrierRefund> {
  constructor(private service: ClaimCarrierRefundService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClaimCarrierRefund> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((claimCarrierRefund: HttpResponse<ClaimCarrierRefund>) => {
          if (claimCarrierRefund.body) {
            return of(claimCarrierRefund.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClaimCarrierRefund());
  }
}

export const claimCarrierRefundRoute: Routes = [
  {
    path: '',
    component: ClaimCarrierRefundComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimCarrierRefund.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ClaimCarrierRefundDetailComponent,
    resolve: {
      claimCarrierRefund: ClaimCarrierRefundResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimCarrierRefund.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ClaimCarrierRefundUpdateComponent,
    resolve: {
      claimCarrierRefund: ClaimCarrierRefundResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimCarrierRefund.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ClaimCarrierRefundUpdateComponent,
    resolve: {
      claimCarrierRefund: ClaimCarrierRefundResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimCarrierRefund.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
