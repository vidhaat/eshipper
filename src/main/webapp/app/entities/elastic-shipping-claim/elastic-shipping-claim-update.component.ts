import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IElasticShippingClaim, ElasticShippingClaim } from 'app/shared/model/elastic-shipping-claim.model';
import { ElasticShippingClaimService } from './elastic-shipping-claim.service';
import { IShippingClaim } from 'app/shared/model/shipping-claim.model';
import { ShippingClaimService } from 'app/entities/shipping-claim/shipping-claim.service';
import { IElasticsearchStatus } from 'app/shared/model/elasticsearch-status.model';
import { ElasticsearchStatusService } from 'app/entities/elasticsearch-status/elasticsearch-status.service';

type SelectableEntity = IShippingClaim | IElasticsearchStatus;

@Component({
  selector: 'jhi-elastic-shipping-claim-update',
  templateUrl: './elastic-shipping-claim-update.component.html'
})
export class ElasticShippingClaimUpdateComponent implements OnInit {
  isSaving = false;

  shippingclaims: IShippingClaim[] = [];

  elasticsearchstatuses: IElasticsearchStatus[] = [];

  editForm = this.fb.group({
    id: [],
    shippingClaimId: [],
    elasticStatusId: []
  });

  constructor(
    protected elasticShippingClaimService: ElasticShippingClaimService,
    protected shippingClaimService: ShippingClaimService,
    protected elasticsearchStatusService: ElasticsearchStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ elasticShippingClaim }) => {
      this.updateForm(elasticShippingClaim);

      this.shippingClaimService
        .query({ filter: 'elasticshippingclaim-is-null' })
        .pipe(
          map((res: HttpResponse<IShippingClaim[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IShippingClaim[]) => {
          if (!elasticShippingClaim.shippingClaimId) {
            this.shippingclaims = resBody;
          } else {
            this.shippingClaimService
              .find(elasticShippingClaim.shippingClaimId)
              .pipe(
                map((subRes: HttpResponse<IShippingClaim>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IShippingClaim[]) => {
                this.shippingclaims = concatRes;
              });
          }
        });

      this.elasticsearchStatusService
        .query()
        .pipe(
          map((res: HttpResponse<IElasticsearchStatus[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IElasticsearchStatus[]) => (this.elasticsearchstatuses = resBody));
    });
  }

  updateForm(elasticShippingClaim: IElasticShippingClaim): void {
    this.editForm.patchValue({
      id: elasticShippingClaim.id,
      shippingClaimId: elasticShippingClaim.shippingClaimId,
      elasticStatusId: elasticShippingClaim.elasticStatusId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const elasticShippingClaim = this.createFromForm();
    if (elasticShippingClaim.id !== undefined) {
      this.subscribeToSaveResponse(this.elasticShippingClaimService.update(elasticShippingClaim));
    } else {
      this.subscribeToSaveResponse(this.elasticShippingClaimService.create(elasticShippingClaim));
    }
  }

  private createFromForm(): IElasticShippingClaim {
    return {
      ...new ElasticShippingClaim(),
      id: this.editForm.get(['id'])!.value,
      shippingClaimId: this.editForm.get(['shippingClaimId'])!.value,
      elasticStatusId: this.editForm.get(['elasticStatusId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IElasticShippingClaim>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
