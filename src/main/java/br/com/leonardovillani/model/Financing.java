package br.com.leonardovillani.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data @AllArgsConstructor @NoArgsConstructor
@DynamoDBTable(tableName = "financing")
public class Financing {

	@DynamoDBHashKey(attributeName = "client")
	private String client;

	@DynamoDBAttribute(attributeName = "entry")
	private Double entry;

	@DynamoDBRangeKey(attributeName = "dateTimeStart")
	private String dateTimeStart;

	@DynamoDBIndexRangeKey(attributeName = "term",  localSecondaryIndexName = "termIndex")
	private Integer term;
	
	@DynamoDBAttribute(attributeName = "installment")
	private Double installment;

}
