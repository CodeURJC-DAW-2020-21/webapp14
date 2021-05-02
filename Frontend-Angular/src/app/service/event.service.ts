import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable,throwError } from "rxjs";
import { Event } from "../model/event.model";
import { catchError, map } from 'rxjs/operators';

const BASE_URL = '/api/events/';

@Injectable({providedIn: 'root'})
export class EventService{

    constructor(private httpClient:HttpClient){}

    event: Event;

    getEvents(): Observable<Event[]>{
        return this.httpClient.get(BASE_URL).pipe(
          map(response => this.extractResponse(response as Event))
        );
    }

    getEvent(id:number):Observable<Event>{
        return this.httpClient.get(BASE_URL + id).pipe(
            catchError(error => this.handleError(error))
        )as Observable<Event>;
    }

    removeEvent(id: number){
        return this.httpClient.delete(BASE_URL + id).pipe(
            map(response => this.extractResponse(response as Event))
        )
    }

    addEvent(event:Event){
        if(!event.id){
            return this.httpClient.post(BASE_URL, event).pipe(
                catchError(error => this.handleError(error))
            );
        }else{
            return this.httpClient.put(BASE_URL + event.id + "/try", event).pipe(
                catchError(error => this.handleError(error))
            );
        }
    }

    getImage(id: number):Observable<String>{
        return this.httpClient.get(BASE_URL + id + "image").pipe(
            catchError(error => this.handleError(error))
        )as Observable<String>;
    }

    addImage(event:Event, Id:number){
        if(!event.id){
            return this.httpClient.post(BASE_URL + Id + "image", event).pipe(
                catchError(error => this.handleError(error))
            );
        }else{
            return this.httpClient.put(BASE_URL + Id + "image", event).pipe(
                catchError(error => this.handleError(error))
            );
        }
    }
    private extractResponse(response) {
      return response;}

    private handleError(error: any) {
		  console.log("ERROR:");
		  console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}
