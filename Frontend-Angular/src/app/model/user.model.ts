export interface Users {
	id?: number;
	mail: string;
	name: string;
  description: string;
	dni: string;
	password: string;

  image?:string;
  commentPlace?:[];
  eventSuscribe?:[];
	events?: [];
  comment?:[];
	roles?: string[];
}
