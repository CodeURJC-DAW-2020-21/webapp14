import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError,map } from 'rxjs/operators';

import { Comment } from '../model/comment.model';

const BASE_URL = '/api/comments/';

@Injectable({ providedIn: 'root' })
export class CommentService {
  constructor(private httpClient: HttpClient) {
  }


  private handleError(error: any) {
    console.log('ERROR:');
    console.error(error);
    return throwError('Server error (' + error.status + '): ' + error.text())
  }

  getComments(): Observable<Comment[]> {
    return this.httpClient.get(BASE_URL).pipe(
      map(response => this.extractResponse(response as Comment))
    );
  }

  getComment(id: number | string): Observable<Comment> {
    return this.httpClient.get(BASE_URL + id + '/').pipe(
      map(response => this.extractResponse(response as Comment))
    ) ;
  }

  addComment(comment: Comment) {
    if (!comment.id) {
      return this.httpClient.post(BASE_URL, comment).pipe(
        map(response => this.extractResponse(response as Comment))
      );
    } else {
      return this.httpClient.put(BASE_URL + comment.id, comment).pipe(
        map(response => this.extractResponse(response as Comment))
      );
    }
  }

  removeComment(comment: Comment) {
    return this.httpClient.delete(BASE_URL + comment.id).pipe(
      map(response => this.extractResponse(response as Comment))
    );
  }
  private extractResponse(response) {
    return response;
}
}
