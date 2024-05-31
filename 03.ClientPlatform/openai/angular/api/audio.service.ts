/**
 * OpenAI API
 * The OpenAI REST API. Please see https://platform.openai.com/docs/api-reference for more details.
 *
 * OpenAPI spec version: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *//* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional }                      from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams,
         HttpResponse, HttpEvent }                           from '@angular/common/http';
import { CustomHttpUrlEncodingCodec }                        from '../encoder';

import { Observable }                                        from 'rxjs';

import { CreateSpeechRequest } from '../model/createSpeechRequest';
import { InlineResponse200 } from '../model/inlineResponse200';
import { InlineResponse2001 } from '../model/inlineResponse2001';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class AudioService {

    protected basePath = 'https://api.openai.com/v1';
    public defaultHeaders = new HttpHeaders();
    public configuration = new Configuration();

    constructor(protected httpClient: HttpClient, @Optional()@Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
        if (basePath) {
            this.basePath = basePath;
        }
        if (configuration) {
            this.configuration = configuration;
            this.basePath = basePath || configuration.basePath || this.basePath;
        }
    }

    /**
     * @param consumes string[] mime-types
     * @return true: consumes contains 'multipart/form-data', false: otherwise
     */
    private canConsumeForm(consumes: string[]): boolean {
        const form = 'multipart/form-data';
        for (const consume of consumes) {
            if (form === consume) {
                return true;
            }
        }
        return false;
    }


    /**
     * Generates audio from the input text.
     * 
     * @param body 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public createSpeech(body: CreateSpeechRequest, observe?: 'body', reportProgress?: boolean): Observable<Blob>;
    public createSpeech(body: CreateSpeechRequest, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Blob>>;
    public createSpeech(body: CreateSpeechRequest, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Blob>>;
    public createSpeech(body: CreateSpeechRequest, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling createSpeech.');
        }

        let headers = this.defaultHeaders;

        // authentication (ApiKeyAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/octet-stream'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<Blob>('post',`${this.basePath}/audio/speech`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Transcribes audio into the input language.
     * 
     * @param file 
     * @param model 
     * @param language 
     * @param prompt 
     * @param responseFormat 
     * @param temperature 
     * @param timestampGranularities 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public createTranscriptionForm(file: Blob, model: string, language: string, prompt: string, responseFormat: string, temperature: number, timestampGranularities: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<InlineResponse200>;
    public createTranscriptionForm(file: Blob, model: string, language: string, prompt: string, responseFormat: string, temperature: number, timestampGranularities: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<InlineResponse200>>;
    public createTranscriptionForm(file: Blob, model: string, language: string, prompt: string, responseFormat: string, temperature: number, timestampGranularities: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<InlineResponse200>>;
    public createTranscriptionForm(file: Blob, model: string, language: string, prompt: string, responseFormat: string, temperature: number, timestampGranularities: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (file === null || file === undefined) {
            throw new Error('Required parameter file was null or undefined when calling createTranscription.');
        }

        if (model === null || model === undefined) {
            throw new Error('Required parameter model was null or undefined when calling createTranscription.');
        }

        if (language === null || language === undefined) {
            throw new Error('Required parameter language was null or undefined when calling createTranscription.');
        }

        if (prompt === null || prompt === undefined) {
            throw new Error('Required parameter prompt was null or undefined when calling createTranscription.');
        }

        if (responseFormat === null || responseFormat === undefined) {
            throw new Error('Required parameter responseFormat was null or undefined when calling createTranscription.');
        }

        if (temperature === null || temperature === undefined) {
            throw new Error('Required parameter temperature was null or undefined when calling createTranscription.');
        }

        if (timestampGranularities === null || timestampGranularities === undefined) {
            throw new Error('Required parameter timestampGranularities was null or undefined when calling createTranscription.');
        }

        let headers = this.defaultHeaders;

        // authentication (ApiKeyAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'multipart/form-data'
        ];

        const canConsumeForm = this.canConsumeForm(consumes);

        let formParams: { append(param: string, value: any): void; };
        let useForm = false;
        let convertFormParamsToString = false;
        // use FormData to transmit files using content-type "multipart/form-data"
        // see https://stackoverflow.com/questions/4007969/application-x-www-form-urlencoded-or-multipart-form-data
        useForm = canConsumeForm;
        if (useForm) {
            formParams = new FormData();
        } else {
            formParams = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        }

        if (file !== undefined) {
            formParams = formParams.append('file', <any>file) as any || formParams;
        }
        if (model !== undefined) {
            formParams = formParams.append('model', <any>model) as any || formParams;
        }
        if (language !== undefined) {
            formParams = formParams.append('language', <any>language) as any || formParams;
        }
        if (prompt !== undefined) {
            formParams = formParams.append('prompt', <any>prompt) as any || formParams;
        }
        if (responseFormat !== undefined) {
            formParams = formParams.append('response_format', <any>responseFormat) as any || formParams;
        }
        if (temperature !== undefined) {
            formParams = formParams.append('temperature', <any>temperature) as any || formParams;
        }
        if (timestampGranularities) {
            timestampGranularities.forEach((element) => {
                formParams = formParams.append('timestamp_granularities[]', <any>element) as any || formParams;
            })
        }

        return this.httpClient.request<InlineResponse200>('post',`${this.basePath}/audio/transcriptions`,
            {
                body: convertFormParamsToString ? formParams.toString() : formParams,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Translates audio into English.
     * 
     * @param file 
     * @param model 
     * @param prompt 
     * @param responseFormat 
     * @param temperature 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public createTranslationForm(file: Blob, model: string, prompt: string, responseFormat: string, temperature: number, observe?: 'body', reportProgress?: boolean): Observable<InlineResponse2001>;
    public createTranslationForm(file: Blob, model: string, prompt: string, responseFormat: string, temperature: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<InlineResponse2001>>;
    public createTranslationForm(file: Blob, model: string, prompt: string, responseFormat: string, temperature: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<InlineResponse2001>>;
    public createTranslationForm(file: Blob, model: string, prompt: string, responseFormat: string, temperature: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (file === null || file === undefined) {
            throw new Error('Required parameter file was null or undefined when calling createTranslation.');
        }

        if (model === null || model === undefined) {
            throw new Error('Required parameter model was null or undefined when calling createTranslation.');
        }

        if (prompt === null || prompt === undefined) {
            throw new Error('Required parameter prompt was null or undefined when calling createTranslation.');
        }

        if (responseFormat === null || responseFormat === undefined) {
            throw new Error('Required parameter responseFormat was null or undefined when calling createTranslation.');
        }

        if (temperature === null || temperature === undefined) {
            throw new Error('Required parameter temperature was null or undefined when calling createTranslation.');
        }

        let headers = this.defaultHeaders;

        // authentication (ApiKeyAuth) required
        if (this.configuration.accessToken) {
            const accessToken = typeof this.configuration.accessToken === 'function'
                ? this.configuration.accessToken()
                : this.configuration.accessToken;
            headers = headers.set('Authorization', 'Bearer ' + accessToken);
        }
        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'multipart/form-data'
        ];

        const canConsumeForm = this.canConsumeForm(consumes);

        let formParams: { append(param: string, value: any): void; };
        let useForm = false;
        let convertFormParamsToString = false;
        // use FormData to transmit files using content-type "multipart/form-data"
        // see https://stackoverflow.com/questions/4007969/application-x-www-form-urlencoded-or-multipart-form-data
        useForm = canConsumeForm;
        if (useForm) {
            formParams = new FormData();
        } else {
            formParams = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        }

        if (file !== undefined) {
            formParams = formParams.append('file', <any>file) as any || formParams;
        }
        if (model !== undefined) {
            formParams = formParams.append('model', <any>model) as any || formParams;
        }
        if (prompt !== undefined) {
            formParams = formParams.append('prompt', <any>prompt) as any || formParams;
        }
        if (responseFormat !== undefined) {
            formParams = formParams.append('response_format', <any>responseFormat) as any || formParams;
        }
        if (temperature !== undefined) {
            formParams = formParams.append('temperature', <any>temperature) as any || formParams;
        }

        return this.httpClient.request<InlineResponse2001>('post',`${this.basePath}/audio/translations`,
            {
                body: convertFormParamsToString ? formParams.toString() : formParams,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
