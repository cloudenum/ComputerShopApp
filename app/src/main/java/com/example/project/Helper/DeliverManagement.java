package com.example.project.Helper;

import android.content.Context;
import android.util.Log;

import com.example.project.Domain.ItemDomain;

import java.util.ArrayList;

public class DeliverManagement {
    private Context context;
    private TinyDB tinyDB;
    ManagementCart managementCart;

    public DeliverManagement(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
        managementCart = new ManagementCart(context);
    }

    public void insertToDelivering(ArrayList<ItemDomain> list) {

        tinyDB.putListObject("DeliveringList", list);
    }

    public void insertToDelivered(ArrayList<ItemDomain> list) {

        tinyDB.putListObject("DeliveredList", list);
    }

    public ArrayList<ItemDomain> getDeliveringList() {
        return tinyDB.getListObject("DeliveringList");
    }

    public ArrayList<ItemDomain> getDeliveredList() {
        return tinyDB.getListObject("DeliveredList");
    }

    public void RemoveList(){
        tinyDB.remove("DeliveringList");
    }

    public int getTotalPrice() {
        ArrayList<ItemDomain> listFood2 = getDeliveringList();

        int price = 0;
        int id = 0;
        int fee = 0;

        Log.d("TAG", "getTotalPrice11: " + listFood2.size());

        if(listFood2.size() != 0){
            if(listFood2.size() > 1){
                for (int i = 0; i < listFood2.size(); i++) {
                    price = price + (listFood2.get(i).getPrice() * listFood2.get(i).getNumberInCart());
                    if(id != listFood2.get(i).getId()) {
                        fee += listFood2.get(i).getFee();
                        id = listFood2.get(i).getId();
                    }
                }
            }else{
                price = price + (listFood2.get(0).getPrice() * listFood2.get(0).getNumberInCart());
                fee = fee + listFood2.get(0).getFee();
            }
        }

        Log.d("TAG", "getTotalPrice:11 " + fee);

        return price+fee;
    }

    public ArrayList<ItemDomain> copyList(int _id, int fee){
        ArrayList<ItemDomain> listFood2 = managementCart.getListCard();
        ArrayList<ItemDomain> items1 = new ArrayList<>();
        ItemDomain food = new ItemDomain();

        for (int i = 0; i < listFood2.size(); i++) {
            food = listFood2.get(i);
            food.setFee(fee);
            food.setId(_id);

            items1.add(food);
        }

        return items1;
    }
}
