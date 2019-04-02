CREATE TABLE `events` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `photos` varchar(255) NOT NULL,
  `place_id` varchar(255) NOT NULL,
  `point` bigint(20) NOT NULL,
  `review_id` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `place_history` (
  `place_id` varchar(255) NOT NULL,
  `place_user` varchar(255) NOT NULL,
  `review_id` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`place_id`,`place_user`)
)

CREATE TABLE `special_place` (
  `id` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `point_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `events_id` bigint(20) NOT NULL,
  `points_id` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbich1o0xc7kaek9ads6eick3m` (`events_id`),
  KEY `FKrj6q3b4yrsrck6rqbcqfv9x13` (`points_id`),
  CONSTRAINT `FKbich1o0xc7kaek9ads6eick3m` FOREIGN KEY (`events_id`) REFERENCES `events` (`id`),
  CONSTRAINT `FKrj6q3b4yrsrck6rqbcqfv9x13` FOREIGN KEY (`points_id`) REFERENCES `points` (`id`)
)

CREATE TABLE `points` (
  `id` varchar(255) NOT NULL,
  `point` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `review` (
  `id` varchar(255) NOT NULL,
  `content` int(11) DEFAULT NULL,
  `photos` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);