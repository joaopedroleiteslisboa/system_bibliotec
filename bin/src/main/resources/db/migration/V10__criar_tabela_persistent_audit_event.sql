CREATE TABLE IF NOT EXISTS persistent_audit_event(
    event_id BIGINT  primary key AUTO_INCREMENT,
    principal VARCHAR(1000),
    event_date date,
    event_type VARCHAR(1000)

)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;




CREATE TABLE IF NOT EXISTS persistent_audit_evt_data(
                                                     event_id BIGINT  primary key AUTO_INCREMENT,
                                                     name VARCHAR(1000),
                                                     value VARCHAR(1000)


)ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1000;





