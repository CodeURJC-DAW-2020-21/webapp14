import { Component } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { UsersComponent} from '../../components/User/user.component';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';
import { Users } from 'src/app/model/user.model';


@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  title = 'Frontend-Angular';
  public imagefile :any = []




  user:Users;

  constructor(public loginService: LoginService, private userService: UserService, private router: Router) {

    this.user={name:"", dni:"", description:"", password:"" ,mail:"", imageFile: null, eventSuscribe:[] }
  }


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
        this.UploadFile(user);

       this.loginService.logIn(name, password)
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  captureFile(event): any {
    const capturedfile = event.target.files[0];
    this.imagefile.push(capturedfile)
    console.log(this.imagefile)

  }

  UploadFile(user:Users):any {
    try{
      const  newformdata = new FormData();
      this.imagefile.forEach(imgfile =>{
        console.log(imgfile);
        newformdata.append('imageFile',imgfile)
      })
      this.userService.setUserImage(user, newformdata).subscribe(res => {
        console.log('Respuesta del servidor',res);
        })
      } catch (e) {
        console.log('ERROR',e);
      }
  }





  private afterUploadImage(user: Users){
    this.router.navigate(['/users/', user.id]);
  }
}
