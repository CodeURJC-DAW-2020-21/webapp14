import {Component, OnInit} from '@angular/core';
import { StoreService } from '../../service/store.service';
import { LoginService } from '../../service/login.service';
import { Local } from '../../model/local.model';
import { Router} from '@angular/router';

@Component({
  selector: 'store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit{
  stores: Local[]=[];

  constructor(private router: Router, private storeService: StoreService, public loginService: LoginService) {
  }

  ngOnInit() {
    this.storeService.getStores().subscribe(
      storearray =>{
        storearray.map(store =>this.stores.push(store))
        console.log(this.stores);
      },
      error => console.log("error")
    );
  }

  newStore() {
    this.router.navigate(['/storeform']);
  }

  navigatetoStore(id: number){
   this.router.navigate(['mainstore/' + id]);
  }
}
