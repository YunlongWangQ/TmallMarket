package com.wp.TmallMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.wp.TmallMarket.dao"})
public class TmallMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmallMarketApplication.class, args);
	}
}
