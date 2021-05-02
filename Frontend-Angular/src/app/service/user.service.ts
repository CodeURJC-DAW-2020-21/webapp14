import { Users } from './../model/user.model';
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable,throwError } from "rxjs";
import { catchError } from 'rxjs/operators';
import { map } from 'rxjs/operators'

const BASE_URL = '/api/users/';

@Injectable({providedIn: 'root'})
export class UserService{

    constructor(private httpClient:HttpClient){}

    getUsers(): Observable<Users[]>{
        return this.httpClient.get(BASE_URL+'/').pipe(
            catchError(error => this.handleError(error))
        ) as Observable<Users[]>;
    }

    getUser(id: number): Observable<Users>{
        return this.httpClient.get(BASE_URL +'/'+id).pipe(
            catchError(error => this.handleError(error))
        )as Observable<Users>;
    }
    getUsersAux():Observable<Users[]>{
      return this.httpClient.get(BASE_URL+'/').pipe(
        map(response => this.extractResponse(response as Users))
      )
    }
    getCurrentUser(): Observable<Users>{
        return this.httpClient.get(BASE_URL + "/me").pipe(
            catchError(error => this.handleError(error))
        )as Observable<Users>;
    }

    removeUser(id: number){
        return this.httpClient.delete(BASE_URL + id).pipe(
            catchError(error => this.handleError(error))
        )
    }


    RegisterUser(name:string, dni:string, description:string, password:string, mail:string  ):Observable<Users> {
      return this.httpClient.post("/api/users/",{name,dni,description,password,mail}).pipe(
          map(response => this.extractResponse(response as Users))
      )
    }

    addUser(user:Users){
        if(!user.id){
            return this.httpClient.post(BASE_URL, user).pipe(
                catchError(error => this.handleError(error))
            );
        }else{
            return this.httpClient.put(BASE_URL + user.id + "/try", user).pipe(
                catchError(error => this.handleError(error))
            );
        }

    }

    updateUserImage(user:Users, formData:FormData) {
        return this.httpClient.post(BASE_URL + user.id + '/image', formData)
			.pipe(catchError(error => this.handleError(error))
			);
    }
    

    private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}

  private extractResponse(response) {
    return response
}
}
