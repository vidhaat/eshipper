import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClaimSolution } from 'app/shared/model/claim-solution.model';

@Component({
  selector: 'jhi-claim-solution-detail',
  templateUrl: './claim-solution-detail.component.html'
})
export class ClaimSolutionDetailComponent implements OnInit {
  claimSolution: IClaimSolution | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimSolution }) => {
      this.claimSolution = claimSolution;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
