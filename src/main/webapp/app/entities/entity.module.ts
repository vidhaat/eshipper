import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.EshipperRegionModule)
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.EshipperCountryModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.EshipperLocationModule)
      },
      {
        path: 'department',
        loadChildren: () => import('./department/department.module').then(m => m.EshipperDepartmentModule)
      },
      {
        path: 'task',
        loadChildren: () => import('./task/task.module').then(m => m.EshipperTaskModule)
      },
      {
        path: 'employee',
        loadChildren: () => import('./employee/employee.module').then(m => m.EshipperEmployeeModule)
      },
      {
        path: 'job',
        loadChildren: () => import('./job/job.module').then(m => m.EshipperJobModule)
      },
      {
        path: 'job-history',
        loadChildren: () => import('./job-history/job-history.module').then(m => m.EshipperJobHistoryModule)
      },
      {
        path: 'shipping-claim',
        loadChildren: () => import('./shipping-claim/shipping-claim.module').then(m => m.EshipperShippingClaimModule)
      },
      {
        path: 'claim-status',
        loadChildren: () => import('./claim-status/claim-status.module').then(m => m.EshipperClaimStatusModule)
      },
      {
        path: 'contact-preference',
        loadChildren: () => import('./contact-preference/contact-preference.module').then(m => m.EshipperContactPreferenceModule)
      },
      {
        path: 'ticket-reason',
        loadChildren: () => import('./ticket-reason/ticket-reason.module').then(m => m.EshipperTicketReasonModule)
      },
      {
        path: 'shipping-order',
        loadChildren: () => import('./shipping-order/shipping-order.module').then(m => m.EshipperShippingOrderModule)
      },
      {
        path: 'claim-attachment',
        loadChildren: () => import('./claim-attachment/claim-attachment.module').then(m => m.EshipperClaimAttachmentModule)
      },
      {
        path: 'claim-missing-document',
        loadChildren: () => import('./claim-missing-document/claim-missing-document.module').then(m => m.EshipperClaimMissingDocumentModule)
      },
      {
        path: 'claim-document-type',
        loadChildren: () => import('./claim-document-type/claim-document-type.module').then(m => m.EshipperClaimDocumentTypeModule)
      },
      {
        path: 'claim-solution',
        loadChildren: () => import('./claim-solution/claim-solution.module').then(m => m.EshipperClaimSolutionModule)
      },
      {
        path: 'claim-assignee',
        loadChildren: () => import('./claim-assignee/claim-assignee.module').then(m => m.EshipperClaimAssigneeModule)
      },
      {
        path: 'claim-comment',
        loadChildren: () => import('./claim-comment/claim-comment.module').then(m => m.EshipperClaimCommentModule)
      },
      {
        path: 'currency',
        loadChildren: () => import('./currency/currency.module').then(m => m.EshipperCurrencyModule)
      },
      {
        path: 'claim-carrier-refund',
        loadChildren: () => import('./claim-carrier-refund/claim-carrier-refund.module').then(m => m.EshipperClaimCarrierRefundModule)
      },
      {
        path: 'claim-carrier-refund-status',
        loadChildren: () =>
          import('./claim-carrier-refund-status/claim-carrier-refund-status.module').then(m => m.EshipperClaimCarrierRefundStatusModule)
      },
      {
        path: 'claim-eshipper-refund',
        loadChildren: () => import('./claim-eshipper-refund/claim-eshipper-refund.module').then(m => m.EshipperClaimEshipperRefundModule)
      },
      {
        path: 'elastic-shipping-claim',
        loadChildren: () => import('./elastic-shipping-claim/elastic-shipping-claim.module').then(m => m.EshipperElasticShippingClaimModule)
      },
      {
        path: 'elasticsearch-status',
        loadChildren: () => import('./elasticsearch-status/elasticsearch-status.module').then(m => m.EshipperElasticsearchStatusModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class EshipperEntityModule {}
