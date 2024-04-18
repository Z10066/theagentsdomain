import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IWorkspace, NewWorkspace } from '../workspace.model';

export type PartialUpdateWorkspace = Partial<IWorkspace> & Pick<IWorkspace, 'id'>;

export type EntityResponseType = HttpResponse<IWorkspace>;
export type EntityArrayResponseType = HttpResponse<IWorkspace[]>;

@Injectable({ providedIn: 'root' })
export class WorkspaceService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/workspaces');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(workspace: NewWorkspace): Observable<EntityResponseType> {
    return this.http.post<IWorkspace>(this.resourceUrl, workspace, { observe: 'response' });
  }

  update(workspace: IWorkspace): Observable<EntityResponseType> {
    return this.http.put<IWorkspace>(`${this.resourceUrl}/${this.getWorkspaceIdentifier(workspace)}`, workspace, { observe: 'response' });
  }

  partialUpdate(workspace: PartialUpdateWorkspace): Observable<EntityResponseType> {
    return this.http.patch<IWorkspace>(`${this.resourceUrl}/${this.getWorkspaceIdentifier(workspace)}`, workspace, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWorkspace>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findByIdentifier(identifier: string): Observable<EntityArrayResponseType> {
    return this.http.get<IWorkspace[]>(`${this.resourceUrl}/identifier/${identifier}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWorkspace[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getWorkspaceIdentifier(workspace: Pick<IWorkspace, 'id'>): number {
    return workspace.id;
  }

  compareWorkspace(o1: Pick<IWorkspace, 'id'> | null, o2: Pick<IWorkspace, 'id'> | null): boolean {
    return o1 && o2 ? this.getWorkspaceIdentifier(o1) === this.getWorkspaceIdentifier(o2) : o1 === o2;
  }

  addWorkspaceToCollectionIfMissing<Type extends Pick<IWorkspace, 'id'>>(
    workspaceCollection: Type[],
    ...workspacesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const workspaces: Type[] = workspacesToCheck.filter(isPresent);
    if (workspaces.length > 0) {
      const workspaceCollectionIdentifiers = workspaceCollection.map(workspaceItem => this.getWorkspaceIdentifier(workspaceItem)!);
      const workspacesToAdd = workspaces.filter(workspaceItem => {
        const workspaceIdentifier = this.getWorkspaceIdentifier(workspaceItem);
        if (workspaceCollectionIdentifiers.includes(workspaceIdentifier)) {
          return false;
        }
        workspaceCollectionIdentifiers.push(workspaceIdentifier);
        return true;
      });
      return [...workspacesToAdd, ...workspaceCollection];
    }
    return workspaceCollection;
  }
}
