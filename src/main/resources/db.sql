-- hr.authority definition

CREATE TABLE `authority` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `feature` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.yearCalendar definition

CREATE TABLE `yearCalendar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `type` tinyint DEFAULT NULL COMMENT '0:上班, 2:放假',
  `note` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=366 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.clock_raw definition

CREATE TABLE `clock_raw` (
  `id` int NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT NULL,
  `emp_no` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.clocktime definition

CREATE TABLE `clocktime` (
  `id` int NOT NULL AUTO_INCREMENT,
  `emp_no` int DEFAULT NULL,
  `clock_date` date DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `status` int DEFAULT NULL,
  `start_id` int DEFAULT NULL,
  `end_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.dept definition

CREATE TABLE `dept` (
  `dept_id` int NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(100) DEFAULT NULL,
  `mg_empno` int DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.employee definition

CREATE TABLE `employee` (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `position` int DEFAULT NULL,
  `dept` int DEFAULT NULL,
  `emp_no` int DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  `leave_date` date DEFAULT NULL,
  `salary` int DEFAULT NULL,
  `health_insurance` int DEFAULT NULL,
  `work_insurance` int DEFAULT NULL,
  `compensation` int DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '1:未在職,  2:在職, 3:離職',
  `email` varchar(100) DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `employee_un` (`emp_no`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.employee_leave definition

CREATE TABLE `employee_leave` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `leave_id` int DEFAULT NULL,
  `hours` float DEFAULT NULL,
  `leave_date` date DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '1:簽核中 2:簽核完成 0:人資補登',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.insurance definition

CREATE TABLE `insurance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `insurance_type` varchar(50) DEFAULT NULL,
  `level` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.`leave` definition

CREATE TABLE `leave` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `day` int DEFAULT NULL,
  `salary_count` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.`position` definition

CREATE TABLE `position` (
  `position_id` int NOT NULL AUTO_INCREMENT,
  `position_name` varchar(100) DEFAULT NULL,
  `dept_id` int DEFAULT NULL,
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.salary_emp_infodetail definition

CREATE TABLE `salary_emp_infodetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `empno` int DEFAULT NULL,
  `leave_type` varchar(10) DEFAULT NULL,
  `leave_hours` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `info_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.salaryinfo definition

CREATE TABLE `salaryinfo` (
  `info_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `calc_date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.salarystatistics definition

CREATE TABLE `salarystatistics` (
  `id` int NOT NULL AUTO_INCREMENT,
  `info_id` int DEFAULT NULL,
  `emp_no` int DEFAULT NULL,
  `rough_salary` int DEFAULT NULL,
  `leave_count` int DEFAULT NULL,
  `work_insurance` int DEFAULT NULL,
  `health_insurance` int DEFAULT NULL,
  `salary_result` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.seat definition

CREATE TABLE `seat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- hr.`user` definition

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `enabled` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;