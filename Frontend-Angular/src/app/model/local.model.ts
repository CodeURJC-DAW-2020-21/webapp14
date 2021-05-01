import {Comment} from "./comment.model"


export interface Local {
	id?: number;
	activities: string;
	name: string;
	description: string;
  frontdescription: string;
  services:string;
	openDay: string;
  closeDay:string;
  openHour:string;
  closeHour:string;
  street:string;
  latitude:string;
  length:string;

  image1:string;
  image2:string;


	comment?: Comment[];


}
