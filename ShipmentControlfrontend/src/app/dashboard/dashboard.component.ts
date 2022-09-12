import {Component, OnInit} from '@angular/core';
import {Input} from '@angular/core';


import {
  ChangeDetectionStrategy,
  EventEmitter,
  Output
} from '@angular/core';


import {Menu} from './menu.model';
import {Router} from "@angular/router";
import decode from "jwt-decode";


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],

  changeDetection: ChangeDetectionStrategy.OnPush
})

export class DashboardComponent implements OnInit {

  myMenu: Menu;
  user: string | null;
  // @ts-ignore
  userRole = decode(localStorage.getItem('token')).sub;

  constructor(private router: Router) {
    if (this.userRole === '[ROLE_SHIPMENT_COMPANY]') {

      this.myMenu = [
        {
          title: 'Cargo Type',
          icon: 'category',
          link: './cargoType',
          color: '#ff7f0e',
        },
        {

          title: 'Transports',
          icon: 'directions_car',
          link: './transports',
          color: '#ff7f0e',
        },
        {
          title: 'Routes',
          icon: 'directions',
          link: './route',
          color: '#ff7f0e',
        },
        {
          title: 'Booking Requests',
          icon: 'poll',
          link: './booking-requests',
          color: '#ff7f0e',
        },
        {
          title: 'Cargo',
          icon: 'add_shopping_cart',
          link: './cargo',
          color: '#ff7f0e',
        },

      ];
    } else if (this.userRole === '[ROLE_GOODS_COMPANY]') {
      this.myMenu = [
        {
          title: 'Routes',
          icon: 'directions',
          link: './route',
          color: '#ff7f0e',
        },
        {
          title: 'Cargo',
          icon: 'add_shopping_cart',
          link: './cargo',
          color: '#ff7f0e',
        },
      ];
    }


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


