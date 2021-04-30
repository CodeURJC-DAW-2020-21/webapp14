import {Component, OnInit} from '@angular/core';
import { StoreService} from '../../service/store.service';
import {ActivatedRoute} from '@angular/router';
import {Local} from '../../model/local.model';
import {main} from '@angular/compiler-cli/src/main';
import { LoginService } from '../../service/login.service';


export const DEFAULT_HEIGHT = '500px';
export const DEFAULT_WIDTH = '500px';

export const DEFAULT_LAT = -34.603490361131385;
export const DEFAULT_LON = -58.382037891217465;

@Component({
  selector: 'mainstore',
  templateUrl: './mainstore.component.html',
  styleUrls: ['./mainstore.component.css']
})
export class MainStoreComponent implements OnInit{
  title = 'Frontend-Angular';
  id: number;
  store: Local;
  constructor(public loginService: LoginService, public storeService: StoreService, private activatedRoute: ActivatedRoute) {
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
}

const cssUnitsPattern = /([A-Za-z%]+)$/;

function coerceCssPixelValue(value: any): string {
  if (value == null) {
    return '';
  }

  return cssUnitsPattern.test(value) ? value : `${value}px`;
}
