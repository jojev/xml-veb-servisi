import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class InterceptorInterceptor implements HttpInterceptor {
  intercept(
      req: HttpRequest<any>,
      next: HttpHandler
    ): Observable<HttpEvent<any>> {
      const item = localStorage.getItem("user");

      if (item) {
        const decodedItem = JSON.parse(item);
        console.log(decodedItem)
        console.log(decodedItem.usertokenstate)
        const cloned = req.clone({
          headers: req.headers.set("Authorization", 'Bearer ' + decodedItem.usertokenstate.accessToken[0]),
        });
        return next.handle(cloned);
      } else {
        return next.handle(req);
      }
    }
}
