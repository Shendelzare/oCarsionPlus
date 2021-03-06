DROP TABLE IF EXISTS PRICE;
DROP TABLE IF EXISTS CAR;
DROP TABLE IF EXISTS BRAND;


CREATE TABLE IF NOT EXISTS BRAND
(
	ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    NAME   VARCHAR(255)      NOT NULL
); 

CREATE TABLE IF NOT EXISTS CAR
(
	ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ID_BRAND   INT   NOT NULL,   
    MODEL_NAME   VARCHAR(255)      NOT NULL,
    COLOR INT(2) NOT NULL,
    FOREIGN KEY(ID_BRAND) REFERENCES BRAND(ID)
);

CREATE TABLE IF NOT EXISTS PRICE
(
	ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    VALUE   VARCHAR(32) NOT NULL,
    EFFECTIVE_DATE DATE NOT NULL,
    END_EFFECTIVE_DATE DATE,
    ID_CAR INT NOT NULL,
    FOREIGN KEY(ID_CAR) REFERENCES CAR(ID)
);


INSERT INTO BRAND ( NAME ) VALUES ('Ford');
INSERT INTO CAR (ID_BRAND, MODEL_NAME, color ) VALUES ( 1, 'Mondeo', 0);
INSERT INTO PRICE (VALUE,EFFECTIVE_DATE, END_EFFECTIVE_DATE, ID_CAR ) VALUES ('2500.00', PARSEDATETIME('19-03-2021','dd-MM-yyyy'),PARSEDATETIME('20-03-2022','dd-MM-yyyy'), 1);

INSERT INTO CAR (ID_BRAND, MODEL_NAME, color ) VALUES ( 1, 'Mustang', 6);
INSERT INTO PRICE (VALUE,EFFECTIVE_DATE, END_EFFECTIVE_DATE, ID_CAR ) VALUES ('30000.00', PARSEDATETIME('19-03-2015','dd-MM-yyyy'),PARSEDATETIME('20-03-2021','dd-MM-yyyy'), 2);
INSERT INTO PRICE (VALUE,EFFECTIVE_DATE, END_EFFECTIVE_DATE, ID_CAR ) VALUES ('35000.00', PARSEDATETIME('19-03-2014','dd-MM-yyyy'),PARSEDATETIME('18-03-2015','dd-MM-yyyy'), 2);
INSERT INTO PRICE (VALUE,EFFECTIVE_DATE, END_EFFECTIVE_DATE, ID_CAR ) VALUES ('20000.00', PARSEDATETIME('21-03-2021','dd-MM-yyyy'),PARSEDATETIME('20-03-2023','dd-MM-yyyy'),2);


 
INSERT INTO BRAND ( NAME ) VALUES ('Mazda');
INSERT INTO CAR (ID_BRAND, MODEL_NAME, color ) VALUES ( 2, 'MX-5', 1);
INSERT INTO PRICE (VALUE,EFFECTIVE_DATE, ID_CAR ) VALUES ('25000.00', PARSEDATETIME('19-03-2021','dd-MM-yyyy'),3);
INSERT INTO PRICE (VALUE,EFFECTIVE_DATE, END_EFFECTIVE_DATE, ID_CAR ) VALUES ('30000.00', PARSEDATETIME('19-01-2021','dd-MM-yyyy'),PARSEDATETIME('18-03-2021','dd-MM-yyyy'), 3);

INSERT INTO CAR (ID_BRAND, MODEL_NAME, color ) VALUES ( 2, 'X-5', 3);
INSERT INTO PRICE (VALUE,EFFECTIVE_DATE, ID_CAR ) VALUES ('25000.00', PARSEDATETIME('19-03-2021','dd-MM-yyyy'),4);
INSERT INTO PRICE (VALUE,EFFECTIVE_DATE, END_EFFECTIVE_DATE, ID_CAR ) VALUES ('30000.00', PARSEDATETIME('19-01-2021','dd-MM-yyyy'),PARSEDATETIME('18-03-2021','dd-MM-yyyy'), 4);



COMMIT;