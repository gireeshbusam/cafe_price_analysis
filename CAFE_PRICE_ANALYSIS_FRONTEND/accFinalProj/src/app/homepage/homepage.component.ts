import { Component, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { HomepageService } from './homepage.service';
import { categoryDTO } from './dto/categoryDTO';
import { cafeDTO } from './dto/cafeDTO';
import { cafeMenuDTO } from './dto/cafeMenuDTO';
import { cafeMenuListDTO } from './dto/cafeMenuListDTO';
import { counterDTO } from './dto/counterDTO';
import { selectedItemsDTO } from './dto/selectedItemsDTO';
import { ActivatedRoute, Params } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {
  modalRef?: BsModalRef;
  cafeType: string = "";
  nextCafeId: number;
  previousCafeId: number;
  menuItemsCL: cafeMenuListDTO[] = [];
  menuItemsRWC: cafeMenuListDTO[] = [];
  menuItemsNYB: cafeMenuListDTO[] = [];
  itemOptions: any[] = [{id: "0", value: "Burgers"},
  {id: "1", value: "Salads"},
  {id: "2", value: "Sandwiches"}];
  selectedItemType: number = 1;
  //fetchMenuList: any[] = [];
  categoryMst: categoryDTO[] = [];
  cafeMst: cafeDTO[] = [];
  cafeMenuList: cafeMenuDTO[] = [];
  cafeId: number;
  invertedIndexBody: any;
  counterList: counterDTO[] = [];
  selectedItemsList: selectedItemsDTO[] = [];
  empId: any;

  constructor(private modalService: BsModalService,
              private homepageService: HomepageService,
              private route: ActivatedRoute) {
                this.route.params.subscribe((params: Params) => {
                  this.empId = params['empId'];
                })
              }

  ngOnInit(): void {
    //Populating Menu
    this.fetchCafeMenu();
    this.fetchCategory();
    this.fetchCafes();

  }

  openModal(template: TemplateRef<any>, cafeId: number) {
    //this.cafeType = cafeId;
    this.cafeId = cafeId;
    
    this.selectedItemType = 1;

    this.selectCat(this.selectedItemType, this.cafeId);

    this.modalRef = this.modalService.show(
      template,
      Object.assign({}, { class: 'modal-lg' })
    );
  }

  closeModal() {
    //this.selectedItemType = 1;
    this.modalRef?.hide();
  }

  nextCafe(cafeId: number) {
    this.nextCafeId = cafeId;
    this.selectedItemType = 1;
    this.categoryMst = [];
    this.fetchCategory();
    if(this.nextCafeId == 2) {
      this.cafeId = this.nextCafeId;
      this.selectCat(this.selectedItemType, this.cafeId);
    } else if(this.nextCafeId == 3) {
      this.cafeId = this.nextCafeId;
      this.selectCat(this.selectedItemType, this.cafeId);
    }
  }

  previousCafe(cafeId: number) {
    this.previousCafeId = cafeId;
    this.selectedItemType = 1;
    this.categoryMst = [];
    this.fetchCategory();
    if(this.previousCafeId == 1) {
      this.cafeId = this.previousCafeId;
      this.selectCat(this.selectedItemType, this.cafeId);
    } else if(this.previousCafeId == 2) {
      this.cafeId = this.previousCafeId;
      this.selectCat(this.selectedItemType, this.cafeId);
    }
  }

  itemTypeSelect() {
    let ddl: HTMLSelectElement = document.getElementById("itemType") as HTMLSelectElement;
    let selectedValue = Number(ddl.options[ddl.selectedIndex].value);

    this.selectedItemType = selectedValue;

    //this.menuItemsCL = [];

    this.selectCat(this.selectedItemType, this.cafeId);
  }

  selectCat(selectedItemType: number, cafeId: number) {
    this.menuItemsCL = [];
    this.menuItemsRWC = [];
    this.menuItemsNYB = [];


    this.selectedItemType = selectedItemType;
    this.cafeId = cafeId;

    //this.menuItemsCL = [];

    if (this.cafeId == 1) {
      for (let i = 0; i < this.cafeMenuList.length; i++) {
        for (let j = 0; j < this.cafeMst.length; j++) {
          if (this.cafeMenuList[i].cafeId == this.cafeMst[j].cafeId && this.cafeMenuList[i].cafeId == 1) {
            if (this.cafeMenuList[i].categoryId == this.selectedItemType) {
              this.menuItemsCL.push({
                "id": this.cafeMenuList[i].id,
                "itemName": this.cafeMenuList[i].itemName,
                "itemDescription": this.cafeMenuList[i].itemDescription,
                "itemPrice": this.cafeMenuList[i].itemPrice,
                "categoryId": this.cafeMenuList[i].categoryId,
                "cafeId": this.cafeMenuList[i].cafeId,
                "checked": false
              });
            }
          }
        }
      }
    } else if (this.cafeId == 2) {
      for (let i = 0; i < this.cafeMenuList.length; i++) {
        for (let j = 0; j < this.cafeMst.length; j++) {
          if (this.cafeMenuList[i].cafeId == this.cafeMst[j].cafeId && this.cafeMenuList[i].cafeId == 2) {
            if (this.cafeMenuList[i].categoryId == this.selectedItemType) {
              this.menuItemsRWC.push({
                "id": this.cafeMenuList[i].id,
                "itemName": this.cafeMenuList[i].itemName,
                "itemDescription": this.cafeMenuList[i].itemDescription,
                "itemPrice": this.cafeMenuList[i].itemPrice,
                "categoryId": this.cafeMenuList[i].categoryId,
                "cafeId": this.cafeMenuList[i].cafeId,
                "checked": false
              });
            }
          }
        }
      }
    } else if (this.cafeId == 3) {
      for (let i = 0; i < this.cafeMenuList.length; i++) {
        for (let j = 0; j < this.cafeMst.length; j++) {
          if (this.cafeMenuList[i].cafeId == this.cafeMst[j].cafeId && this.cafeMenuList[i].cafeId == 3) {
            if (this.cafeMenuList[i].categoryId == this.selectedItemType) {
              this.menuItemsNYB.push({
                "id": this.cafeMenuList[i].id,
                "itemName": this.cafeMenuList[i].itemName,
                "itemDescription": this.cafeMenuList[i].itemDescription,
                "itemPrice": this.cafeMenuList[i].itemPrice,
                "categoryId": this.cafeMenuList[i].categoryId,
                "cafeId": this.cafeMenuList[i].cafeId,
                "checked": false
              });
            }
          }
        }
      }
    }
  }

  fetchCafeMenu() {
    this.homepageService.fetchCafeMenu().subscribe((data) => {
      this.cafeMenuList = data;
      //this.getInvertedIndex();
    });
  }

  fetchCategory() {
    this.homepageService.fetchCategory().subscribe((data) => {
      this.categoryMst = data;
    });
  }

  fetchCafes() {
    this.homepageService.fetchCafes().subscribe((data) => {
      this.cafeMst = data;
    })
  }

  getInvertedIndex() {
    this.invertedIndexBody = {
      "cafeMenuList": this.cafeMenuList,
      "key": 'AM Burger'
    }

    this.homepageService.getInvertedIndex(this.invertedIndexBody).subscribe((data) => {
      var result = JSON.stringify(data);
      result = JSON.parse(result);
      console.log(result);
    })
  }

  select() {
    //this.getCount();
    //console.log("CHECKED>>>>>>>>>>>>>" + checked);
    this.selectedItemsList = [];

    for(let i = 0; i < this.menuItemsCL.length; i++) {
      if(this.menuItemsCL[i].checked) {
        this.selectedItemsList.push({
          "id": 0,
          "selectionId": 0,
          "item_name": this.menuItemsCL[i].itemName,
          "item_description": this.menuItemsCL[i].itemDescription,
          "item_price": this.menuItemsCL[i].itemPrice,
          "category_id": this.menuItemsCL[i].categoryId,
          "cafe_id": this.menuItemsCL[i].cafeId,
          "userId": this.empId
        });
      }
    }

    for(let i = 0; i < this.menuItemsRWC.length; i++) {
      if(this.menuItemsRWC[i].checked) {
        this.selectedItemsList.push({
          "id": 0,
          "selectionId": 0,
          "item_name": this.menuItemsRWC[i].itemName,
          "item_description": this.menuItemsRWC[i].itemDescription,
          "item_price": this.menuItemsRWC[i].itemPrice,
          "category_id": this.menuItemsRWC[i].categoryId,
          "cafe_id": this.menuItemsRWC[i].cafeId,
          "userId": this.empId
        });
      }
    }

    for(let i = 0; i < this.menuItemsNYB.length; i++) {
      if(this.menuItemsNYB[i].checked) {
        this.selectedItemsList.push({
          "id": 0,
          "selectionId": 0,
          "item_name": this.menuItemsNYB[i].itemName,
          "item_description": this.menuItemsNYB[i].itemDescription,
          "item_price": this.menuItemsNYB[i].itemPrice,
          "category_id": this.menuItemsNYB[i].categoryId,
          "cafe_id": this.menuItemsNYB[i].cafeId,
          "userId": this.empId
        });
      }
    }

    this.homepageService.saveSelectedItems(this.selectedItemsList).subscribe((data: any) => {
      if(data["Success"] == 1000) {
        Swal.fire("Success", "Menu Item(s) selected Successfully.", 'success');
      } else if(data["Error"] == 9999) {
        Swal.fire("Error", "Error adding Menu Item(s).", 'error');
      }
    })

    console.log(this.menuItemsCL);
  }

  getCount() {
    this.homepageService.getCount().subscribe((data) => {
      this.counterList = data;
      console.log(this.counterList);
    })
  }
}
