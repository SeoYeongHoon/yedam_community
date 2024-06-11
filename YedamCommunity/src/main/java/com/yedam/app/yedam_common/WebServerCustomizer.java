package com.yedam.app.yedam_common;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		// 404 error
        ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/errorPages/error404");

        // 500 error
        ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorPages/error500");

        // runtime Exception 및 자식 예외
        ErrorPage exPage = new ErrorPage(RuntimeException.class, "/errorPages/error500");

        factory.addErrorPages(error404, error500, exPage);
	}

}
