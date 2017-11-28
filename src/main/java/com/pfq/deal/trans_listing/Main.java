package com.pfq.deal.trans_listing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pfq.deal.trans_listing.util.SpringContextUtils;

@EnableTransactionManagement
@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class, DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
//@ImportResource("classpath:provider.xml")
@ComponentScan(basePackages = "com.pfq")
public class Main {

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(Main.class);
		ConfigurableApplicationContext context=application.run(args);
		SpringContextUtils.setApplicationContext(context);
	}

}
