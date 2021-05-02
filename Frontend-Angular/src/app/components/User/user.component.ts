import { Component } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { UserService } from '../../service/user.service';


@Component({
  selector: 'users',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UsersComponent {
  title = 'Frontend-Angular';
  constructor(public loginService: LoginService,public userService: UserService) {



   }


}
