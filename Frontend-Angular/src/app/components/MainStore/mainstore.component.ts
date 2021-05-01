import {Component, OnInit} from '@angular/core';
import {StoreService} from '../../service/store.service';
import {Router, ActivatedRoute} from '@angular/router';
import {Local} from '../../model/local.model';
import {main} from '@angular/compiler-cli/src/main';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'mainstore',
  templateUrl: './mainstore.component.html',
  styleUrls: ['./mainstore.component.css']
})
export class MainStoreComponent{// implements OnInit{
  //title = 'Frontend-Angular';
  id: number;
  store: Local;
  constructor(private router: Router, public loginService: LoginService, public storeService: StoreService, private activatedRoute: ActivatedRoute) {
    let id = activatedRoute.snapshot.params['id'];
    this.id = id;
  }

  ngOnInit() {
    this.storeService.getStore(this.id).subscribe(
      mainstore => {
        this.store = mainstore;
        console.log(this.store);
      },
      error => console.log("error")
    );
  }

  deleteStore(id:number){
    this.storeService.removeStore(id).subscribe(
      _ => this.router.navigate(['/stores']),
      error => console.log(error)
    );
}
}



