package com.hr.hotel.hibernate.dialect;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class CustomMySQLDialect extends MySQL5InnoDBDialect {
	public CustomMySQLDialect() {
		super();
		registerFunction("timestampdiff", new StandardSQLFunction(
				"timestampdiff", StandardBasicTypes.INTEGER));
	}
}
