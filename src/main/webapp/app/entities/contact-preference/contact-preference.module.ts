import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ContactPreferenceComponent } from './contact-preference.component';
import { ContactPreferenceDetailComponent } from './contact-preference-detail.component';
import { ContactPreferenceUpdateComponent } from './contact-preference-update.component';
import { ContactPreferenceDeleteDialogComponent } from './contact-preference-delete-dialog.component';
import { contactPreferenceRoute } from './contact-preference.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(contactPreferenceRoute)],
  declarations: [
    ContactPreferenceComponent,
    ContactPreferenceDetailComponent,
    ContactPreferenceUpdateComponent,
    ContactPreferenceDeleteDialogComponent
  ],
  entryComponents: [ContactPreferenceDeleteDialogComponent]
})
export class EshipperContactPreferenceModule {}
