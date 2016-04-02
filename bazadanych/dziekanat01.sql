-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 03 Kwi 2016, 01:57
-- Wersja serwera: 10.1.10-MariaDB
-- Wersja PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `dziekanat01`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `groupstudent`
--

CREATE TABLE `groupstudent` (
  `id` int(11) NOT NULL,
  `nameValue` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `groupStudentTypeMnemonic` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `groupstudent`
--

INSERT INTO `groupstudent` (`id`, `nameValue`, `groupStudentTypeMnemonic`) VALUES
(1002, 'Grupa I', 'LAB'),
(2002, 'Grupa I', 'DEAN'),
(3002, 'Grupa II', 'DEAN'),
(4002, 'Grupa II', 'LAB'),
(5002, 'Grupa III', 'LAB'),
(6002, 'Grupa IV', 'LAB'),
(7002, 'Grupa IV', 'LAB');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `lastNameValue` varchar(255) DEFAULT NULL,
  `firstNameValue` varchar(255) DEFAULT NULL,
  `nickNameValue` varchar(60) NOT NULL,
  `emailValue` varchar(255) DEFAULT NULL,
  `passwordValue` varchar(255) DEFAULT NULL,
  `PersonTypeMnemonic` varchar(30) NOT NULL,
  `albumNumberValue` varchar(20) DEFAULT NULL,
  `groupId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `person`
--

INSERT INTO `person` (`id`, `lastNameValue`, `firstNameValue`, `nickNameValue`, `emailValue`, `passwordValue`, `PersonTypeMnemonic`, `albumNumberValue`, `groupId`) VALUES
(6001, 'Studencki', 'Michal', 'ms', 'student.agh@gmail.com', 'a2df1e659c9fd2578de0a26565357cb273292eeb', 'STUDENT', 'US09876', NULL),
(8001, 'Morawska', 'Eleonora', 'mu', 'elakamorka@gmail.com', '2c83d915775688d0b906d1fff291571c1e154864', 'ADMINISTRATION', '', NULL),
(9001, 'Lepuk', 'Waldek', 'wlepuk', 'wlepuk@agh.com.pl', 'a2df1e659c9fd2578de0a26565357cb273292eeb', 'STUDENT', 'WA654567', NULL),
(10001, 'Mocny', 'Gienek', 'gmocny', 'mocarz.agh@gmail.com', 'a63aa92b17db50b3843221234c60f231a56fdd45', 'STUDENT', 'TF556765', NULL),
(12001, 'Hybiony', 'Zenobiusz', 'zchybiony', 'zenon.Chyian.agh@gmail.com', '39202eb6aed70dd4a1270f5ec5ca4d6171b6c773', 'TEACHER', '', NULL),
(13001, 'Wyciszonów', 'Romuald', 'rwyciszony', 'romuald.agh@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', 'TEACHER', '', NULL),
(14001, 'Dudek', 'Tomasz', 'tdudek', 'tdudek5@gmail.com', '9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684', 'STUDENT', 'AB12346', 1002),
(16001, 'Mocny', 'Sołtyś', 'mslos', 'mocarz.agh@gmail.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'STUDENT', 'GF21211', NULL),
(17001, 'Mazurek', 'Mazurek', 'mmazurek', 'mazur.agh@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', 'STUDENT', 'AZ23459', NULL),
(18001, 'Silek', 'GilekMIlek', 'gilek', 'gilsil@gmail.com', 'ea73344294b1c6e2cb529d7fc98a4971de7607ac', 'STUDENT', 'AD54321', NULL),
(21001, 'Brysiowski', 'Rysiek', 'rbysiek', 'mysiekbysiek@gmail.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'STUDENT', 'RY4325', NULL),
(27001, 'Biały', 'Lemon', 'lbialy', 'lbialy@agh.com.pl', NULL, 'STUDENT', 'AA34323', NULL),
(28001, 'Mały', 'Zenek', 'zmaly', 'zmaly@agh.edu.pl', NULL, 'STUDENT', '', NULL),
(29001, 'Wolna', 'Anna', 'awolna', 'awolna@agh.edu.pl', NULL, 'STUDENT', '', NULL),
(30001, 'Nowy', 'Michał', 'mnowy', 'mnowy@agh.edu.pl', NULL, 'STUDENT', 'AS23432', NULL),
(31001, 'Miły', 'Grzegorz', 'gmily', 'gmily@agh.edu.pl', NULL, 'STUDENT', 'GT554667', NULL),
(32001, 'Szybki', 'Tadeusz', 'tszybki', 'tszybki@agh.edu.pl', NULL, 'STUDENT', 'GV454323', NULL),
(33001, 'Placekk', 'Jacek', 'jplacek', 'jplacek@agh.pl', NULL, 'STUDENT', 'GH67654', NULL),
(34001, 'Fasola', 'Jasiek', 'jfasola', 'jfasola@agh.edu.pl', NULL, 'STUDENT', 'HJ67543', NULL),
(35001, 'Mądry', 'Andrzej', 'amadry', 'amadry@agh.edu.pl', '8cb2237d0679ca88db6464eac60da96345513964', 'TEACHER', NULL, NULL),
(36001, 'Bolek', 'Lolek', 'lbolek', 'lbole@mail.com', '8cb2237d0679ca88db6464eac60da96345513964', 'TEACHER', NULL, NULL),
(37001, 'Borek', 'Zbigniew', 'zborek', 'zborek@mail.nowy.pl', 'adf112e37655c425d4366a9954a2f1f24bca6d00', 'STUDENT', 'AS12312', NULL),
(38001, 'yyyy', 'Rafal', 'hhhh', 'yyy@hhhhh', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'TEACHER', NULL, NULL),
(39001, 'Admin', 'Admin', 'admin', 'admin@nasz.email.pl', '8cb2237d0679ca88db6464eac60da96345513964', 'ADMINISTRATION', NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `sequence`
--

CREATE TABLE `sequence` (
  `moduleID` int(11) NOT NULL,
  `lastID` int(11) NOT NULL,
  `moduleNameValue` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `sequence`
--

INSERT INTO `sequence` (`moduleID`, `lastID`, `moduleNameValue`) VALUES
(1, 39, 'pl.edu.agh.dziekanat.person.Person'),
(2, 7, 'pl.edu.agh.dziekanat.model.GroupStudent');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `groupstudent`
--
ALTER TABLE `groupstudent`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `groupId_2` (`groupId`),
  ADD KEY `groupId` (`groupId`);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `FK64xlxxengen1ltl16ykrwqoy5` FOREIGN KEY (`groupId`) REFERENCES `groupstudent` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
