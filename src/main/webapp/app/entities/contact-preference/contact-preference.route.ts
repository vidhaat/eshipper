import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IContactPreference, ContactPreference } from 'app/shared/model/contact-preference.model';
import { ContactPreferenceService } from './contact-preference.service';
import { ContactPreferenceComponent } from './contact-preference.component';
import { ContactPreferenceDetailComponent } from './contact-preference-detail.component';
import { ContactPreferenceUpdateComponent } from './contact-preference-update.component';

@Injectable({ providedIn: 'root' })
export class ContactPreferenceResolve implements Resolve<IContactPreference> {
  constructor(private service: ContactPreferenceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IContactPreference> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((contactPreference: HttpResponse<ContactPreference>) => {
          if (contactPreference.body) {
            return of(contactPreference.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ContactPreference());
  }
}

export const contactPreferenceRoute: Routes = [
  {
    path: '',
    component: ContactPreferenceComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.contactPreference.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ContactPreferenceDetailComponent,
    resolve: {
      contactPreference: ContactPreferenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.contactPreference.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ContactPreferenceUpdateComponent,
    resolve: {
      contactPreference: ContactPreferenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.contactPreference.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ContactPreferenceUpdateComponent,
    resolve: {
      contactPreference: ContactPreferenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.contactPreference.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
