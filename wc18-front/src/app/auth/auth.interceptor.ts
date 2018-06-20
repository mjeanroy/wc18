/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Logger } from '../log';
import { AuthService } from './auth.service';

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
