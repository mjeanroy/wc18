/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import { Logger } from '../log';
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {

  private readonly _logger: Logger;
  private readonly _authService: AuthService;
  private readonly _headerName: string;

  constructor(authService: AuthService, logger: Logger) {
    this._headerName = 'X-Auth-Token';
    this._logger = logger;
    this._authService = authService;
  }

  /**
   * Intercept request and add authentication information with
   * appropriate token.
   *
   * @param {HttpRequest<any>} req The intercepted request.
   * @param {HttpHandler} next The request handler chain.
   * @returns {Observable<HttpEvent<any>>} The request response.
   */
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this._logger.debug(`Intercepting request: [${req.method}] ${req.url}`);

    if (!this._authService.isLogged()) {
      return next.handle(req);
    }

    const token = this._authService.token();
    const cloned = req.clone({
      headers: req.headers.set(this._headerName, token),
    });

    return next.handle(cloned);
  }
}
