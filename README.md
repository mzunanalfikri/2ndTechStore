# 2ndTechStore
Simple E-Commerce App Made with Spring Boot, Spring Security, Spring Data JPA, Postgresql, Thymeleaf, and CSS.
This app deployed in [this link](https://ndtechstore.herokuapp.com).

## Prerequisite :
1. Java 11
2. Maven
3. Spring Boot
4. Thymeleaf

## How to run :
1. clone this project
```
git clone https://github.com/mzunanalfikri/2ndTechStore.git
cd 2ndTechStore
```
2. Make Postgre database using query in src/main/resources/static/crate_table.txt
3. Setting properties in src/main/java/main/resources/application.properties
4. Run Application
```
mvn spring-boot:run
```
## Features
User can register, login, and logout.
● User can update their profile information.
● User can add their items to be sold.
➢ Item sold by an user must have Item name, Item description, Item
category, Item Price, and Item owner.
● User can see their own items in the shop.
● User can remove their own items from the shop.
● User can update their own items from the shop.
● User can see other User items.
● User can search for specific other User items in the shop.
● User can search other User items by category in the shop.
➢ Categories: RAM, Processor, VGA, Motherboard, Storage.
● User can buy other User items (price doesn’t count, so when you click buy
the item will be automatically bought).
● User can’t buy their own products.
● After an item is bought, that item doesn’t exist anymore.
