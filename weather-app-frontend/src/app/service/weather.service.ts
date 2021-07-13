import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { City } from '../type/city';
import { TemperatureResponse } from '../type/temperature-response';


@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  cities_route: String = "/cities";
  temperatures_route: String = "/temperatures"

  constructor(private http: HttpClient) { 
  }

  getAllCities(): Observable<City[]> {
    return this.http.get<City[]>(environment.apiUrl + this.cities_route)
  }

  getTemperaturesForCity(name: string, numOfDays: number) : Observable<TemperatureResponse[]> {
    return this.http.get<TemperatureResponse[]>(environment.apiUrl + this.temperatures_route + 
                                        "/city?numOfDays=" + numOfDays + "&name=" + name)
  }

}