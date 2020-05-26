package com.cb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Reference;
import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
	@Id
	private String cId;

	@Field
	private String phoneNumber;

	@Field
	private String[] roles;

	@Field
	private List<MlcCard> mlcCards;


}
