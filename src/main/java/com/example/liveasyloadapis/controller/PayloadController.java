package com.example.liveasyloadapis.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.liveasyloadapis.exception.ResourceNotFoundexception;
import com.example.liveasyloadapis.model.Payload;
import com.example.liveasyloadapis.repository.PayloadRepository;


@RestController
public class PayloadController {
	
	@Autowired
	
	private PayloadRepository payloadRepository;
	
	@GetMapping("load")
	public List<Payload> getAllPayloads(){
		return payloadRepository.findAll();
	}
	
	@GetMapping("load/{id}")
	public ResourceEntity<Payload> getPayloadById(@PathVariable(value = "id") Long shipperId)
			throws ResourceNotFoundexception {
		Payload Payload = payloadRepository.findById(shipperId)
				.orElseThrow(() -> new ResourceNotFoundException("Payload not found for this id :: " +shipperId));
		return ResponseEntity.ok().body(Payload);
	}
	@PostMapping("load")
	public ResponseEntity<Payload> updatePayload(@PathVariable(value = "id") Long shipperId,
			 @RequestBody Payload PayloadDetails) throws ResourceNotFoundException {
			Payload payload = payloadRepository.findById(shipperId)
					.orElseThrow();

			payload.setLoadingPoint(PayloadDetails.getLoadingPoint());
			payload.setUnloadingPoint(PayloadDetails.getUnloadingPoint());
			payload.setProductType(PayloadDetails.getProductType());
			payload.setTruckType(PayloadDetails.getTruckType());
			payload.setNoOfTrucks(PayloadDetails.getNoOfTrucks());
			payload.setWeight(PayloadDetails.getWeight());
			payload.setComment(PayloadDetails.getComment());
			payload.setDate(PayloadDetails.getDate());
			final Payload updatedPayload = payloadRepository.save(payload);
			return ResponseEntity.ok(updatedPayload);
		}
	@DeleteMapping("load/{id}")
	public Map<String, Boolean> deletePayload(@PathVariable(value = "id") Long shipperId)
			throws ResourceNotFoundException {
		Payload Payload = payloadRepository.findById(shipperId)
				.orElseThrow();

		payloadRepository.delete(Payload);
		Map<String, Boolean> response = new  HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
