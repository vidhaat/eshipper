import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IElasticShippingClaim, ElasticShippingClaim } from 'app/shared/model/elastic-shipping-claim.model';
import { ElasticShippingClaimService } from './elastic-shipping-claim.service';
import { ElasticShippingClaimComponent } from './elastic-shipping-claim.component';
import { ElasticShippingClaimDetailComponent } from './elastic-shipping-claim-detail.component';
import { ElasticShippingClaimUpdateComponent } from './elastic-shipping-claim-update.component';

@Injectable({ providedIn: 'root' })
export class ElasticShippingClaimResolve implements Resolve<IElasticShippingClaim> {
  constructor(private service: ElasticShippingClaimService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IElasticShippingClaim> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((elasticShippingClaim: HttpResponse<ElasticShippingClaim>) => {
          if (elasticShippingClaim.body) {
            return of(elasticShippingClaim.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ElasticShippingClaim());
  }
}

export const elasticShippingClaimRoute: Routes = [
  {
    path: '',
    component: ElasticShippingClaimComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.elasticShippingClaim.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ElasticShippingClaimDetailComponent,
    resolve: {
      elasticShippingClaim: ElasticShippingClaimResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.elasticShippingClaim.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ElasticShippingClaimUpdateComponent,
    resolve: {
      elasticShippingClaim: ElasticShippingClaimResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.elasticShippingClaim.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ElasticShippingClaimUpdateComponent,
    resolve: {
      elasticShippingClaim: ElasticShippingClaimResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.elasticShippingClaim.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
