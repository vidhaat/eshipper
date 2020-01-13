import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ElasticsearchStatusComponent } from './elasticsearch-status.component';
import { ElasticsearchStatusDetailComponent } from './elasticsearch-status-detail.component';
import { ElasticsearchStatusUpdateComponent } from './elasticsearch-status-update.component';
import { ElasticsearchStatusDeleteDialogComponent } from './elasticsearch-status-delete-dialog.component';
import { elasticsearchStatusRoute } from './elasticsearch-status.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(elasticsearchStatusRoute)],
  declarations: [
    ElasticsearchStatusComponent,
    ElasticsearchStatusDetailComponent,
    ElasticsearchStatusUpdateComponent,
    ElasticsearchStatusDeleteDialogComponent
  ],
  entryComponents: [ElasticsearchStatusDeleteDialogComponent]
})
export class EshipperElasticsearchStatusModule {}
