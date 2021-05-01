import {Comment} from "./comment.model"

export interface Users {
	id: number;
	mail: string;
	name: string;
  	description: string;
	DNI: string;
	password: string;
  	image:string;



	//events?: EventSuscribe[];
	commentPlaces?: string[];
	comment?: Comment[];
	roles?: string[];
}
