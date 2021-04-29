import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Local } from '../model/local.model';
import {error} from '@angular/compiler/src/util';

const BASE_URL = '/api/store';

@Injectable({ providedIn: 'root' })
export class StoreService {
  constructor(private httpClient: HttpClient ) {
  }

  private handleError(error: any) {
    console.log('ERROR:');
    console.error(error);
    return throwError('Server error (' + error.status + '): ' + error.text())
  }

  getStores(): Observable<Local[]> {
    return this.httpClient.get(BASE_URL).pipe(
      catchError(error => this.handleError(error))
    ) as Observable<Local[]>;
  }

  getStore(id: number | string): Observable<Local[]> {
    return this.httpClient.get(BASE_URL + id).pipe(
      catchError(error => this.handleError(error))
    ) as Observable<Local[]>;
  }

  addStore(store: Local) {
    if (!store.id) {
      return this.httpClient.post(BASE_URL, store).pipe(
        catchError(error => this.handleError(error))
      );
    } else {
      return this.httpClient.put(BASE_URL + store.id, store).pipe(
        catchError(error => this.handleError(error))
      );
    }
  }

  removeStore(store: Local) {
    return this.httpClient.delete(BASE_URL + store.id).pipe(
      catchError(error => this.handleError(error))
    );
  }

  updateStore(store: Local) {
    return this.httpClient.put(BASE_URL + store.id, store).pipe(
      catchError(error => this.handleError(error))
    );
  }
}
