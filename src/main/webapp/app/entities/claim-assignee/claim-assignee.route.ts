import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClaimAssignee, ClaimAssignee } from 'app/shared/model/claim-assignee.model';
import { ClaimAssigneeService } from './claim-assignee.service';
import { ClaimAssigneeComponent } from './claim-assignee.component';
import { ClaimAssigneeDetailComponent } from './claim-assignee-detail.component';
import { ClaimAssigneeUpdateComponent } from './claim-assignee-update.component';

@Injectable({ providedIn: 'root' })
export class ClaimAssigneeResolve implements Resolve<IClaimAssignee> {
  constructor(private service: ClaimAssigneeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClaimAssignee> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((claimAssignee: HttpResponse<ClaimAssignee>) => {
          if (claimAssignee.body) {
            return of(claimAssignee.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClaimAssignee());
  }
}

export const claimAssigneeRoute: Routes = [
  {
    path: '',
    component: ClaimAssigneeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimAssignee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ClaimAssigneeDetailComponent,
    resolve: {
      claimAssignee: ClaimAssigneeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimAssignee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ClaimAssigneeUpdateComponent,
    resolve: {
      claimAssignee: ClaimAssigneeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimAssignee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ClaimAssigneeUpdateComponent,
    resolve: {
      claimAssignee: ClaimAssigneeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimAssignee.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
