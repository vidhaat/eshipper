import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClaimSolution, ClaimSolution } from 'app/shared/model/claim-solution.model';
import { ClaimSolutionService } from './claim-solution.service';
import { ClaimSolutionComponent } from './claim-solution.component';
import { ClaimSolutionDetailComponent } from './claim-solution-detail.component';
import { ClaimSolutionUpdateComponent } from './claim-solution-update.component';

@Injectable({ providedIn: 'root' })
export class ClaimSolutionResolve implements Resolve<IClaimSolution> {
  constructor(private service: ClaimSolutionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClaimSolution> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((claimSolution: HttpResponse<ClaimSolution>) => {
          if (claimSolution.body) {
            return of(claimSolution.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClaimSolution());
  }
}

export const claimSolutionRoute: Routes = [
  {
    path: '',
    component: ClaimSolutionComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimSolution.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ClaimSolutionDetailComponent,
    resolve: {
      claimSolution: ClaimSolutionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimSolution.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ClaimSolutionUpdateComponent,
    resolve: {
      claimSolution: ClaimSolutionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimSolution.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ClaimSolutionUpdateComponent,
    resolve: {
      claimSolution: ClaimSolutionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimSolution.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
