
import { Comment } from './comment.model';
import { Event } from './event.model';



export interface Users {
	id?: number;
	mail: string;
	name: string;
  description: string;

	dni: string;
	password: string;
  image:string;
	events?: string[];
  eventSubscribe?:number[];
  comment?: Comment[];
  commentPlaces?:string[];

  imageFile:FormData;
  commentPlace?:[];
  eventSuscribe?:[];
	events?: [];
  comment?:[];
	roles?: string[];
  map: Map<string,number>;
}
