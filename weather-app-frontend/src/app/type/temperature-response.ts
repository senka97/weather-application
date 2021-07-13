import { City } from "./city";

export interface TemperatureResponse {
  city : City
  averageTemp: number
  date: Date
  day: string
}