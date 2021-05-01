
import {Comment} from "./comment.model"

export interface Event {
	id?: number;
	activities: string;
	name: string;
	description: string;
  	place:string;
	reward: string;
  	people:string;
  	price:string;
  	tag1:string;
  	image:string;
    date:Date;


	comment?: Comment[];


}
