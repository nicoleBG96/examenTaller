package com.ucbcba.taller.services;

import com.ucbcba.taller.entities.LevelPrice;
import com.ucbcba.taller.repositories.LevelPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LevelPriceServiceImpl implements LevelPriceService {
    private LevelPriceRepository levelPriceRepository;

    @Autowired
    @Qualifier(value="levelPriceRepository")
    public void setLevelriceRepository(LevelPriceRepository levelriceRepository){this.levelPriceRepository=levelriceRepository;}


    @Override
    public Iterable<LevelPrice> listAllLevelsPrices() {
        return levelPriceRepository.findAll();
    }

    @Override
    public void saveLevelPrice(LevelPrice levelPrice) {
        levelPriceRepository.save(levelPrice);
    }

    @Override
    public LevelPrice getLevelPrice(Integer id) {
        return levelPriceRepository.findOne(id);
    }

    @Override
    public void deleteLevelPrice(Integer id) {
        levelPriceRepository.delete(id);
    }
}
