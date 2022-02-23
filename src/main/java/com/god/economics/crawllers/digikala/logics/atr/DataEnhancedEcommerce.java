package com.god.economics.crawllers.digikala.logics.atr;

public class DataEnhancedEcommerce{
 private int id;

 private String name;

 private String category;

 private String brand;
 private String src;

 private int variant;

 private int price;

 private int quantity;

 public void setId(int id){
  this.id = id;
 }
 public int getId(){
  return this.id;
 }
 public void setName(String name){
  this.name = name;
 }
 public String getName(){
  return this.name;
 }
 public void setCategory(String category){
  this.category = category;
 }
 public String getCategory(){
  return this.category;
 }
 public void setBrand(String brand){
  this.brand = brand;
 }
 public String getBrand(){
  return this.brand;
 }
 public void setVariant(int variant){
  this.variant = variant;
 }
 public int getVariant(){
  return this.variant;
 }
 public void setPrice(int price){
  this.price = price;
 }
 public int getPrice(){
  return this.price;
 }
 public void setQuantity(int quantity){
  this.quantity = quantity;
 }
 public int getQuantity(){
  return this.quantity;
 }

 public String getSrc() {
  return src;
 }

 public void setSrc(String src) {
  this.src = src;
 }
}
