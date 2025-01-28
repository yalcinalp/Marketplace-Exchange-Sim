# Marketplace-Exchange Simulation:

![gi](https://github.com/user-attachments/assets/939d84ae-a394-4752-8dce-c3ac7c191b17)

- The application dynamically simulates money exchanges between customers and stores in a trading area. Stores keep stock of a single type of product like food and customers in the area visit several of the stores to buy their needs according to their shopping lists.   

## Customers:

Customers keep a shopping list that the products they will buy. To buy a product, a customer should be inside a shop (their drawn entities should collide) that sells and have that type of product. A customer can only buy one product at a single step and should wait for the next time step to buy again. When they buy everything in their lists, customers leave the area and are removed from the simulation afterwards.
 
When shopping, a customer can use one of several strategies: 

- **Cheapest**: Find and go to the cheapest store that **have** the **first product** on the list. Upon arrival, if the store is out of stock wait until the product is available again.
  
- **Closest**: Find and go to the closest store that **have** the **first product** on the list. Upon arrival, if the store is out of stock, try to go to the nearest closest store that sells the product from there.
  
- **Travelling**: Similar to **Closest** strategy, find the closest store, **buy** anything available and on the list and go to closest store from there that is not visited yet. After visiting all stores, if there are still things to buy on the shopping list, start to visit all stores again with same strategy.

## Stores:

Stores are added to simulation from the start, and they are permanent. A store can have a single type of product and its maximum stock and selling price is defined when created. 

When a customer wants to buy an item and the store is out of stock it should signal it to the customer by **raising an error**  

The simulation replenishes all stocks in all stores, in interval. 

- Stores are drawn as orange

## Products:

There are three types of products: 

- Food

- Electronics

- Luxury

Shopping list of a customer can contain arbitrary number of products of these types and a store can have only single type of product in its store.
