import {Component, OnInit} from '@angular/core';
import {Input} from '@angular/core';


import {
  ChangeDetectionStrategy,
  EventEmitter,
  Output
} from '@angular/core';


import {Menu} from './menu.model';
import {Router} from "@angular/router";


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],

  changeDetection: ChangeDetectionStrategy.OnPush
})

export class DashboardComponent implements OnInit {

  myMenu: Menu;
  user: string | null;

  constructor(private router: Router) {
    this.myMenu = [
      {
        title: 'Routes',
        icon: 'home',
        link: '/routes',
        color: '#ff7f0e',
      },
      {
        title: 'Transport',
        icon: 'router',
        color: '#ff7f0e',

        subMenu: [
          {
            title: 'Transports List',
            icon: 'money',
            link: './transports',
            color: '#ff7f0e',
          },
        ],
      },
    ];

    this.user = localStorage.getItem('username');
  }

  ngOnInit(): void {
  }

  // PageHeaderComponent
  @Input() icon?: string;


  // headerComponent
  @Output() menuToggled = new EventEmitter<boolean>();

  // constructor(private authService: AuthService) { }
  logout(): void {
    localStorage.clear();
    this.router.navigateByUrl('/login');
  }

  // layout
  opened = true;

  toggle(): void {
    this.opened = !this.opened;
  }
}


