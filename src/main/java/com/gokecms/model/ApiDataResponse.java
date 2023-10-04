package com.gokecms.model;

import java.util.List;

public class ApiDataResponse {
	private Client client;
	private List<ClientFileResponse> documents;
	
	public ApiDataResponse(){
		
	}
	
	public ApiDataResponse(Client client, List<ClientFileResponse> documents){
		this.client = client;
		this.documents = documents;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ClientFileResponse> getDocuments() {
		return documents;
	}

	public void setDocuments(List<ClientFileResponse> documents) {
		this.documents = documents;
	}
	
}
