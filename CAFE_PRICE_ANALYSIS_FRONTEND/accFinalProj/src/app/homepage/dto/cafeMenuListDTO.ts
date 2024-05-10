export class cafeMenuListDTO {
    id: number;
    itemName: string;
    itemDescription: string;
    itemPrice: string;
    categoryId: number;
    cafeId: number;
    checked?: boolean = false;
}