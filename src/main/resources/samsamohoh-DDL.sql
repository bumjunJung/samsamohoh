/*20160718 code varchar(15) > varchar(20)*/
CREATE TABLE TB_MEMBER(
 code     VARCHAR(20) NOT NULL,       
 id       VARCHAR(30) NOT NULL,       
 name     VARCHAR(20) NOT NULL,       
 reg_date DATETIME   NOT NULL,       
 state    VARCHAR(5)  DEFAULT 'Y',    
 grade    VARCHAR(5)  DEFAULT 'user', 
 PRIMARY KEY (code))
ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tb_family (
	seq int(10) unsigned NOT NULL AUTO_INCREMENT,
	reg_date datetime NOT NULL,
	restaurant_code varchar(20) NOT NULL,
	members varchar(100) ,
	meal_type int(3) NOT NULL,
	active_type varchar(2) DEFAULT NULL,
	PRIMARY KEY (seq),KEY restaurant_code (restaurant_code),
	CONSTRAINT tb_family_ibfk_1 FOREIGN KEY (restaurant_code) REFERENCES tb_restaurant (code) ON DELETE CASCADE)
ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tb_family_member (
	family_seq int(10) unsigned NOT NULL AUTO_INCREMENT,
	member_code varchar(20) NOT NULL,  KEY family_seq (family_seq),
	KEY member_code (member_code),
	CONSTRAINT tb_family_member_ibfk_1 FOREIGN KEY (family_seq) REFERENCES tb_family (seq) ON DELETE CASCADE,
	CONSTRAINT tb_family_member_ibfk_2 FOREIGN KEY (member_code) REFERENCES tb_member (code) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE tb_image (
	seq int(10) unsigned NOT NULL AUTO_INCREMENT,
	real_name varchar(50) NOT NULL,
	change_name varchar(80) NOT NULL,
	file_path varchar(100) NOT NULL,
	reg_date datetime NOT NULL,
	code varchar(20) DEFAULT NULL,
	PRIMARY KEY (seq),
	KEY code (code),
	CONSTRAINT tb_image_ibfk_1 FOREIGN KEY (code) REFERENCES tb_restaurant (code)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


CREATE TABLE tb_pay (
	seq int(10) unsigned NOT NULL AUTO_INCREMENT,
	card_type int(3) NOT NULL,
	meal_type int(3) NOT NULL,
	members varchar(100) NOT NULL,
	restaurant_code varchar(20) NOT NULL,
	card_no varchar(30) NOT NULL,
	settling_date datetime NOT NULL,
	total_price int(10) NOT NULL,
	etc varchar(50) DEFAULT NULL,
	reg_code varchar(20) DEFAULT NULL,
	PRIMARY KEY (seq),
	KEY restaurant_code (restaurant_code),
	KEY reg_code (reg_code),
	CONSTRAINT tb_pay_ibfk_1 FOREIGN KEY (restaurant_code) REFERENCES tb_restaurant (code) ON DELETE CASCADE,
	CONSTRAINT tb_pay_ibfk_2 FOREIGN KEY (reg_code) REFERENCES tb_member (code) ON DELETE CASCADE) 
ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


CREATE TABLE tb_pay_member (
	pay_seq int(10) unsigned NOT NULL AUTO_INCREMENT,
	member_code varchar(20) NOT NULL,
	price int(10) DEFAULT NULL,
	KEY pay_seq (pay_seq),
	KEY member_code (member_code),
	CONSTRAINT tb_pay_member_ibfk_1 FOREIGN KEY (pay_seq) REFERENCES tb_pay (seq) ON DELETE CASCADE,
	CONSTRAINT tb_pay_member_ibfk_2 FOREIGN KEY (member_code) REFERENCES tb_member (code) ON DELETE CASCADE) 
ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


CREATE TABLE tb_reply (
	seq int(10) unsigned NOT NULL AUTO_INCREMENT,
	content text NOT NULL,
	member_code varchar(20) NOT NULL,
	write_date datetime NOT NULL,
	restaurant_code varchar(20) NOT NULL,
	PRIMARY KEY (seq),
	KEY restaurant_code (restaurant_code),
	KEY member_code (member_code),
	CONSTRAINT tb_reply_ibfk_1 FOREIGN KEY (restaurant_code) REFERENCES tb_restaurant (code) ON DELETE CASCADE,
	CONSTRAINT tb_reply_ibfk_2 FOREIGN KEY (member_code) REFERENCES tb_member (code) ON DELETE CASCADE) 
ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*20160718 code varchar(15) > varchar(20)*/
CREATE TABLE tb_restaurant (
	code varchar(20) NOT NULL,
	menu varchar(100) DEFAULT '-',
	name varchar(40) NOT NULL,
	reg_id varchar(30) NOT NULL,
	reg_date datetime NOT NULL,
	tag varchar(100) DEFAULT NULL,
	use_yn varchar(5) DEFAULT 'Y',
	update_date timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
	update_id varchar(30) NOT NULL,
	PRIMARY KEY (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;