import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClaimAttachment, ClaimAttachment } from 'app/shared/model/claim-attachment.model';
import { ClaimAttachmentService } from './claim-attachment.service';
import { ClaimAttachmentComponent } from './claim-attachment.component';
import { ClaimAttachmentDetailComponent } from './claim-attachment-detail.component';
import { ClaimAttachmentUpdateComponent } from './claim-attachment-update.component';

@Injectable({ providedIn: 'root' })
export class ClaimAttachmentResolve implements Resolve<IClaimAttachment> {
  constructor(private service: ClaimAttachmentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClaimAttachment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((claimAttachment: HttpResponse<ClaimAttachment>) => {
          if (claimAttachment.body) {
            return of(claimAttachment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClaimAttachment());
  }
}

export const claimAttachmentRoute: Routes = [
  {
    path: '',
    component: ClaimAttachmentComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimAttachment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ClaimAttachmentDetailComponent,
    resolve: {
      claimAttachment: ClaimAttachmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimAttachment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ClaimAttachmentUpdateComponent,
    resolve: {
      claimAttachment: ClaimAttachmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimAttachment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ClaimAttachmentUpdateComponent,
    resolve: {
      claimAttachment: ClaimAttachmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimAttachment.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
