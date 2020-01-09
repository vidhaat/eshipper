import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClaimStatus, ClaimStatus } from 'app/shared/model/claim-status.model';
import { ClaimStatusService } from './claim-status.service';
import { ClaimStatusComponent } from './claim-status.component';
import { ClaimStatusDetailComponent } from './claim-status-detail.component';
import { ClaimStatusUpdateComponent } from './claim-status-update.component';

@Injectable({ providedIn: 'root' })
export class ClaimStatusResolve implements Resolve<IClaimStatus> {
  constructor(private service: ClaimStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClaimStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((claimStatus: HttpResponse<ClaimStatus>) => {
          if (claimStatus.body) {
            return of(claimStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClaimStatus());
  }
}

export const claimStatusRoute: Routes = [
  {
    path: '',
    component: ClaimStatusComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ClaimStatusDetailComponent,
    resolve: {
      claimStatus: ClaimStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ClaimStatusUpdateComponent,
    resolve: {
      claimStatus: ClaimStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ClaimStatusUpdateComponent,
    resolve: {
      claimStatus: ClaimStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
