package com.ucbcba.taller.repositories;

import com.ucbcba.taller.entities.LevelPrice;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface LevelPriceRepository extends CrudRepository<LevelPrice,Integer> {
}
