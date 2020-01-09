import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShippingClaim } from 'app/shared/model/shipping-claim.model';

@Component({
  selector: 'jhi-shipping-claim-detail',
  templateUrl: './shipping-claim-detail.component.html'
})
export class ShippingClaimDetailComponent implements OnInit {
  shippingClaim: IShippingClaim | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shippingClaim }) => {
      this.shippingClaim = shippingClaim;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
