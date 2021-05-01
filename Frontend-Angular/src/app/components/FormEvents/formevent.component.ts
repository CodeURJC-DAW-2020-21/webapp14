import { Component } from '@angular/core';
import { EventService} from '../../service/event.service';
import { Event } from '../../model/event.model';
import { ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'formevent',
  templateUrl: './formevent.component.html',
  styleUrls: ['./formevent.component.css']
})
export class FormeventComponent {
  event: Event;
  new: boolean;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private eventService: EventService) {
    const id = activatedRoute.snapshot.params['id'];
    if (id) {
      eventService.getEvent(id).subscribe(
        event => this.event = event,
        error => console.error(error)
      );
      this.new = false;
    } else {
      this.event = {activities: ' ', name: ' ', description: ' ', place: ' ', reward: ' ', people: ' ', price: ' ', tag1: ' ', image: ' ', date: null };
    }
  }

  newEvent(){
    this.eventService.addEvent(this.event).subscribe(
      (event: Event) => this.router.navigate(['/mainevent/', event.id]),
      error => alert('Error al crear el nuevo evento: ' + error)
    );
  }

}
