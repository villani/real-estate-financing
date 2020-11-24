package br.com.leonardovillani.handler;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.leonardovillani.dao.FinancingRepository;
import br.com.leonardovillani.model.Financing;
import br.com.leonardovillani.model.HandlerRequest;
import br.com.leonardovillani.model.HandlerResponse;

public class CreateFinancingRecord implements RequestHandler<HandlerRequest, HandlerResponse> {
	
	private final FinancingRepository repository = new FinancingRepository();

	@Override
	public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {

		Financing Financing = null;
		try {
			Financing = new ObjectMapper().readValue(request.getBody(), Financing.class);
		} catch (IOException e) {
			return HandlerResponse.builder().setStatusCode(400).setRawBody("There is a error in your Financing!").build();
		}
		context.getLogger().log("Creating a new Financing record for the client " + Financing.getClient());
		final Financing FinancingRecorded = repository.save(Financing);
		return HandlerResponse.builder().setStatusCode(201).setObjectBody(FinancingRecorded).build();
	}
}