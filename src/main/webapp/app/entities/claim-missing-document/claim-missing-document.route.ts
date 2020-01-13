import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClaimMissingDocument, ClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';
import { ClaimMissingDocumentService } from './claim-missing-document.service';
import { ClaimMissingDocumentComponent } from './claim-missing-document.component';
import { ClaimMissingDocumentDetailComponent } from './claim-missing-document-detail.component';
import { ClaimMissingDocumentUpdateComponent } from './claim-missing-document-update.component';

@Injectable({ providedIn: 'root' })
export class ClaimMissingDocumentResolve implements Resolve<IClaimMissingDocument> {
  constructor(private service: ClaimMissingDocumentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClaimMissingDocument> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((claimMissingDocument: HttpResponse<ClaimMissingDocument>) => {
          if (claimMissingDocument.body) {
            return of(claimMissingDocument.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClaimMissingDocument());
  }
}

export const claimMissingDocumentRoute: Routes = [
  {
    path: '',
    component: ClaimMissingDocumentComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimMissingDocument.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ClaimMissingDocumentDetailComponent,
    resolve: {
      claimMissingDocument: ClaimMissingDocumentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimMissingDocument.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ClaimMissingDocumentUpdateComponent,
    resolve: {
      claimMissingDocument: ClaimMissingDocumentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimMissingDocument.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ClaimMissingDocumentUpdateComponent,
    resolve: {
      claimMissingDocument: ClaimMissingDocumentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimMissingDocument.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
