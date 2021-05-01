import {Component, OnInit} from '@angular/core';

import { StoreService} from '../../service/store.service';
import {ActivatedRoute, Router} from '@angular/router';

import {Local} from '../../model/local.model';
import {Comment} from '../../model/comment.model';
import { CommentService } from '../../service/comment.service';
import {main} from '@angular/compiler-cli/src/main';
import { LoginService } from '../../service/login.service';
import { UserService } from '../../service/user.service';
import { Users } from 'src/app/model/user.model';


@Component({
  selector: 'mainstore',
  templateUrl: './mainstore.component.html',
  styleUrls: ['./mainstore.component.css']
})
export class MainStoreComponent implements OnInit{
  title = 'Frontend-Angular';
  id: number;
  store: Local;
  user:Users;
  comment : Comment;
  text:string;
  date:Date;
  new: boolean;
  constructor(private router: Router,private activatedRoute: ActivatedRoute,public storeService: StoreService,public commentService: CommentService ,public loginService: LoginService,public userService: UserService ) {

    let id = activatedRoute.snapshot.params['id'];
    this.id = id;
    this.comment = {name:' ',text: ' ',date:  null, image :' '};
    this.user = loginService.currentUser();
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

  newCommentStore(){

    this.commentService.addComment(this.comment).subscribe(
      (comentario:Comment)=> {console.log(comentario)
        this.store.comment.push(comentario)
        this.storeService.addStore(this.store).subscribe(
          (tienda:Local) => {console.log(tienda)
            this.user.comment.push(comentario);
            this.user.commentPlaces.push(tienda.name);
            this.userService.addUser(this.user).subscribe(
              (usuario:Users) => console.log(usuario),
               error => alert('Error al actualizar el usuario : ' + error)
        ); 
          },
          error => alert('Error al actualizar la tienda : ' + error)
        ); 
      },
      error => alert('Error al crear el comentario: ' + error)
    );
  
  }

}



