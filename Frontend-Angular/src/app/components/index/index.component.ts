import { Component } from '@angular/core';

@Component({
  selector: 'index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {
  title = 'Frontend-Angular';

  ngAfterViewInit(): void {
    (<any>window).twttr.widgets.load();
}

}
