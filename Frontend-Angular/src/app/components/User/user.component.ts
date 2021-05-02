import { EventService } from './../../service/event.service';
import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { UserService } from '../../service/user.service';
import {Event} from '../../model/event.model';


@Component({
  selector: 'users',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UsersComponent implements OnInit{
  title = 'Frontend-Angular';
  mainevent:string
  eventRecommend:Event;
  eventRecommendName:string;
  events:Event[]=[];
  constructor(public loginService: LoginService,public userService: UserService,public eventService: EventService) {

  }
  ngOnInit(){
    let numcultura: number;
    let numdeportes: number;
    let nummusica: number;
    let numvideojuegos: number;
    let map = this.loginService.currentUser().map;
    numcultura = map["Cultura"];
    numdeportes = map["Deporte"];
    nummusica = map["Musica"];
    numvideojuegos= map["Videojuegos"];
    console.log(numvideojuegos);
    console.log(numcultura);
    console.log(numdeportes);
    console.log(nummusica);
    if(numcultura>numdeportes && numcultura>nummusica && numcultura>numvideojuegos){
      this.mainevent="Cultura";
      console.log("a")
    } else if(numdeportes>numcultura && numdeportes>nummusica && numdeportes>numvideojuegos){
      this.mainevent="Deporte";
      console.log("b")
    } else if(nummusica>numcultura && nummusica>numdeportes && nummusica>numvideojuegos){
      this.mainevent="Musica";
      console.log("c")
    } else if(numvideojuegos>numcultura && numvideojuegos>numdeportes && numvideojuegos>nummusica){
      this.mainevent="Videojuegos";
      console.log("d")
    }
    console.log(this.mainevent)
    this.eventService.getEventsByTag(this.mainevent).subscribe(
      eventarray =>{
        eventarray.map(event => this.events.push(event))
        console.log(this.events);
        let num = Math.floor(Math.random()*(((this.events.length+1)-0))+0);
        this.eventRecommend=this.events[num];
        console.log(this.eventRecommend.name);
        this.eventRecommendName = this.eventRecommend.name;
        console.log(this.eventRecommendName);
      },
      error => console.log("error")
    )
  }




}
