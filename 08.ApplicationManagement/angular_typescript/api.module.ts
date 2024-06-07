import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { Configuration } from './configuration';
import { HttpClient } from '@angular/common/http';


import { APIGatewayLoginServiceAuthInfoService } from './api/aPIGatewayLoginServiceAuthInfo.service';
import { APIGatewayLoginServiceAuthenticateService } from './api/aPIGatewayLoginServiceAuthenticate.service';
import { APIGatewayLoginServiceCreateInviteusersService } from './api/aPIGatewayLoginServiceCreateInviteusers.service';
import { APIGatewayLoginServiceGatewayService } from './api/aPIGatewayLoginServiceGateway.service';
import { APIGatewayLoginServiceGetAuthoritiesService } from './api/aPIGatewayLoginServiceGetAuthorities.service';
import { APIGatewayLoginServiceGetInviteusersService } from './api/aPIGatewayLoginServiceGetInviteusers.service';
import { APIGatewayLoginServiceGetUsersService } from './api/aPIGatewayLoginServiceGetUsers.service';
import { APIGatewayLoginServiceLoginUserService } from './api/aPIGatewayLoginServiceLoginUser.service';
import { APIGatewayManagementServiceAdminApproveAdvsService } from './api/aPIGatewayManagementServiceAdminApproveAdvs.service';
import { APIGatewayManagementServiceAdminCreateAdvsForUserService } from './api/aPIGatewayManagementServiceAdminCreateAdvsForUser.service';
import { APIGatewayManagementServiceAdminDeleteAdvsByIdService } from './api/aPIGatewayManagementServiceAdminDeleteAdvsById.service';
import { APIGatewayManagementServiceAdminGetAdvsByIdService } from './api/aPIGatewayManagementServiceAdminGetAdvsById.service';
import { APIGatewayManagementServiceAdminGetAllAdvsService } from './api/aPIGatewayManagementServiceAdminGetAllAdvs.service';
import { APIGatewayManagementServiceAdminRejectAdvsService } from './api/aPIGatewayManagementServiceAdminRejectAdvs.service';
import { APIGatewayManagementServiceAdminUpdateAdvsByIdService } from './api/aPIGatewayManagementServiceAdminUpdateAdvsById.service';
import { APIGatewayManagementServiceUserGetAdvService } from './api/aPIGatewayManagementServiceUserGetAdv.service';
import { APIGatewayManagementServiceUserGetAllAdvsService } from './api/aPIGatewayManagementServiceUserGetAllAdvs.service';
import { APIGatewayUserServiceLoginAdminService } from './api/aPIGatewayUserServiceLoginAdmin.service';
import { APIGatewayUserServiceSignUpAdminService } from './api/aPIGatewayUserServiceSignUpAdmin.service';
import { APIGatewayUserServiceUserInfoService } from './api/aPIGatewayUserServiceUserInfo.service';

@NgModule({
  imports:      [],
  declarations: [],
  exports:      [],
  providers: [
    APIGatewayLoginServiceAuthInfoService,
    APIGatewayLoginServiceAuthenticateService,
    APIGatewayLoginServiceCreateInviteusersService,
    APIGatewayLoginServiceGatewayService,
    APIGatewayLoginServiceGetAuthoritiesService,
    APIGatewayLoginServiceGetInviteusersService,
    APIGatewayLoginServiceGetUsersService,
    APIGatewayLoginServiceLoginUserService,
    APIGatewayManagementServiceAdminApproveAdvsService,
    APIGatewayManagementServiceAdminCreateAdvsForUserService,
    APIGatewayManagementServiceAdminDeleteAdvsByIdService,
    APIGatewayManagementServiceAdminGetAdvsByIdService,
    APIGatewayManagementServiceAdminGetAllAdvsService,
    APIGatewayManagementServiceAdminRejectAdvsService,
    APIGatewayManagementServiceAdminUpdateAdvsByIdService,
    APIGatewayManagementServiceUserGetAdvService,
    APIGatewayManagementServiceUserGetAllAdvsService,
    APIGatewayUserServiceLoginAdminService,
    APIGatewayUserServiceSignUpAdminService,
    APIGatewayUserServiceUserInfoService ]
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): ModuleWithProviders<ApiModule> {
        return {
            ngModule: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        };
    }

    constructor( @Optional() @SkipSelf() parentModule: ApiModule,
                 @Optional() http: HttpClient) {
        if (parentModule) {
            throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
        }
        if (!http) {
            throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
            'See also https://github.com/angular/angular/issues/20575');
        }
    }
}
