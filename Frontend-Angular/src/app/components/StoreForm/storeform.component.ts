import { Component } from '@angular/core';
import { StoreService } from '../../service/store.service';
import { Local } from '../../model/local.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'storeform',
  templateUrl: './storeform.component.html',
  styleUrls: ['./storeform.component.css']
})
export class StoreFormComponent {
  store: Local;
  new: boolean;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private storeService: StoreService) {
    const id = activatedRoute.snapshot.params['id'];
    if (id) {
      storeService.getStore(id).subscribe(
        store => this.store = store,
        error => console.log(error)
      );
      this.new = false;
    } else {
      this.store = {activities: ' ', name: ' ', description: ' ', frontdescription: ' ', services: ' ', openDay: ' ', closeDat: ' ', openHour: ' ', closeHour: ' ', street: ' ', latitude: ' ', length: ' ', image1: ' ', image2: ' '};
    }
  }

  newStore() {
    this.storeService.addStore(this.store).subscribe(
      (store: Local) => this.router.navigate(['/mainstore/', store.id]),
      error => alert('Error al crear nuevo local: ' + error)
    );
  }
}
