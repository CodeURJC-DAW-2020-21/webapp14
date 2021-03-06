import { UserService } from './../../service/user.service';
import { Component, OnInit } from '@angular/core';
import { EventService} from '../../service/event.service';
import {Router,ActivatedRoute} from '@angular/router';
import {Event} from '../../model/event.model';
import {Comment} from '../../model/comment.model';
import { CommentService } from '../../service/comment.service';
import {main} from '@angular/compiler-cli/src/main';
import { LoginService } from '../../service/login.service';
import { Users } from 'src/app/model/user.model';

@Component({
  selector: 'mainevent',
  templateUrl: './mainevent.component.html',
  styleUrls: ['./mainevent.component.css']
})
export class MainEventComponent {

  event: Event;
  id: number;

  user:Users;
  comment : Comment;
  text:string;
  date:Date;
  new: boolean;

  comments : Comment[]=[]
  constructor(private router: Router, public loginService: LoginService, public eventService: EventService, private activatedRoute: ActivatedRoute,public commentService: CommentService,public userService: UserService) {

    let id = activatedRoute.snapshot.params['id'];
    this.id = id;
    this.comment = {name:' ',text: ' ',date:  null, image :' '};
    this.user = loginService.currentUser();
  }

  ngOnInit() {
    this.eventService.getEvent(this.id).subscribe(
      mainevent => {
        this.event = mainevent;
        this.comments = this.event.comment;
        console.log(this.event);
      },
      error => console.log("error")
    );
  }

  subscribeToEvent(){
    let currentUser = this.loginService.currentUser();
    let useraux = currentUser;
    console.log(useraux);

    if (!useraux.eventSuscribe.includes(this.event.id)){

    useraux.events.push(this.event.name);
    useraux.eventSuscribe.push(this.event.id);
    let firstVal = useraux.map[this.event.tag1];
    useraux.map[this.event.tag1]=firstVal+1;
    console.log(useraux.map);
    this.userService.addUser(useraux).subscribe(
      user =>{
        console.log(user);
      }
    );
    }
    else {
      alert("Ya estas suscrito a este evento")
    }
    }



  deleteEvent(id:number){
      this.eventService.removeEvent(id).subscribe(
        _ => this.router.navigate(['/events']),
        error => console.log(error)
      );
  }
  newCommentEvent(){
    console.log(this.user);
    this.commentService.addComment(this.comment).subscribe(
      (comentario:Comment)=> {console.log(comentario)
        this.event.comment.push(comentario)
        this.eventService.addEvent(this.event).subscribe(
          (evento:Event) => {console.log(evento)
            this.user.comment.push(comentario);
            this.user.commentPlaces.push(evento.name);
            this.userService.addUser(this.user).subscribe(
              (usuario:Users) => console.log(usuario),
               error => alert('Error al actualizar el usuario : ' + error)
        );
          },
          error => alert('Error al actualizar el evento : ' + error)
        );
      },
      error => alert('Error al crear el comentario: ' + error)
    );



  }
}

