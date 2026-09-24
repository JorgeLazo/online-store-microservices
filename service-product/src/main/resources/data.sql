Insert Into tbl_categories (id, name) values (1, 'Electronics');
Insert Into tbl_categories (id, name) values (2, 'Computers');
Insert Into tbl_categories (id, name) values (3, 'Books');

Insert Into tbl_products (name, description, stock, price, status, create_at, category_id) 
values ('Laptop', 'High performance laptop', 10, 1200.00, 'ACTIVE', now(), 1);
Insert Into tbl_products (name, description, stock, price, status, create_at, category_id) 
values ('Smartphone', 'Latest model smartphone', 20, 800.00, 'ACTIVE', now(), 1); 
Insert Into tbl_products (name, description, stock, price, status, create_at, category_id) 
values ('Novel', 'Bestselling novel', 50, 20.00, 'ACTIVE', now(), 2); 