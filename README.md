# Marketplace-Exchange Simulation:

- The application dynamically simulates money exchanges between customers and stores in a confined area. Stores keep stock of a single type of product like food and customers in the area visit several of the stores to buy their needs according to their shopping lists.   


## Customers:

Customers keep a shopping list that the products they will buy. To buy a product, a customer should be inside a shop (their drawn entities should collide) that sells and have that type of product. A customer can only buy one product at a single step and should wait for the next time step to buy again. When they buy everything in their lists, customers leave the area and are removed from the simulation afterwards.
 
When shopping, a customer can use one of several strategies: 

- *Cheapest*: Find and go to the cheapest store that *have* the *first product on the list. Upon arrival, if the store is out of stock wait until the product is available again.
  
- *Closest*: Find and go to the closest store that *have* the *first product* on the list. Upon arrival, if the store is out of stock, try to go to the nearest closest store that sells the product from there.
  
- *Travelling*: Similar to *Closest* strategy, find the closest store, *buy* anything avail- able and on the l
