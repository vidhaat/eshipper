import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClaimDocumentType, ClaimDocumentType } from 'app/shared/model/claim-document-type.model';
import { ClaimDocumentTypeService } from './claim-document-type.service';
import { ClaimDocumentTypeComponent } from './claim-document-type.component';
import { ClaimDocumentTypeDetailComponent } from './claim-document-type-detail.component';
import { ClaimDocumentTypeUpdateComponent } from './claim-document-type-update.component';

@Injectable({ providedIn: 'root' })
export class ClaimDocumentTypeResolve implements Resolve<IClaimDocumentType> {
  constructor(private service: ClaimDocumentTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClaimDocumentType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((claimDocumentType: HttpResponse<ClaimDocumentType>) => {
          if (claimDocumentType.body) {
            return of(claimDocumentType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClaimDocumentType());
  }
}

export const claimDocumentTypeRoute: Routes = [
  {
    path: '',
    component: ClaimDocumentTypeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimDocumentType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ClaimDocumentTypeDetailComponent,
    resolve: {
      claimDocumentType: ClaimDocumentTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimDocumentType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ClaimDocumentTypeUpdateComponent,
    resolve: {
      claimDocumentType: ClaimDocumentTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimDocumentType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ClaimDocumentTypeUpdateComponent,
    resolve: {
      claimDocumentType: ClaimDocumentTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimDocumentType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
