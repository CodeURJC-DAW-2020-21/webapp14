import { Component } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { UsersComponent} from '../../components/User/user.component';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  title = 'Frontend-Angular';
  constructor(public loginService: LoginService, private userService: UserService, private router: Router) { }

  logIn(event: any, user: string, pass: string) {

    event.preventDefault();

    this.loginService.logIn(user, pass);
  }

  logOut() {
    this.loginService.logOut();
  }

  newUser(name:string, dni:string, description:string, password:string, mail:string ){
    this.userService.RegisterUser(name,dni,description,password,mail).subscribe(
      user => {
        console.log(user);
        
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )


  }
}
