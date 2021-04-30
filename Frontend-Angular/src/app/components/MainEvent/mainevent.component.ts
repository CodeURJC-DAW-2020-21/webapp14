import { Component, OnInit } from '@angular/core';
import { EventService} from '../../service/event.service';
import {ActivatedRoute} from '@angular/router';
import {Event} from '../../model/event.model';
import {main} from '@angular/compiler-cli/src/main';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'mainevent',
  templateUrl: './mainevent.component.html',
  styleUrls: ['./mainevent.component.css']
})
export class MainEventComponent {

  event: Event;
  id: number;
  constructor(public loginService: LoginService, public eventService: EventService, private activatedRoute: ActivatedRoute) {
    let id = activatedRoute.snapshot.params['id'];
    this.id = id;
  }

  ngOnInit() {
    this.eventService.getEvent(this.id).subscribe(
      mainevent => {
        this.event = mainevent;
        console.log(this.event);
      },
      error => console.log("error")
    );
  }
}
