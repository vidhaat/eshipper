import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClaimComment, ClaimComment } from 'app/shared/model/claim-comment.model';
import { ClaimCommentService } from './claim-comment.service';
import { ClaimCommentComponent } from './claim-comment.component';
import { ClaimCommentDetailComponent } from './claim-comment-detail.component';
import { ClaimCommentUpdateComponent } from './claim-comment-update.component';

@Injectable({ providedIn: 'root' })
export class ClaimCommentResolve implements Resolve<IClaimComment> {
  constructor(private service: ClaimCommentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClaimComment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((claimComment: HttpResponse<ClaimComment>) => {
          if (claimComment.body) {
            return of(claimComment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClaimComment());
  }
}

export const claimCommentRoute: Routes = [
  {
    path: '',
    component: ClaimCommentComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimComment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ClaimCommentDetailComponent,
    resolve: {
      claimComment: ClaimCommentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimComment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ClaimCommentUpdateComponent,
    resolve: {
      claimComment: ClaimCommentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimComment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ClaimCommentUpdateComponent,
    resolve: {
      claimComment: ClaimCommentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.claimComment.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
