import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { Configuration } from './configuration';
import { HttpClient } from '@angular/common/http';


import { AssistantsService } from './api/assistants.service';
import { AudioService } from './api/audio.service';
import { BatchService } from './api/batch.service';
import { ChatService } from './api/chat.service';
import { CompletionsService } from './api/completions.service';
import { EmbeddingsService } from './api/embeddings.service';
import { FilesService } from './api/files.service';
import { FineTuningService } from './api/fineTuning.service';
import { ImagesService } from './api/images.service';
import { ModelsService } from './api/models.service';
import { ModerationsService } from './api/moderations.service';
import { VectorStoresService } from './api/vectorStores.service';

@NgModule({
  imports:      [],
  declarations: [],
  exports:      [],
  providers: [
    AssistantsService,
    AudioService,
    BatchService,
    ChatService,
    CompletionsService,
    EmbeddingsService,
    FilesService,
    FineTuningService,
    ImagesService,
    ModelsService,
    ModerationsService,
    VectorStoresService ]
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
