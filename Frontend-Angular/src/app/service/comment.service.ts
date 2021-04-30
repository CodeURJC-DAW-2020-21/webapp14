import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

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
      catchError(error => this.handleError(error))
    ) as Observable<Comment[]>;
  }

  getComment(id: number | string): Observable<Comment> {
    return this.httpClient.get(BASE_URL + id).pipe(
      catchError(error => this.handleError(error))
    ) as Observable<Comment>;
  }

  addComment(comment: Comment) {
    if (!comment.id) {
      return this.httpClient.post(BASE_URL, comment).pipe(
        catchError(error => this.handleError(error))
      );
    } else {
      return this.httpClient.put(BASE_URL + comment.id, comment).pipe(
        catchError(error => this.handleError(error))
      );
    }
  }

  removeComment(comment: Comment) {
    return this.httpClient.delete(BASE_URL + comment.id).pipe(
      catchError(error => this.handleError(error))
    );
  }

  updateComment(comment: Comment){
    return this.httpClient.put(BASE_URL + comment.id, comment).pipe(
      catchError(error => this.handleError(error))
    );
  }
}
