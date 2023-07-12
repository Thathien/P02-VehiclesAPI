package com.udacity.pricing.repository;

import org.springframework.stereotype.Repository;

import com.udacity.pricing.entity.Price;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {

}
