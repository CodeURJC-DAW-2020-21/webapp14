import {Component, OnInit} from '@angular/core';
import { StoreService} from '../../service/store.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Local} from '../../model/local.model';
import {Comment} from '../../model/comment.model';
import { CommentService } from '../../service/comment.service';
import {main} from '@angular/compiler-cli/src/main';
import { LoginService } from '../../service/login.service';




@Component({
  selector: 'mainstore',
  templateUrl: './mainstore.component.html',
  styleUrls: ['./mainstore.component.css']
})
export class MainStoreComponent implements OnInit{
  title = 'Frontend-Angular';
  id: number;
  store: Local;
  comment : Comment;
  text:string;
  date:Date;
  new: boolean;
  constructor(private router: Router,private activatedRoute: ActivatedRoute,public storeService: StoreService,public commentService: CommentService ,public loginService: LoginService ) {
    let id = activatedRoute.snapshot.params['id'];
    this.id = id;
    this.comment = {name:' ',text: ' ',date:  null, image :' '};
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

  newCommentStore(){
    this.commentService.addComment(this.comment).subscribe(
      (comment:Comment)=> console.log("OK"),
      error => alert('Error al crear el nuevo evento: ' + error)
    )
  }
}



