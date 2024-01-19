package org.example.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductList {

    private List<String> menProducts;
    private List<String> womenProducts;

    public ProductList(){
        menProducts = new ArrayList<>(Arrays.asList(
                "NIKE ZAP SB FORCE 58",
                "CONVERSE ZAP CHUCK TAYLOR AS CX EXPLORE",
                "ADIDAS PANTALON M 3S FT TE PT",
                "UA POLERA PJT ROCK BRAHMA BULL SS",
                "UA POLERA TECH POLO"
        ));
        womenProducts = new ArrayList<>(Arrays.asList(
                "PUMA ZAP CARINA 2.0",
                "UA CALZA MOTION ANKLE",
                "UA MOCHILA FAVORITE BACKPACK",
                "PUMA MOCHILAS CORE POP",
                "NIKE ZAP W COURT VISION ALTA LTR"
        ));
    }
    public int menProductsSize(){
        return menProducts.size();
    }
    public int womenProductsSize(){
        return womenProducts.size();
    }
    public String getMenProduct(int index){
        return menProducts.get(index);
    }
    public String getWomenProduct(int index){
        return womenProducts.get(index);
    }
}
