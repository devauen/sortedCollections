package com.proartz;

import java.util.Map;

public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        Basket timsBasket = new Basket("Tim");
        Basket janesBasket = new Basket("Jane");
        Basket petersBasket = new Basket("Peter");
        
        System.out.println(timsBasket);
        System.out.println(janesBasket);
        System.out.println(petersBasket);
        System.out.println(stockList);

        reserveItem(timsBasket, "car", 1);
        reserveItem(timsBasket, "car", 1);
        reserveItem(timsBasket, "spanner", 5);
        reserveItem(timsBasket, "juice", 4);
        reserveItem(timsBasket, "cup", 12);
        reserveItem(timsBasket, "bread", 1);

        System.out.println(timsBasket);
        System.out.println(janesBasket);
        System.out.println(petersBasket);
        System.out.println(stockList);

        reserveItem(janesBasket, "car", 1);
        reserveItem(janesBasket, "car", 1);
        reserveItem(janesBasket, "spanner", 5);
        reserveItem(janesBasket, "juice", 4);
        reserveItem(janesBasket, "cup", 12);
        reserveItem(janesBasket, "bread", 1);

        System.out.println(timsBasket);
        System.out.println(janesBasket);
        System.out.println(petersBasket);
        System.out.println(stockList);

        reserveItem(petersBasket, "car", 1);
        reserveItem(petersBasket, "car", 1);
        reserveItem(petersBasket, "spanner", 5);
        reserveItem(petersBasket, "juice", 4);
        reserveItem(petersBasket, "cup", 12);
        reserveItem(petersBasket, "bread", 1);
        unreserveItem(petersBasket, "bread");
        unreserveItem(petersBasket, "bread");

        System.out.println(timsBasket);
        System.out.println(janesBasket);
        System.out.println(petersBasket);
        System.out.println(stockList);

        checkout(timsBasket);
        checkout(janesBasket);
        checkout(petersBasket);

        System.out.println(timsBasket);
        System.out.println(janesBasket);
        System.out.println(petersBasket);
        System.out.println(stockList);


//        for(Map.Entry<String, Double> price : stockList.PriceList().entrySet()) {
//            System.out.println(price.getKey() + " costs " + price.getValue());
//        }
    }
    public static int reserveItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.reserveStock(item, quantity) != 0) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }

    public static int unreserveItem(Basket basket, String item) {
        int quantity = basket.removeFromBasket(new StockItem(item, 0));
        return stockList.unreserveStock(item, quantity);
    }

    public static void checkout(Basket basket) {
        // retrieve itemst from the basket
        Map<StockItem, Integer> basketItems = basket.Items();

        // iterate through the list of items in the basket
        for(Map.Entry<StockItem, Integer> basketItem : basketItems.entrySet()) {
            // for every item in the basket, find the item in the stockList
            StockItem stockItem = stockList.get(basketItem.getKey().getName());

            // adjust quantity
            stockItem.adjustStock((-1) * basketItem.getValue());

            // adjust reserve
            stockItem.adjustReserve((-1) * basketItem.getValue());
        }

        basket.empty();
    }
}