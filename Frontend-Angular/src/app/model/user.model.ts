import { Comment } from './comment.model';
import { Event } from './event.model';


export interface Users {
	id: number;
	mail: string;
	name: string;
  description: string;
	DNI: string;
	password: string;
  image:string;
	events: string[];
  eventSubscribe:number[];
  comment?: Comment[];
  commentPlaces?:string[];
	roles?: string[];
  map: Map<string,number>;
}
