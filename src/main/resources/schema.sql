CREATE TABLE BET (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    bet_type VARCHAR(10) NOT NULL,
    prop_number INT NOT NULL,
    customer_id INT NOT NULL,
    investment DECIMAL NOT NULL
); 
    
    