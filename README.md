
# CRUD API Kotlin with Spring Boot and GraphQL example.

## GraphQL API:

[ ] Querys and Mutation by GUI:

- http://localhost:8180/gui

- query { productById(productId: 1) { name } } 
- query { productBySku(sku: 1) { name } } 
- query { products { productId name } }
- query { products { productId name inventory { warehouses { locality quantity } } } }

- mutation { createProduct(product: { properties... } ) { name } } 
- mutation { updateProduct(product: { properties... } ) {} } 
- mutation { removeProductById(productId: 1 ) {} } 
- mutation { removeProductBySku(sku: 1 ) {} } 


## REST API:

[ ] GET - Find product(s)

- http://localhost:8180/products

- http://localhost:8180/products/2/id

- http://localhost:8180/products/12345/sku

[ ] POST - Create product

- http://localhost:8180/products

[ ] PUT - Update product

- http://localhost:8180/products

[ ] DELETE - Remove product

- http://localhost:8180/products/12345/id

- http://localhost:8180/products/12345/sku
