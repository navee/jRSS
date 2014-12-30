CREATE TABLE `item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `channel_id` int(11) NOT NULL,
  `title` varchar(300) DEFAULT '',
  `link` varchar(200) DEFAULT '',
  `description` text,
  `sync_date` datetime DEFAULT NULL,
  `pub_date` varchar(64) DEFAULT '''''',
  `is_read` tinyint(1) NOT NULL DEFAULT '0',
  `is_star` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `channel` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(300) DEFAULT NULL,
  `link` varchar(128) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `source_link` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;