import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IShippingClaim, ShippingClaim } from 'app/shared/model/shipping-claim.model';
import { ShippingClaimService } from './shipping-claim.service';
import { ShippingClaimComponent } from './shipping-claim.component';
import { ShippingClaimDetailComponent } from './shipping-claim-detail.component';
import { ShippingClaimUpdateComponent } from './shipping-claim-update.component';

@Injectable({ providedIn: 'root' })
export class ShippingClaimResolve implements Resolve<IShippingClaim> {
  constructor(private service: ShippingClaimService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IShippingClaim> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((shippingClaim: HttpResponse<ShippingClaim>) => {
          if (shippingClaim.body) {
            return of(shippingClaim.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ShippingClaim());
  }
}

export const shippingClaimRoute: Routes = [
  {
    path: '',
    component: ShippingClaimComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.shippingClaim.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ShippingClaimDetailComponent,
    resolve: {
      shippingClaim: ShippingClaimResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shippingClaim.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ShippingClaimUpdateComponent,
    resolve: {
      shippingClaim: ShippingClaimResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shippingClaim.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ShippingClaimUpdateComponent,
    resolve: {
      shippingClaim: ShippingClaimResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shippingClaim.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
