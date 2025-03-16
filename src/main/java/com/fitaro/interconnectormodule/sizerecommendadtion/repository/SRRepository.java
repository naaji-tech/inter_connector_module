package com.fitaro.interconnectormodule.sizerecommendadtion.repository;

import com.fitaro.interconnectormodule.sizerecommendadtion.model.MeasurementsWeight;

public interface SRRepository {
    MeasurementsWeight getMeasurementWeights(String productId);
}
