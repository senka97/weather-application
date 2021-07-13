import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { WeatherService } from 'src/app/service/weather.service';
import { City } from 'src/app/type/city';
import { TemperatureResponse } from 'src/app/type/temperature-response';

const SEARCH_ICON = `
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
  <path fill-rule="evenodd" d="M14.53 15.59a8.25 8.25 0 111.06-1.06l5.69 5.69a.75.75 0 11-1.06 1.06l-5.69-5.69zM2.5 9.25a6.75 6.75 0 1111.74 4.547.746.746 0 00-.443.442A6.75 6.75 0 012.5 9.25z"></path>
</svg>
`;

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.scss']
})
export class WeatherComponent implements OnInit {

  public cities : City[] = [];
  public temperatures: TemperatureResponse[] = [];
  public averageTemperature!: number;
  public forecastDate!: string;
  public showTemperatures: boolean = false;
  public days = ['SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'];
  public months = ['JANUARY', 'FEBRUARY', 'MARCH', 'APRIL', 'MAY', 'JUN', 'JULY', 'AUGUST', 'SEPTEMBER', 'OCTOBER', 'NOVEMBER', 'DECEMBER'];

  constructor(private service: WeatherService,
              iconRegistry: MatIconRegistry, 
              sanitizer: DomSanitizer) { 

    iconRegistry.addSvgIconLiteral('search', sanitizer.bypassSecurityTrustHtml(SEARCH_ICON));
  }

  ngOnInit(): void {
    this.getCities();
  }

  getCities(){
    this.service.getAllCities().subscribe(
      citiesResponse => {
        this.cities = citiesResponse
      }
    )
  }

  getTemperature(name: string, numOfDays: number){
    this.service.getTemperaturesForCity(name, numOfDays).subscribe(
      temperaturesResponse => {
        this.temperatures = temperaturesResponse;
        this.averageTemperature = this.temperatures[0].city.averageTemperature5Days;

        for(let temp of this.temperatures){
            temp.day = this.days[new Date(temp.date).getDay()]
        }

        this.forecastDate = this.months[new Date(this.temperatures[0].date).getMonth()] + ' '
                            + new Date(this.temperatures[0].date).getDate() + '-' 
                            + new Date(this.temperatures[4].date).getDate() + ' '
                            + new Date(this.temperatures[0].date).getFullYear();

        this.showTemperatures = true;
      }
    )
  }

  search(event: any){
    this.showTemperatures = false;
    var inputValue = event.target.value.toLowerCase();
    var foundCity = false;
    if(this.cities.some(c => c.name.toLowerCase() === inputValue)){
      foundCity = true
      this.getTemperature(inputValue, 5);
    }
  }

}
