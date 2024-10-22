-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 22 oct. 2024 à 11:04
-- Version du serveur : 10.6.19-MariaDB
-- Version de PHP : 8.1.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `tima6358_frederic`
--

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `label` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`category_id`, `label`) VALUES
(1, 'Livre'),
(2, 'Dvd');

-- --------------------------------------------------------

--
-- Structure de la table `category_has_subcategory`
--

CREATE TABLE `category_has_subcategory` (
  `category_id` int(11) NOT NULL,
  `subcategory_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Déchargement des données de la table `category_has_subcategory`
--

INSERT INTO `category_has_subcategory` (`category_id`, `subcategory_id`) VALUES
(1, 1),
(1, 3),
(1, 4),
(1, 5),
(2, 1),
(2, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `emprunt`
--

CREATE TABLE `emprunt` (
  `emprunteur_id` int(11) NOT NULL,
  `media_id` varchar(45) NOT NULL,
  `date_emprunt` date DEFAULT NULL,
  `date_retour` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Déchargement des données de la table `emprunt`
--

INSERT INTO `emprunt` (`emprunteur_id`, `media_id`, `date_emprunt`, `date_retour`) VALUES
(1, '2023-02-00001', '2023-02-15', '2023-02-22'),
(1, '2023-02-00002', '2024-02-12', '2024-02-20'),
(1, '2023-02-00003', '2024-09-20', NULL),
(1, '2023-02-00004', '2023-03-03', '2023-03-14'),
(1, '2023-03-00005', '2023-10-16', '2023-11-08'),
(1, '2023-03-00006', '2024-06-18', '2024-06-26'),
(1, '2023-04-00007', '2024-01-03', '2024-01-08'),
(1, '2023-05-00008', '2023-05-13', '2023-05-22'),
(1, '2023-06-00009', '2023-07-15', '2023-08-04'),
(1, '2023-07-00010', '2023-08-08', '2023-08-11'),
(1, '2023-09-00011', '2023-12-17', '2024-01-05'),
(1, '2023-10-00012', '2023-08-21', '2023-08-26'),
(1, '2023-10-00013', '2024-01-21', '2024-02-09'),
(1, '2023-10-00014', '2024-07-04', '2024-07-30'),
(2, '2023-02-00001', '2024-08-28', NULL),
(2, '2023-02-00002', '2024-04-12', '2024-04-23'),
(2, '2023-02-00003', '2023-02-27', '2023-03-24'),
(2, '2023-02-00004', '2023-10-18', '2023-11-08'),
(2, '2023-03-00005', '2023-11-15', '2023-11-24'),
(2, '2023-03-00006', '2024-05-20', '2024-06-05'),
(2, '2023-04-00007', '2024-04-01', '2024-04-07'),
(2, '2023-05-00008', '2024-06-18', NULL),
(2, '2023-06-00009', '2024-05-23', '2024-06-11'),
(2, '2023-07-00010', '2023-07-11', '2023-07-24'),
(2, '2023-09-00011', '2024-05-22', NULL),
(2, '2023-10-00012', '2023-01-02', '2023-01-09'),
(2, '2023-10-00013', '2024-03-01', '2024-03-19'),
(2, '2023-10-00014', '2024-07-27', '2024-08-02'),
(3, '2023-02-00001', '2024-08-13', '2024-09-02'),
(3, '2023-02-00002', '2023-03-31', '2023-04-09'),
(3, '2023-02-00003', '2023-11-28', '2023-12-01'),
(3, '2023-02-00004', '2023-08-24', '2023-09-17'),
(3, '2023-03-00005', '2023-04-18', '2023-05-11'),
(3, '2023-03-00006', '2023-03-20', '2023-04-17'),
(3, '2023-04-00007', '2023-04-24', '2023-05-02'),
(3, '2023-05-00008', '2024-01-23', '2024-02-20'),
(3, '2023-06-00009', '2024-06-01', '2024-06-19'),
(3, '2023-07-00010', '2023-09-26', '2023-09-26'),
(3, '2023-09-00011', '2024-02-13', '2024-03-12'),
(3, '2023-10-00012', '2024-01-09', '2024-01-29'),
(3, '2023-10-00013', '2023-10-27', '2023-11-16'),
(3, '2023-10-00014', '2024-08-22', NULL),
(4, '2023-02-00003', '2024-03-30', '2024-04-22'),
(4, '2023-02-00004', '2024-02-09', '2024-03-06'),
(4, '2023-03-00006', '2024-02-18', '2024-03-02'),
(4, '2023-05-00008', '2023-10-28', '2023-10-31'),
(4, '2023-06-00009', '2024-05-09', '2024-06-05'),
(4, '2023-09-00011', '2023-09-19', '2023-09-26'),
(4, '2023-10-00013', '2024-04-09', NULL),
(4, '2023-10-00014', '2024-08-15', '2024-08-18');

-- --------------------------------------------------------

--
-- Structure de la table `emprunteur`
--

CREATE TABLE `emprunteur` (
  `emprunteur_id` int(11) NOT NULL,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Déchargement des données de la table `emprunteur`
--

INSERT INTO `emprunteur` (`emprunteur_id`, `nom`, `prenom`, `date_naissance`) VALUES
(1,  'Dupont', 'Jean', '1985-02-15'),
(2,  'Martin', 'Sophie', '1990-06-25'),
(3,  'Durand', 'Pierre', '1983-11-10'),
(4,  'Lefevre', 'Marie', '1992-04-05'),
(5,  'Moreau', 'Paul', '1978-09-18'),
(6,  'Simon', 'Julie', '1987-12-30'),
(7,  'Laurent', 'Luc', '1989-03-22'),
(8,  'Roux', 'Claire', '1995-07-14'),
(9,  'Blanc', 'Antoine', '1981-08-09'),
(10,  'Faure', 'Emma', '1993-05-20'),
(11,  'Durand', 'Pierre', '1985-01-15'),
(12,  'Martin', 'Sophie', '1990-03-22'),
(13,  'Bernard', 'Luc', '1982-07-18'),
(14,  'Petit', 'Marie', '1975-11-30'),
(15,  'Robert', 'Nicolas', '1995-06-10'),
(16,  'Richard', 'Julie', '1988-12-02'),
(17,  'Dubois', 'Claire', '1993-08-25'),
(18,  'Moreau', 'Hugo', '1989-09-17'),
(19,  'Laurent', 'Camille', '1983-05-05'),
(20,  'Lefebvre', 'Louis', '1979-10-11'),
(21,  'Simon', 'Elodie', '1991-04-20'),
(22,  'Michel', 'Antoine', '1987-02-13'),
(23,  'Leroy', 'Sarah', '1994-07-22'),
(24,  'Roux', 'Thomas', '1980-12-08'),
(25,  'David', 'Manon', '1986-09-01'),
(26,  'Bertrand', 'Arthur', '1992-03-29'),
(27,  'Morel', 'Chloe', '1984-11-05'),
(28,  'Fournier', 'Lucas', '1978-01-27'),
(29,  'Girard', 'Alice', '1996-06-18'),
(30,  'Bonnet', 'Hugo', '1983-10-23'),
(31,  'Dupont', 'Emma', '1990-02-11'),
(32,  'Lambert', 'Victor', '1981-08-14'),
(33,  'Fontaine', 'Lea', '1987-11-07'),
(34,  'Chevalier', 'Alexandre', '1993-04-02'),
(35,  'Guerin', 'Aurelie', '1989-12-20'),
(36,  'Faure', 'Benoit', '1992-07-16'),
(37,  'André', 'Charlotte', '1985-05-19'),
(38,  'Mercier', 'Theo', '1991-01-30'),
(39,  'Blanc', 'Valentin', '1977-09-05'),
(40,  'Gauthier', 'Anais', '1986-03-12'),
(41,  'Murphy', 'Sean', '1985-03-15'),
(42,  'OBrien', 'Liam', '1990-06-21'),
(43,  'OConnor', 'Niamh', '1987-12-04'),
(44,  'Kelly', 'Eoin', '1983-01-12'),
(45,  'Byrne', 'Aoife', '1992-09-17'),
(46,  'Walsh', 'Siobhan', '1989-11-05'),
(47,  'Ryan', 'Conor', '1984-07-22'),
(48,  'Doyle', 'Ciaran', '1991-04-30'),
(49,  'McCarthy', 'Roisin', '1988-05-25'),
(50,  'OSullivan', 'Aidan', '1982-10-14'),
(51,  'Garcia', 'Carlos', '1985-02-18'),
(52, 'Martinez', 'Maria', '1986-08-30'),
(53, 'Rodriguez', 'Juan', '1991-03-02'),
(54, 'Lopez', 'Ana', '1989-12-12'),
(55, 'Hernandez', 'Pedro', '1983-04-07'),
(56, 'Gonzalez', 'Sofia', '1992-09-09'),
(57, 'Perez', 'Luis', '1987-11-16'),
(58, 'Sanchez', 'Isabel', '1984-01-29'),
(59, 'Ramirez', 'Miguel', '1990-06-05'),
(60, 'Diaz', 'Elena', '1993-07-19');


-- --------------------------------------------------------

--
-- Structure de la table `media`
--

CREATE TABLE `media` (
  `media_id` varchar(45) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `description` longtext DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `subcategory_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Déchargement des données de la table `media`
--

INSERT INTO `media` (`media_id`, `title`, `description`, `category_id`, `subcategory_id`) VALUES
('2023-02-00001', 'La Fille du train', 'Ce roman captivant suit Rachel, une femme en crise, qui prend chaque jour le même train et observe la vie d''un couple qu''elle admire à distance. Un jour, elle est témoin d''un événement choquant, puis se retrouve mêlée à la disparition de la femme. Rachel doit déterrer la vérité tout en luttant contre ses propres démons.', 1, 1),
('2023-02-00002', 'Le Petit Prince', 'Ce conte poétique raconte l''histoire d''un aviateur échoué dans le désert qui rencontre un jeune prince venu d''une petite planète. À travers les yeux du prince, il découvre des leçons de vie sur l''amitié, l''amour et la perte, révélant l''importance de voir au-delà des apparences et de cultiver l''innocence de l''enfance.', 1, 4),
('2023-02-00003', 'Le Silence des Agneaux', 'Dans ce thriller psychologique, l''agent du FBI Clarice Starling est chargée d''interroger le brillant mais dangereux tueur en série Hannibal Lecter, incarcéré dans un hôpital psychiatrique. Alors qu''elle cherche à comprendre l''esprit tordu de Lecter, elle doit également traquer un autre tueur qui enlève des femmes, plongeant ainsi dans un jeu d''esprit mortel.', 1, 1),
('2023-02-00004', 'Prisoners', 'Lorsqu''un homme, Keller Dover (Hugh Jackman), voit sa fille et son amie disparaître, il prend les choses en main lorsqu''il estime que la police ne fait pas assez pour les retrouver. Alors que la tension monte, le film explore les thèmes de la justice, de la moralité et des limites de la loi dans un récit intense et captivant.', 2, 1),
('2023-03-00005', 'Superbad', 'Deux adolescents, Seth et Evan, cherchent à profiter de leurs dernières semaines au lycée en essayant d''acheter de l''alcool pour une fête. Leur quête les entraîne dans des situations hilarantes et imprévues, mettant en lumière leur amitié et les défis de la transition vers l''âge adulte.', 2, 2),
('2023-03-00006', 'Arrival', 'Lorsque douze vaisseaux spatiaux extraterrestres atterrissent sur Terre, une linguiste, Louise Banks (Amy Adams), est recrutée par l''armée pour établir un contact avec les aliens. Alors qu''elle tente de comprendre leur langage complexe, elle fait face à des défis de communication et découvre des vérités bouleversantes sur le temps, la mémoire et la nature humaine. Ce film introspectif explore des thèmes profonds tout en étant un thriller de science-fiction captivant.', 2, 3),
('2023-04-00007', 'L''Appel du Coucou', 'Ce premier livre de la série mettant en scène le détective privé Cormoran Strike commence lorsque la sœur d''une célèbre mannequin disparue engage Strike pour enquêter sur sa mort. À mesure qu''il creuse dans le monde glamour de la mode, Strike découvre des secrets sombres et des mensonges, mettant en lumière des thèmes de célébrité et de déception.', 1, 1),
('2023-05-00008', 'Inception', 'Dom Cobb (Leonardo DiCaprio) est un voleur de secrets, capable d''entrer dans les rêves des autres pour y dérober des informations précieuses. Recruté pour réaliser un \"inception\" — implanter une idée dans l''esprit d''une cible — Cobb et son équipe doivent naviguer à travers des rêves imbriqués, tout en luttant contre leurs propres démons. Le film est un thriller complexe qui questionne la nature de la réalité et des rêves.', 2, 3),
('2023-06-00009', 'Les Visiteurs', 'Ce film français raconte l''histoire de Godefroy de Montmirail, un noble du Moyen Âge, et de son fidèle écuyer, Jacquouille, qui se retrouvent accidentellement propulsés dans le futur. Leur maladresse face à la modernité et aux moeurs contemporaines provoque des situations comiques mémorables.', 2, 2),
('2023-07-00010', 'Dune', 'Dans un lointain avenir, la planète désertique d''Arrakis, surnommée Dune, est la seule source de l''épice précieuse qui prolonge la vie et accorde des pouvoirs mentaux. Le jeune Paul Atreides, héritier de la Maison Atreides, se retrouve au cœur d''une lutte politique et écologique. Alors que sa famille est trahie et que Paul doit fuir dans le désert, il découvre son destin lié à Dune et aux mystérieux Fremen, les habitants autochtones de la planète. Ce roman explore des thèmes de pouvoir, d''écologie et de prophétie dans un univers riche et complexe.', 1, 3),
('2023-09-00011', 'Blade Runner 2049', 'Dans cette suite du classique \"Blade Runner\", un nouveau blade runner, K (Ryan Gosling), découvre un secret longtemps enfoui qui pourrait plonger la société dans le chaos. Sa quête pour retrouver Rick Deckard (Harrison Ford), un ancien blade runner disparu depuis des décennies, l''entraîne dans un voyage à travers un futur dystopique riche en mystères et en réflexions sur l''humanité.', 2, 3),
('2023-10-00012', 'L''Ombre du Vent', 'Dans la Barcelone d''après-guerre, un jeune garçon nommé Daniel découvre un livre dans un mystérieux cimetière de livres oubliés. En cherchant à en savoir plus sur l''auteur, il se retrouve plongé dans une intrigue mêlant secrets, passion et trahison, tout en naviguant à travers les mystères de la ville.', 1, 4),
('2023-10-00013', 'Le Grand Lebowski', 'Cette comédie culte suit Jeffrey \"The Dude\" Lebowski, un paresseux amateur de bowling dont la vie est bouleversée lorsqu''il est confondu avec un milliardaire du même nom. Avec l''aide de ses amis excentriques, The Dude se retrouve entraîné dans une série de mésaventures loufoques et absurdes.', 2, 2),
('2023-10-00014', 'Se7en', 'Deux détectives, Somerset (Morgan Freeman) et Mills (Brad Pitt), traquent un tueur en série qui utilise les sept péchés capitaux comme motifs pour ses meurtres. À mesure qu''ils avancent dans l''enquête, ils découvrent une spirale de violence et de désespoir qui les conduit à une conclusion choquante et dévastatrice.', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `subcategory`
--

CREATE TABLE `subcategory` (
  `subcategory_id` int(11) NOT NULL,
  `label` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Déchargement des données de la table `subcategory`
--

INSERT INTO `subcategory` (`subcategory_id`, `label`) VALUES
(1, 'Policier'),
(2, 'Comedie'),
(3, 'Science Fiction'),
(4, 'Roman'),
(5, 'Drame');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `emprunteur`
--
ALTER TABLE `emprunteur`
  ADD PRIMARY KEY (`emprunteur_id`);

--
-- Index pour la table `subcategory`
--
ALTER TABLE `subcategory`
  ADD PRIMARY KEY (`subcategory_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `emprunteur`
--
ALTER TABLE `emprunteur`
  MODIFY `emprunteur_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT pour la table `subcategory`
--
ALTER TABLE `subcategory`
  MODIFY `subcategory_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
