import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IElasticShippingClaim } from 'app/shared/model/elastic-shipping-claim.model';

@Component({
  selector: 'jhi-elastic-shipping-claim-detail',
  templateUrl: './elastic-shipping-claim-detail.component.html'
})
export class ElasticShippingClaimDetailComponent implements OnInit {
  elasticShippingClaim: IElasticShippingClaim | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ elasticShippingClaim }) => {
      this.elasticShippingClaim = elasticShippingClaim;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
