import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root-layout',
  templateUrl: './root-layout.component.html',
  styleUrls: ['./root-layout.component.scss']
})
export class RootLayoutComponent implements OnInit {
  private role: any = localStorage.getItem("role")
  constructor() { }

  ngOnInit(): void {
  }

}
