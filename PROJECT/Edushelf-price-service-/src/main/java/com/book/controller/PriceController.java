package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.dto.PriceRequest;
import com.book.dto.PriceResponse;
import com.book.services.PriceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/price")
public class PriceController {
	@Autowired
	private PriceService priceService;

	@PostMapping
	public ResponseEntity<PriceResponse> addPrice(@RequestBody @Valid PriceRequest request) {
		return new ResponseEntity<>(priceService.addPrice(request), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PriceResponse> updatePrice(@PathVariable Long id, @RequestBody @Valid PriceRequest request) {
		return ResponseEntity.ok(priceService.updatePrice(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePrice(@PathVariable Long id) {
		priceService.deletePrice(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{productId}")
	public ResponseEntity<List<PriceResponse>> getPricesByProduct(@PathVariable Long productId) {
		return ResponseEntity.ok(priceService.getPricesByProduct(productId));
	}

//	@GetMapping("/dynamic")
//	public ResponseEntity<PriceResponse> getDynamicPrice(@RequestParam Long productId, @RequestParam String sku,
//			@RequestParam(required = false) String customerGroup, @RequestParam(required = false) String country,
//			@RequestParam String currency) {
//		return ResponseEntity.ok(priceService.getDynamicPrice(productId, sku, customerGroup, country, currency));
//	}

	@GetMapping("/SKU/{sku}")
	public ResponseEntity<PriceResponse> findPriceBySku(@PathVariable String sku) {
		return ResponseEntity.ok(priceService.findPriceBySku(sku));
	}
}
