import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomepageService {

  private fetchCafeMenuUrl = '/rest/api/getMenu';
  private fetchCategoryUrl = '/rest/api/getCategory';
  private fetchCafesUrl = '/rest/api/getCafes';
  private getInvertedIndexUrl = '/rest/api/invertedIndex?searchKey=';
  private getCountUrl = '/rest/api/getCount';
  private saveSelectedItemsUrl = '/rest/api/saveSelectedItems?saveSelectedItemsList=';

  constructor(private http: HttpClient) { }

  fetchCafeMenu() {
    return this.http.get<any[]>(this.fetchCafeMenuUrl);
  }

  fetchCategory() {
    return this.http.get<any[]>(this.fetchCategoryUrl);
  }

  fetchCafes() {
    return this.http.get<any[]>(this.fetchCafesUrl);
  }

  getInvertedIndex(searchKey: any) {
    return this.http.post<any[]>(this.getInvertedIndexUrl, searchKey);
  }

  getCount() {
    return this.http.get<any[]>(this.getCountUrl);
  }

  saveSelectedItems(saveSelectedItemsList: any) {
    return this.http.post<any[]>(this.saveSelectedItemsUrl, saveSelectedItemsList);
  }
}
