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
  subscribeLevel1:boolean;
  subscribeLevel2:boolean;
  subscribeLevel3:boolean;
  subscribeLevel4:boolean;
  subscribeLevel5:boolean;
  commentLevel1:boolean;
  commentLevel2:boolean;
  commentLevel3:boolean;
  commentLevel4:boolean;
  commentLevel5:boolean;
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


    if(this.loginService.currentUser().comment.length>=5){
      this.commentLevel1 = false;
      this.commentLevel2 = false;
      this.commentLevel3 = false;
      this.commentLevel4 = false;
      this.commentLevel5 = true;
    } else if(this.loginService.currentUser().comment.length>=4){
      this.commentLevel1 = false;
      this.commentLevel2 = false;
      this.commentLevel3 = false;
      this.commentLevel4 = true;
      this.commentLevel5 = false;
    } else if(this.loginService.currentUser().comment.length>=3){
      this.commentLevel1 = false;
      this.commentLevel2 = false;
      this.commentLevel3 = true;
      this.commentLevel4 = false;
      this.commentLevel5 = false;
    } else if(this.loginService.currentUser().comment.length>=2){
      this.commentLevel1 = false;
      this.commentLevel2 = true;
      this.commentLevel3 = false;
      this.commentLevel4 = false;
      this.commentLevel5 = false;
    } else if(this.loginService.currentUser().comment.length>=1){
      this.commentLevel1 = true;
      this.commentLevel2 = false;
      this.commentLevel3 = false;
      this.commentLevel4 = false;
      this.commentLevel5 = false;
    }

    if(this.loginService.currentUser().events.length>=5){
      this.subscribeLevel1 = false;
      this.subscribeLevel2 = false;
      this.subscribeLevel3 = false;
      this.subscribeLevel4 = false;
      this.subscribeLevel5 = true;
    } else if(this.loginService.currentUser().events.length>=4){
      this.subscribeLevel1 = false;
      this.subscribeLevel2 = false;
      this.subscribeLevel3 = false;
      this.subscribeLevel4 = true;
      this.subscribeLevel5 = false;
    } else if(this.loginService.currentUser().events.length>=3){
      this.subscribeLevel1 = false;
      this.subscribeLevel2 = false;
      this.subscribeLevel3 = true;
      this.subscribeLevel4 = false;
      this.subscribeLevel5 = false;
    } else if(this.loginService.currentUser().events.length>=2){
      this.subscribeLevel1 = false;
      this.subscribeLevel2 = true;
      this.subscribeLevel3 = false;
      this.subscribeLevel4 = false;
      this.subscribeLevel5 = false;
    } else if(this.loginService.currentUser().events.length>=1){
      this.subscribeLevel1 = true;
      this.subscribeLevel2 = false;
      this.subscribeLevel3 = false;
      this.subscribeLevel4 = false;
      this.subscribeLevel5 = false;
    }
  }




}
