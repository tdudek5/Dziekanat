SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `lastNameValue` varchar(255) DEFAULT NULL,
  `firstNameValue` varchar(255) DEFAULT NULL,
  `nickNameValue` varchar(60) NOT NULL,
  `emailValue` varchar(255) DEFAULT NULL,
  `passwordValue` varchar(255) DEFAULT NULL,
  `PersonTypeMnemonic` varchar(30) NOT NULL,
  `grupaValue` varchar(30) DEFAULT NULL,
  `albumNumberValue` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `person` (`id`, `lastNameValue`, `firstNameValue`, `nickNameValue`, `emailValue`, `passwordValue`, `PersonTypeMnemonic`, `grupaValue`, `albumNumberValue`) VALUES
(6001, 'Studencki', 'Michal', 'ms', 'student.agh@gmail.com', 'a2df1e659c9fd2578de0a26565357cb273292eeb', 'STUDENT', 'Pierwsza', 'US09876'),
(8001, 'Morawska', 'Eleonora', 'mu', 'elakamorka@gmail.com', '2c83d915775688d0b906d1fff291571c1e154864', 'ADMINISTRATION', NULL, ''),
(9001, 'Lepuk', 'Waldek', 'wlepuk', 'wlepuk@agh.com.pl', 'a2df1e659c9fd2578de0a26565357cb273292eeb', 'STUDENT', 'Pierwsza', 'WA654567'),
(10001, 'Mocny', 'Gienek', 'gmocny', 'mocarz.agh@gmail.com', 'a63aa92b17db50b3843221234c60f231a56fdd45', 'STUDENT', 'Druga', 'TF556765'),
(12001, 'Hybiony', 'Zenobiusz', 'zchybiony', 'zenon.Chyian.agh@gmail.com', '39202eb6aed70dd4a1270f5ec5ca4d6171b6c773', 'TEACHER', NULL, ''),
(13001, 'Wyciszonów', 'Romuald', 'rwyciszony', 'romuald.agh@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', 'TEACHER', NULL, ''),
(14001, 'Dudek', 'Tomasz', 'tdudek', 'tdudek5@gmail.com', '9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684', 'STUDENT', 'Pierwsza', 'AB12346'),
(16001, 'Mocny', 'Sołtyś', 'mslos', 'mocarz.agh@gmail.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'STUDENT', 'Druga', 'GF21211'),
(17001, 'Mazurek', 'Mazurek', 'mmazurek', 'mazur.agh@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', 'STUDENT', 'Pierwsza', 'AZ23459'),
(18001, 'Silek', 'GilekMIlek', 'gilek', 'gilsil@gmail.com', 'ea73344294b1c6e2cb529d7fc98a4971de7607ac', 'STUDENT', 'Druga', 'AD54321'),
(21001, 'Brysiowski', 'Rysiek', 'rbysiek', 'mysiekbysiek@gmail.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'STUDENT', 'Pierwsza', 'RY4325'),
(27001, 'Biały', 'Lemon', 'lbialy', 'lbialy@agh.com.pl', NULL, 'STUDENT', NULL, 'AA34323'),
(28001, 'Mały', 'Zenek', 'zmaly', 'zmaly@agh.edu.pl', NULL, 'STUDENT', NULL, ''),
(29001, 'Wolna', 'Anna', 'awolna', 'awolna@agh.edu.pl', NULL, 'STUDENT', NULL, ''),
(30001, 'Nowy', 'Michał', 'mnowy', 'mnowy@agh.edu.pl', NULL, 'STUDENT', NULL, 'AS23432'),
(31001, 'Miły', 'Grzegorz', 'gmily', 'gmily@agh.edu.pl', NULL, 'STUDENT', NULL, 'GT554667'),
(32001, 'Szybki', 'Tadeusz', 'tszybki', 'tszybki@agh.edu.pl', NULL, 'STUDENT', NULL, 'GV454323'),
(33001, 'Placekk', 'Jacek', 'jplacek', 'jplacek@agh.pl', NULL, 'STUDENT', NULL, 'GH67654'),
(34001, 'Fasola', 'Jasiek', 'jfasola', 'jfasola@agh.edu.pl', NULL, 'STUDENT', NULL, 'HJ67543'),
(35001, 'Mądry', 'Andrzej', 'amadry', 'amadry@agh.edu.pl', '8cb2237d0679ca88db6464eac60da96345513964', 'TEACHER', NULL, NULL),
(36001, 'Bolek', 'Lolek', 'lbolek', 'lbole@mail.com', '8cb2237d0679ca88db6464eac60da96345513964', 'TEACHER', NULL, NULL),
(37001, 'Borek', 'Zbigniew', 'zborek', 'zborek@mail.nowy.pl', 'adf112e37655c425d4366a9954a2f1f24bca6d00', 'STUDENT', NULL, 'AS12312'),
(38001, 'yyyy', 'Rafal', 'hhhh', 'yyy@hhhhh', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'TEACHER', NULL, NULL),
(39001, 'Admin', 'Admin', 'admin', 'admin@nasz.email.pl', '8cb2237d0679ca88db6464eac60da96345513964', 'ADMINISTRATION', NULL, NULL);

CREATE TABLE `sequence` (
  `moduleID` int(11) NOT NULL,
  `lastID` int(11) NOT NULL,
  `moduleNameValue` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sequence` (`moduleID`, `lastID`, `moduleNameValue`) VALUES
(1, 39, 'pl.edu.agh.dziekanat.person.Person');


ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
