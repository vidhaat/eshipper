import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IElasticsearchStatus, ElasticsearchStatus } from 'app/shared/model/elasticsearch-status.model';
import { ElasticsearchStatusService } from './elasticsearch-status.service';
import { IElasticShippingClaim } from 'app/shared/model/elastic-shipping-claim.model';
import { ElasticShippingClaimService } from 'app/entities/elastic-shipping-claim/elastic-shipping-claim.service';

@Component({
  selector: 'jhi-elasticsearch-status-update',
  templateUrl: './elasticsearch-status-update.component.html'
})
export class ElasticsearchStatusUpdateComponent implements OnInit {
  isSaving = false;

  elasticshippingclaims: IElasticShippingClaim[] = [];

  editForm = this.fb.group({
    id: [],
    elasticShippingClaimId: []
  });

  constructor(
    protected elasticsearchStatusService: ElasticsearchStatusService,
    protected elasticShippingClaimService: ElasticShippingClaimService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ elasticsearchStatus }) => {
      this.updateForm(elasticsearchStatus);

      this.elasticShippingClaimService
        .query()
        .pipe(
          map((res: HttpResponse<IElasticShippingClaim[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IElasticShippingClaim[]) => (this.elasticshippingclaims = resBody));
    });
  }

  updateForm(elasticsearchStatus: IElasticsearchStatus): void {
    this.editForm.patchValue({
      id: elasticsearchStatus.id,
      elasticShippingClaimId: elasticsearchStatus.elasticShippingClaimId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const elasticsearchStatus = this.createFromForm();
    if (elasticsearchStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.elasticsearchStatusService.update(elasticsearchStatus));
    } else {
      this.subscribeToSaveResponse(this.elasticsearchStatusService.create(elasticsearchStatus));
    }
  }

  private createFromForm(): IElasticsearchStatus {
    return {
      ...new ElasticsearchStatus(),
      id: this.editForm.get(['id'])!.value,
      elasticShippingClaimId: this.editForm.get(['elasticShippingClaimId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IElasticsearchStatus>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IElasticShippingClaim): any {
    return item.id;
  }
}
