import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PriceanalysisService {

  private getInvertedIndexUrl = '/rest/api/invertedIndex?searchKey=';
  private fetchSelectedItemsUrl = '/rest/api/fetchSelectedItems?userId=';
  private priceAnalysisUrl = '/rest/api/priceAnalysis?userId=';
  private deleteSelectedItemsUrl = '/rest/api/deleteItems?userId=';
  private selectMoreItemsUrl = '/rest/api/addMoreItems';

  constructor(private http: HttpClient) { }

  getInvertedIndex(searchKey: any) {
    return this.http.post<any[]>(this.getInvertedIndexUrl, searchKey);
  }

  fetchSelectedItems(userId: number, catId: number) {
    return this.http.get<any[]>(this.fetchSelectedItemsUrl + userId + "&catId=" + catId);
  }

  priceAnalysis(catId: number, userId: number) {
    return this.http.get<any[]>(this.priceAnalysisUrl + userId + "&catId=" + catId);
  }

  deleteSelectedItems(deleteSelectedList: any) {
    return this.http.post<any[]>(this.deleteSelectedItemsUrl, deleteSelectedList);
  }

  selectMoreItems(addMoreItemsBody: any) {
    return this.http.post<any[]>(this.selectMoreItemsUrl, addMoreItemsBody);
  }
}
