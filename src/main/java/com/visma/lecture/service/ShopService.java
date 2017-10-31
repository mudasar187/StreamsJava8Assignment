package com.visma.lecture.service;

import com.visma.lecture.repository.ShopRepository;




/**
 * Service class for shop
 *
 * @author Leo-Andreas Ervik
 */
public class ShopService {
	
	private final ShopRepository shopRepository;
	
	public ShopService(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

}
