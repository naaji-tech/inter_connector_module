package com.fitaro.interconnectormodule.sizerecommendadtion.service;

import com.fitaro.interconnectormodule.sizerecommendadtion.repository.SRRepository;
import com.fitaro.interconnectormodule.util.Error;
import com.fitaro.interconnectormodule.util.ResHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SRServiceImpl implements SRService{
    private SRRepository srRepository;

    public SRServiceImpl(SRRepository srRepository) {
        this.srRepository = srRepository;
    }

    @Override
    public ResponseEntity<Object> getSizeRecommendationByMeasureMe(String username) {
        try {

            return ResHandler.success("Get size recommendation by measure me success", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getSizeRecommendationByOldMeasurements(String username) {
        try {

            return ResHandler.success("Get size recommendation by old measurements success", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
