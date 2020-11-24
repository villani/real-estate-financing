package br.com.leonardovillani.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.leonardovillani.dao.FinancingRepository;
import br.com.leonardovillani.model.HandlerRequest;
import br.com.leonardovillani.model.HandlerResponse;
import br.com.leonardovillani.model.Financing;

public class GetFinancingRecordsByClient implements RequestHandler<HandlerRequest, HandlerResponse> {

	private final FinancingRepository repository = new FinancingRepository();

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {
		
		final String client = request.getPathParameters().get("client");

		context.getLogger()
				.log("Searching for all financing from client " + client);
		final List<Financing> allFinancing = this.repository.findByClient(client);

		if (allFinancing == null || allFinancing.isEmpty()) {
			return HandlerResponse.builder().setStatusCode(404).build();
		}

		return HandlerResponse.builder().setStatusCode(200).setObjectBody(allFinancing).build();
	}
}