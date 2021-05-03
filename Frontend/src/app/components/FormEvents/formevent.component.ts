import { Component } from '@angular/core';
import { EventService} from '../../service/event.service';
import { Event } from '../../model/event.model';
import { ActivatedRoute, Router} from '@angular/router';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'formevent',
  templateUrl: './formevent.component.html',
  styleUrls: ['./formevent.component.css']
})
export class FormeventComponent {
  event: Event;
  new: boolean;
  public imagefile:any = []

  constructor(private router: Router,public loginService: LoginService, private activatedRoute: ActivatedRoute, private eventService: EventService) {
    const id = activatedRoute.snapshot.params['id'];
    if (id) {
      eventService.getEvent(id).subscribe(
        event => this.event = event,
        error => console.error(error)
      );
      this.new = false;
    } else {
      this.event = {activities: ' ', name: ' ', description: ' ', place: ' ', reward: ' ', people: ' ', price: ' ', tag1: ' ', imageFile: null, date: null };
    }
  }

  newEvent(){
    this.eventService.addEvent(this.event).subscribe(
      (event: Event) => {this.router.navigate(['/events/']);
      this.UploadFile(event)}),
      error => alert('Error al crear el nuevo evento: ' + error);
  }


  captureFile(event): any {
    const capturedfile = event.target.files[0];
    this.imagefile.push(capturedfile)
    console.log(this.imagefile)

  }


  UploadFile(event:Event):any {
    try{
      const  newformdata = new FormData();
      this.imagefile.forEach(imgfile =>{
        console.log(imgfile);
        newformdata.append('imageFile',imgfile)
      })
      this.eventService.setEventImage(event, newformdata).subscribe(res => {
        console.log('Respuesta del servidor',res);
        })
      } catch (e) {
        console.log('ERROR',e);
      }
  }


}
