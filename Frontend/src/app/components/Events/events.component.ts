import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { EventService } from '../../service/event.service';
import { Event } from '../../model/event.model';
import { Router} from '@angular/router';
@Component({
  selector: 'events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent  implements OnInit{
  events: Event[]=[];

  constructor(private router: Router, private eventService: EventService, public loginService: LoginService) {
  }


  ngOnInit() {
    this.eventService.getEvents().subscribe(
      eventarray =>{
        eventarray.map(event =>this.events.push(event))
        console.log(this.events);
      },
      error => console.log("error")
    );
  }

  newEvent() {
    this.router.navigate(['/eventform']);
  }

  navigatetoEvent(id: number){
   this.router.navigate(['mainevent/' + id]);
  }

  
}


