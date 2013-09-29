-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.18-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema onlinedoc
--

CREATE DATABASE IF NOT EXISTS onlinedoc;
USE onlinedoc;

--
-- Definition of table `tblfile`
--

DROP TABLE IF EXISTS `tblfile`;
CREATE TABLE `tblfile` (
  `fileId` int(10) unsigned NOT NULL auto_increment,
  `fileName` varchar(255) NOT NULL,
  `createDate` datetime NOT NULL,
  PRIMARY KEY  (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblfile`
--

/*!40000 ALTER TABLE `tblfile` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblfile` ENABLE KEYS */;


--
-- Definition of table `tbluser`
--

DROP TABLE IF EXISTS `tbluser`;
CREATE TABLE `tbluser` (
  `userId` int(10) unsigned NOT NULL auto_increment,
  `userName` varchar(45) NOT NULL,
  `userPwd` varchar(45) NOT NULL,
  `createDate` datetime NOT NULL,
  PRIMARY KEY  USING BTREE (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbluser`
--

/*!40000 ALTER TABLE `tbluser` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbluser` ENABLE KEYS */;


--
-- Definition of table `tbluserfile`
--

DROP TABLE IF EXISTS `tbluserfile`;
CREATE TABLE `tbluserfile` (
  `userId` int(10) unsigned NOT NULL,
  `fileId` int(10) unsigned NOT NULL,
  `type` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`userId`,`fileId`),
  KEY `FK_tbluserfile_tblfile_fileId` (`fileId`),
  CONSTRAINT `FK_tbluserfile_tblfile_fileId` FOREIGN KEY (`fileId`) REFERENCES `tblfile` (`fileId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tbluserfile_tbluser_userId` FOREIGN KEY (`userId`) REFERENCES `tbluser` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbluserfile`
--

/*!40000 ALTER TABLE `tbluserfile` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbluserfile` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
