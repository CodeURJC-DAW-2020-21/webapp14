import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { Local } from '../model/local.model';

const BASE_URL = '/api/graphics/';

@Injectable({ providedIn: 'root' })
export class GraphicService {
  constructor(private httpClient: HttpClient ) {
  }

  local: Local;

  private handleError(error: any) {
    console.log('ERROR:');
    console.error(error);
    return throwError('Server error (' + error.status + '): ' + error.text());
  }

  getEventsNumber(): Observable<number> {
    return this.httpClient.get(BASE_URL+'events').pipe(
      map(response => this.extractResponse(response as number))
    );
  }

  getStoresNumber(): Observable<number> {
    return this.httpClient.get(BASE_URL+'stores').pipe(
      map(response => this.extractResponse(response as number))
    );
  }

  getUsersNumber(): Observable<number> {
    return this.httpClient.get(BASE_URL+'users').pipe(
      map(response => this.extractResponse(response as number))
    );
  }

  getCommentsNumber(): Observable<number> {
    return this.httpClient.get(BASE_URL+'comments').pipe(
      map(response => this.extractResponse(response as number))
    );
  }

  private extractResponse(response) {
    return response;
}
}
