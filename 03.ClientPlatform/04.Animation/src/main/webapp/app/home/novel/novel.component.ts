import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { INovel } from 'app/entities/novel/novel.model';
import { NovelService } from 'app/entities/novel/service/novel.service';
import { NovelFormGroup, NovelFormService } from 'app/entities/novel/update/novel-form.service';
import SharedModule from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { FootMenuComponent } from 'app/layouts/foot-menu/foot-menu.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'jhi-novel',
  standalone: true,
  imports: [SharedModule, RouterModule, FootMenuComponent, ReactiveFormsModule],
  templateUrl: './novel.component.html',
  styleUrls: ['./novel.component.scss']
})
export class NovelComponent implements OnInit {
  isSaving = false;
  novel: INovel | null = null;
  
  editForm: NovelFormGroup = this.novelFormService.createNovelFormGroup();

  constructor(
    protected novelService: NovelService,
    protected novelFormService: NovelFormService,
    protected activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ novel }) => {
      this.novel = novel;
      if (novel) {
        this.updateForm(novel);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    console.log("save")
    this.isSaving = true;
    const novel = this.novelFormService.getNovel(this.editForm);
    if (novel.id !== null) {
      this.subscribeToSaveResponse(this.novelService.update(novel));
    } else {
      this.subscribeToSaveResponse(this.novelService.create(novel));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INovel>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError()
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

  protected updateForm(novel: INovel): void {
    this.novel = novel;
    this.novelFormService.resetForm(this.editForm, novel);
  }
}
