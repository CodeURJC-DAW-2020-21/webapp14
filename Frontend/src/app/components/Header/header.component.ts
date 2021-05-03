import { Component } from '@angular/core';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  title = 'Frontend-Angular';
  constructor(public loginService: LoginService) { }
}
