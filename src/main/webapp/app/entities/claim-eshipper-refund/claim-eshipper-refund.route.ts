import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClaimEshipperRefund, ClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';
import { ClaimEshipperRefundService } from './claim-eshipper-refund.service';
import { ClaimEshipperRefundComponent } from './claim-eshipper-refund.component';
import { ClaimEshipperRefundDetailComponent } from './claim-eshipper-refund-detail.component';
import { ClaimEshipperRefundUpdateComponent } from './claim-eshipper-refund-update.component';

@Injectable({ providedIn: 'root' })
export class ClaimEshipperRefundResolve implements Resolve<IClaimEshipperRefund> {
  constructor(private service: ClaimEshipperRefundService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClaimEshipperRefund> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((claimEshipperRefund: HttpResponse<ClaimEshipperRefund>) => {
          if (claimEshipperRefund.body) {
            return of(claimEshipperRefund.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClaimEshipperRefund());
  }
}

export const claimEshipperRefundRoute: Routes = [
  {
    path: '',
    component: ClaimEshipperRefundComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimEshipperRefund.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ClaimEshipperRefundDetailComponent,
    resolve: {
      claimEshipperRefund: ClaimEshipperRefundResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimEshipperRefund.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ClaimEshipperRefundUpdateComponent,
    resolve: {
      claimEshipperRefund: ClaimEshipperRefundResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimEshipperRefund.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ClaimEshipperRefundUpdateComponent,
    resolve: {
      claimEshipperRefund: ClaimEshipperRefundResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimEshipperRefund.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
