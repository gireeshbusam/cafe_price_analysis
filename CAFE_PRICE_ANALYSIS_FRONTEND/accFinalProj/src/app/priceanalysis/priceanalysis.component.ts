import { Component, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { HomepageService } from '../homepage/homepage.service';
import { cafeMenuDTO } from '../homepage/dto/cafeMenuDTO';
import { PriceanalysisService } from './priceanalysis.service';
import { invertedIndexDTO } from './dto/invertedIndexDTO';
import { categoryDTO } from '../homepage/dto/categoryDTO';
import { cafeName } from './dto/cafeName';
import { selectedItemsDTO } from '../homepage/dto/selectedItemsDTO';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-priceanalysis',
  templateUrl: './priceanalysis.component.html',
  styleUrls: ['./priceanalysis.component.css']
})
export class PriceanalysisComponent {
  modalRef?: BsModalRef;
  cafeType: string = "";
  nextCafeType: string = "";
  previousCafeType: string = "";
  priceAnalysedList: any[] = [];
  itemOptions: any[] = [{id: "0", value: "Burgers"},
  {id: "1", value: "Salads"},
  {id: "2", value: "Sandwiches"}];
  selectedItemType: string = "B";
  invertedIndexBody: any;
  cafeMenuList: cafeMenuDTO[] = [];
  searchKey: any;
  files: invertedIndexDTO[] = [];
  categoryMst: categoryDTO[] = [];
  cafeNameList: cafeName[] = [];
  selectedItemsList: selectedItemsDTO[] = [];
  catId: number = 1;
  userId: number;
  priceAnalysisList: selectedItemsDTO[] = [];
  //categoryMst: categoryDTO[] = [];
  responseMessage: any;
  responseCode: any;
  page = 1;
  addMoreItemsBody: any;

  constructor(private modalService: BsModalService,
    private homepageService: HomepageService,
    private priceanalysisService: PriceanalysisService) {}

  ngOnInit(): void {

    //Populating Menu
    this.fetchCafeMenu();
    this.fetchSelectedItems(1);
    this.fetchCategory();
    
    this.priceAnalysedList = [
      {
        "burgers": [
          { "title": "The Classic", "price": "$8 / $11" },
          { "title": "Havana (Breakfast feel Burger)", "price": "$11" },
          { "title": "Moonstruck Burger", "price": "$15.95" }
        ],
        "salads": [
          { "title": "Nacho Cheese Ravioli", "price": "$6.50" },
          { "title": "Caesar", "price": "$9.5" },
        ],
        "sandwiches": [
          { "title": "Grilled Chicken", "price": "$8.5 / $11.5" },
          { "title": "Astro Grilled Cheese", "price": "$10.95" },
          { "title": "Galactic Sandwich", "price": "$14.95" }
        ]
      }
    ];
  }

  handlePageChange(event: any) {
    this.page = event;
  }

  itemTypeSelect() {
    this.page = 1;
    let ddl:HTMLSelectElement = document.getElementById("itemType") as HTMLSelectElement;
    let selectedValue = ddl.options[ddl.selectedIndex].value;
    this.catId = Number(selectedValue);
    if(selectedValue == '1') {
      this.fetchSelectedItems(this.catId);
    } else if(selectedValue == '2') {
      this.fetchSelectedItems(this.catId);
    } else if(selectedValue == '3') {
      this.fetchSelectedItems(this.catId);
    } else if(selectedValue == '4') {
      this.fetchSelectedItems(this.catId);
    }
  }

  openModal(template: TemplateRef<any>) {
    this.cafeNameList = [];
    this.searchKey = '';
    this.modalRef = this.modalService.show(
      template,
      Object.assign({}, { class: 'modal-lg' })
    );
  }

  fetchCafeMenu() {
    this.homepageService.fetchCafeMenu().subscribe((data) => {
      this.cafeMenuList = data;
      //this.getInvertedIndex();
    });
  }

  getInvertedIndex() {
    this.invertedIndexBody = {
      "cafeMenuList": this.cafeMenuList,
      "key": this.searchKey
    }

    this.priceanalysisService.getInvertedIndex(this.invertedIndexBody).subscribe((data: any) => {
      this.responseMessage = data["responseMessage"];
      this.responseCode = data["responseCode"];

      if(this.responseCode === 1000) {
        this.files = data["files"];
      } else if(this.responseCode === 2000) {
        Swal.fire(this.responseMessage);
      } else {
        Swal.fire("Warning", this.responseMessage, "warning");
      }

    },
      (error) => {
        console.log('Error: ', error);
      },
      () => {
        //console.log(this.files[0].itemName.split(","));
        this.getCafeName();
      }
    );
  }

  getCafeName() {
    
    for(let i = 0; i < this.files.length; i++) {
      this.cafeNameList.push({
        'cafe': this.files[i].itemName,
        'itemName': this.searchKey,
        'itemId': this.files[i].itemId
      });
    }
    console.log(this.cafeNameList);
  }

  fetchCategory() {
    this.homepageService.fetchCategory().subscribe((data) => {
      this.categoryMst = data;
    });
  }

  fetchSelectedItems(catId: number) {
    this.userId = Number(localStorage.getItem("userId"));
    this.selectedItemsList = [];
    this.priceanalysisService.fetchSelectedItems(this.userId, catId).subscribe((data) => {
      this.selectedItemsList = data;
      console.log("SELECTED ITEMS>>>>>>>>>>" + this.selectedItemsList);
    })
  }

  priceAnalysis() {
    this.priceAnalysisList = [];
    this.userId = Number(localStorage.getItem("userId"));
    this.priceanalysisService.priceAnalysis(this.catId, this.userId).subscribe((data) => {
      this.priceAnalysisList = data;
    });
  }

  openPriceAnalysis(priceAnalysisPopup: TemplateRef<any>) {
    this.priceAnalysisList = [];
    this.modalRef = this.modalService.show(
      priceAnalysisPopup,
      Object.assign({}, { class: 'modal-lg' })
    );
  }

  deleteSelectedItems(deleteItems: selectedItemsDTO) {
    this.priceanalysisService.deleteSelectedItems(deleteItems).subscribe((data: any) => {
      this.responseMessage = data["responseMessage"];
      this.responseCode = data["responseCode"];

      if(this.responseCode == 1000) {
        Swal.fire("Success", "Item deleted successfully.", "success");
        this.fetchSelectedItems(this.catId);
      }
    });
  }

  selectMoreItems(cafeList: cafeName) {
    this.userId = Number(localStorage.getItem("userId"));
    
    this.addMoreItemsBody = {
      "itemId": cafeList.itemId,
      "userId": this.userId
    }

    this.priceanalysisService.selectMoreItems(this.addMoreItemsBody).subscribe((data: any) => {
      this.responseMessage = data["responseMessage"];
      this.responseCode = data["responseCode"];
      
      if(this.responseCode == 1000) {
        Swal.fire("Success", this.responseMessage, "success");
        this.fetchSelectedItems(this.catId);
      } else {
        Swal.fire("Error", this.responseMessage, "error");
      }
    })
  }
}
