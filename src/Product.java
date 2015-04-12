/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zoli
 */
public class Product {
    
    private String name;
    private float price;
    
    public Product(String name, float price)
    {
        this.name = name;
        this.price = price;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public float getPrice()
    {
        return this.price;
    }
}
