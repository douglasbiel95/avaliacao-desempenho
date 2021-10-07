package com.douglasgabriel.avaliacaodesempenho.utils.custom;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class InfoWebEndpointExtension {

	@Autowired
	private InfoEndpoint delegateInfo;
	
	@ReadOperation
	public WebEndpointResponse<Map> infoAplicacao() {
		Map<String, Object> infosApp = this.delegateInfo.info();
		Integer codigoStatus = getStatus(infosApp);
		Map<String, Object> customInfo = new HashMap<>();
		customInfo.put("customInfo", "handOn");
		return new WebEndpointResponse<>(customInfo, codigoStatus);
	}

	private Integer getStatus(Map<String, Object> infosApp) {
		// TODO Auto-generated method stub
		return 200;
	}
}
