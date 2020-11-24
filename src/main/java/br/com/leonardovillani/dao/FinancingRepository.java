package br.com.leonardovillani.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.leonardovillani.model.Financing;

public class FinancingRepository {

	private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

	public Financing save(final Financing Financing) {
		mapper.save(Financing);
		return Financing;
	}

	public List<Financing> findByClient(final String client) {

		final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(client));
		

		final DynamoDBQueryExpression<Financing> queryExpression = new DynamoDBQueryExpression<Financing>()
				.withKeyConditionExpression("client = :val1").withExpressionAttributeValues(eav);

		final List<Financing> financing = mapper.query(Financing.class, queryExpression);

		return financing;
	}

	
}