import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFileConfiguration } from '../file-configuration.model';

@Component({
  selector: 'jhi-file-configuration-detail',
  templateUrl: './file-configuration-detail.component.html',
})
export class FileConfigurationDetailComponent implements OnInit {
  fileConfiguration: IFileConfiguration | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fileConfiguration }) => {
      this.fileConfiguration = fileConfiguration;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
