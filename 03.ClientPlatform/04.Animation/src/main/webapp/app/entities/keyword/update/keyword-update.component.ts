import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IKeyword } from '../keyword.model';
import { KeywordService } from '../service/keyword.service';
import { KeywordFormService, KeywordFormGroup } from './keyword-form.service';

@Component({
  standalone: true,
  selector: 'jhi-keyword-update',
  templateUrl: './keyword-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class KeywordUpdateComponent implements OnInit {
  isSaving = false;
  keyword: IKeyword | null = null;

  editForm: KeywordFormGroup = this.keywordFormService.createKeywordFormGroup();

  constructor(
    protected keywordService: KeywordService,
    protected keywordFormService: KeywordFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ keyword }) => {
      this.keyword = keyword;
      if (keyword) {
        this.updateForm(keyword);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const keyword = this.keywordFormService.getKeyword(this.editForm);
    if (keyword.id !== null) {
      this.subscribeToSaveResponse(this.keywordService.update(keyword));
    } else {
      this.subscribeToSaveResponse(this.keywordService.create(keyword));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKeyword>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(keyword: IKeyword): void {
    this.keyword = keyword;
    this.keywordFormService.resetForm(this.editForm, keyword);
  }
}
