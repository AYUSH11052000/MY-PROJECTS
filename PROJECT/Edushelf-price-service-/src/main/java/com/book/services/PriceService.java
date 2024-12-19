package com.book.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.dto.PriceRequest;
import com.book.dto.PriceResponse;
import com.book.entity.Price;
import com.book.repository.PriceRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PriceService {
	@Autowired
	private PriceRepository priceRepository;

	public PriceResponse addPrice(PriceRequest request) {
//		Optional<Price> existing = priceRepository.findDynamicPrice(request.getProductId(), request.getSku(),
//				request.getCustomerGroup(), request.getCountry(), request.getCurrency());
//		if (existing.isPresent()) {
//			throw new IllegalArgumentException("Duplicate price configuration");
//		}
		
		Price price = new Price();
		BeanUtils.copyProperties(request, price);// model mapper
		Price saved = priceRepository.save(price);
		return convertToResponse(saved);
	}

	public PriceResponse updatePrice(Long id, PriceRequest request) {
		Price price = priceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Price not found"));
		BeanUtils.copyProperties(request, price, "id");
		Price updated = priceRepository.save(price);
		return convertToResponse(updated);
	}

	public void deletePrice(Long id) {
		priceRepository.deleteById(id);
	}

	public List<PriceResponse> getPricesByProduct(Long productId) {
		return priceRepository.findByProductId(productId).stream().map(this::convertToResponse)
				.collect(Collectors.toList());
	}

//	public PriceResponse getDynamicPrice(Long productId, String sku, String customerGroup, String country,
//			String currency) {
//		return priceRepository.findDynamicPrice(productId, sku, customerGroup, country, currency)
//				.map(this::convertToResponse).orElseThrow(() -> new EntityNotFoundException("Price not found"));
//	}

	private PriceResponse convertToResponse(Price price) {
		PriceResponse response = new PriceResponse();
		BeanUtils.copyProperties(price, response);
		return response;
	}

	public PriceResponse findPriceBySku(String sku) {
		Price price = priceRepository.findFirstBySku(sku);
		return convertToResponse(price);
	}
}
