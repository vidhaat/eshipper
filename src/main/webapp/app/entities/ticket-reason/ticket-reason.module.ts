import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { TicketReasonComponent } from './ticket-reason.component';
import { TicketReasonDetailComponent } from './ticket-reason-detail.component';
import { TicketReasonUpdateComponent } from './ticket-reason-update.component';
import { TicketReasonDeleteDialogComponent } from './ticket-reason-delete-dialog.component';
import { ticketReasonRoute } from './ticket-reason.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ticketReasonRoute)],
  declarations: [TicketReasonComponent, TicketReasonDetailComponent, TicketReasonUpdateComponent, TicketReasonDeleteDialogComponent],
  entryComponents: [TicketReasonDeleteDialogComponent]
})
export class EshipperTicketReasonModule {}
