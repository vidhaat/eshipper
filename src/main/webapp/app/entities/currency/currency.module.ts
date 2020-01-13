import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { CurrencyComponent } from './currency.component';
import { CurrencyDetailComponent } from './currency-detail.component';
import { CurrencyUpdateComponent } from './currency-update.component';
import { CurrencyDeleteDialogComponent } from './currency-delete-dialog.component';
import { currencyRoute } from './currency.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(currencyRoute)],
  declarations: [CurrencyComponent, CurrencyDetailComponent, CurrencyUpdateComponent, CurrencyDeleteDialogComponent],
  entryComponents: [CurrencyDeleteDialogComponent]
})
export class EshipperCurrencyModule {}
