DROP SCHEMA IF EXISTS `projectcs548`;

CREATE SCHEMA `projectcs548`;

CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`categoryId`)
)

CREATE TABLE `book` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `categoryId` int(11) DEFAULT NULL,
  PRIMARY KEY (`bookId`),
  CONSTRAINT `book_catID`
    FOREIGN KEY (`categoryId`)
    REFERENCES `category` (`categoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)


INSERT INTO category(categoryName) VALUES('Social');
INSERT INTO category(categoryName) VALUES('Physics');
INSERT INTO category(categoryName) VALUES('Computer');

INSERT INTO book(title,content,categoryId) VALUES('Career Development', 'content of career development', 1);
INSERT INTO book(title,content,categoryId) VALUES('Learning basic Physics', 'content of learning basic physics', 2);
INSERT INTO book(title,content,categoryId) VALUES('Advance coding in Java', 'content of advance coding in java', 3);