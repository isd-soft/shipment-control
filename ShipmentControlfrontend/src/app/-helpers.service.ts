/* import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HelpersService {

  constructor() { }
} */


import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCheckboxModule} from "@angular/material/checkbox";
import { MatSelectModule} from "@angular/material/select";
import { ReactiveFormsModule } from '@angular/forms';

/*import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HTTP_INTERCEPTORS,
  HttpResponse
} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {error} from "@angular/compiler-cli/src/transformers/util";
@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      headers: req.headers.set('login', ''),
      withCredentials: true,
    });
    return next.handle(req).pipe(
      tap(
        event => {
              if (event instanceof HttpResponse) {
                console.log("api call success : ", event);
              }
            },
        error => {
             if (event instanceof HttpResponse) {
               console.log("api call error : ", event);
             }
    }
      )
    );
  }
}
export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true },
];
*/
