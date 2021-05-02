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
  public archivos:any = []




  user:Users;

  constructor(public loginService: LoginService, private userService: UserService, private router: Router) {

    this.user={name:"", dni:"", description:"", password:"" ,mail:"", imageFile: null }
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
        this.subirArchivo(user);

       this.loginService.logIn(name, password)
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  capturarFile(event): any {
    const archivoCapturado = event.target.files[0];
    this.archivos.push(archivoCapturado)
    console.log(this.archivos)

  }

  subirArchivo(user:Users):any {
    try{
      const formularioDeDatos = new FormData();
      this.archivos.forEach(archivo =>{
        console.log(archivo);
        formularioDeDatos.append('imageFile',archivo)
      })
      this.userService.setUserImage(user,formularioDeDatos).subscribe(res => {
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
