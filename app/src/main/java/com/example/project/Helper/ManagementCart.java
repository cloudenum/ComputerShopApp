package com.example.project.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.project.Domain.ItemDomain;
import com.example.project.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(ItemDomain item) {
        ArrayList<ItemDomain> listFood = getListCard();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }

        tinyDB.putListObject("CardList", listFood);
        Toast.makeText(context, "Added To Your Card", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<ItemDomain> getListCard() {
        return tinyDB.getListObject("CardList");
    }

    public void plusNumberFood(ArrayList<ItemDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }

    public void MinusNumerFood(ArrayList<ItemDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listfood.get(position).getNumberInCart() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }

    public int getTotalPrice() {
        ArrayList<ItemDomain> listFood2 = getListCard();
        int price = 0;
        for (int i = 0; i < listFood2.size(); i++) {
            price = price + (listFood2.get(i).getPrice() * listFood2.get(i).getNumberInCart());
        }
        return price;
    }

}
