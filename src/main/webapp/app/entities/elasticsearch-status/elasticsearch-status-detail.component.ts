import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IElasticsearchStatus } from 'app/shared/model/elasticsearch-status.model';

@Component({
  selector: 'jhi-elasticsearch-status-detail',
  templateUrl: './elasticsearch-status-detail.component.html'
})
export class ElasticsearchStatusDetailComponent implements OnInit {
  elasticsearchStatus: IElasticsearchStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ elasticsearchStatus }) => {
      this.elasticsearchStatus = elasticsearchStatus;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
