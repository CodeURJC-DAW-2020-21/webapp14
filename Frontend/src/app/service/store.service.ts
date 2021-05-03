import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { Local } from '../model/local.model';
import {Users} from '../model/user.model';

const BASE_URL = '/api/store/';

@Injectable({ providedIn: 'root' })
export class StoreService {
  constructor(private httpClient: HttpClient ) {
  }

  local: Local;

  private handleError(error: any) {
    console.log('ERROR:');
    console.error(error);
    return throwError('Server error (' + error.status + '): ' + error.text());
  }

  getStores(): Observable<Local[]> {
    return this.httpClient.get(BASE_URL).pipe(
      map(response => this.extractResponse(response as Local))
    );
  }

  getStore(id: number | string): Observable<Local> {
    return this.httpClient.get(BASE_URL + id + '/').pipe(
      map(response => this.extractResponse(response as Local))
    );
  }

  currentStore() {
    return this.local;
  }

  addStore(store: Local) {
    if (!store.id) {
      return this.httpClient.post(BASE_URL, store).pipe(
        map(response => this.extractResponse(response as Local))
      );
    } else {
      return this.httpClient.put(BASE_URL + store.id + "/try", store).pipe(
        map(response => this.extractResponse(response as Local))
      );
    }
  }

  removeStore(id:number) {
    return this.httpClient.delete(BASE_URL + id).pipe(
      map(response => this.extractResponse(response as Local))
    );
  }

  updateStore(store: Local) {
    return this.httpClient.put(BASE_URL + store.id + '/', store).pipe(
      map(response => this.extractResponse(response as Local))
    );
  }

  getImage1(id: number): Observable<String> {
    return this.httpClient.get(BASE_URL + id + 'image1').pipe(
      map(response => this.extractResponse(response as String))
    );
  }

  getImage2(id: number): Observable<String> {
    return this.httpClient.get(BASE_URL + id + 'image2').pipe(
      map(response => this.extractResponse(response as String))
    );
  }

  addImage1(store: Local, id: number) {
    if (!store.id) {
      return this.httpClient.post(BASE_URL + id + 'image1', store).pipe(
        catchError(error => this.handleError(error))
      );
    } else {
      return this.httpClient.put(BASE_URL + id + 'image1', store).pipe(
        catchError(error => this.handleError(error))
      );
    }
  }

  addImage2(store: Local, id: number) {
    if (!store.id) {
      return this.httpClient.post(BASE_URL + id + 'image2', store).pipe(
        catchError(error => this.handleError(error))
      );
    } else {
      return this.httpClient.put(BASE_URL + id + 'image2', store).pipe(
        catchError(error => this.handleError(error))
      );
    }
  }

  setStoreImage1(store: Local, formData: FormData) {
    return this.httpClient.post(BASE_URL + store.id + '/image1' , formData)
      .pipe(
        catchError(error => this.handleError(error))
      );

  }

  setStoreImage2(store: Local, formData: FormData) {
    return this.httpClient.post(BASE_URL + store.id + '/image2' , formData)
      .pipe(
        catchError(error => this.handleError(error))
      );

  }
  private extractResponse(response) {
    return response;
}
}
