import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ICreator } from '../creator.model';
import { CreatorService } from '../service/creator.service';
import { CreatorFormService, CreatorFormGroup } from './creator-form.service';

@Component({
  standalone: true,
  selector: 'jhi-creator-update',
  templateUrl: './creator-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CreatorUpdateComponent implements OnInit {
  isSaving = false;
  creator: ICreator | null = null;

  editForm: CreatorFormGroup = this.creatorFormService.createCreatorFormGroup();

  constructor(
    protected creatorService: CreatorService,
    protected creatorFormService: CreatorFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ creator }) => {
      this.creator = creator;
      if (creator) {
        this.updateForm(creator);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const creator = this.creatorFormService.getCreator(this.editForm);
    if (creator.id !== null) {
      this.subscribeToSaveResponse(this.creatorService.update(creator));
    } else {
      this.subscribeToSaveResponse(this.creatorService.create(creator));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICreator>>): void {
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

  protected updateForm(creator: ICreator): void {
    this.creator = creator;
    this.creatorFormService.resetForm(this.editForm, creator);
  }
}
