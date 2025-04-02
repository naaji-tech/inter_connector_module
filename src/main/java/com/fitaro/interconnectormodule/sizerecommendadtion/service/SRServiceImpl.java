package com.fitaro.interconnectormodule.sizerecommendadtion.service;

import com.fitaro.interconnectormodule.client.SizeRecommendationClient;
import com.fitaro.interconnectormodule.client.UserMeasurementClient;
import com.fitaro.interconnectormodule.productmeasurement.model.ProductMeasurement;
import com.fitaro.interconnectormodule.productmeasurement.repository.PMRepository;
import com.fitaro.interconnectormodule.sizerecommendadtion.model.MeasurementsWeight;
import com.fitaro.interconnectormodule.sizerecommendadtion.model.ProductSize;
import com.fitaro.interconnectormodule.sizerecommendadtion.model.RecommendSizeDTO;
import com.fitaro.interconnectormodule.sizerecommendadtion.model.SizeRecommendation;
import com.fitaro.interconnectormodule.sizerecommendadtion.repository.SRRepository;
import com.fitaro.interconnectormodule.user.repository.UserRepository;
import com.fitaro.interconnectormodule.usermeasurement.model.UserMeasurement;
import com.fitaro.interconnectormodule.usermeasurement.repository.UMRepository;
import com.fitaro.interconnectormodule.util.Error;
import com.fitaro.interconnectormodule.util.ResHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SRServiceImpl implements SRService{
    private final SRRepository srRepository;
    private final UserRepository userRepository;
    private final PMRepository pmRepository;
    private final UserMeasurementClient umClient;
    private final SizeRecommendationClient srClient;
    private final UMRepository umRepository;

    @Autowired
    public SRServiceImpl(
            @Qualifier(value = "srPostgresql") SRRepository srRepository,
            UserRepository userRepository,
            @Qualifier(value = "pmPostgresql") PMRepository pmRepository,
            UserMeasurementClient umClient,
            SizeRecommendationClient srClient,
            @Qualifier(value = "umPostgresql") UMRepository umRepository
    ) {
        this.srRepository = srRepository;
        this.userRepository = userRepository;
        this.pmRepository = pmRepository;
        this.umClient = umClient;
        this.srClient = srClient;
        this.umRepository = umRepository;
    }

    @Override
    public ResponseEntity<Object> getSizeRecommendationByMeasureMe(String username, String productId, MultipartFile usrImage, float usrHeight) {
        try {
            if (usrImage.isEmpty())
                return ResHandler.error(Error.USER_IMAGE_MISSING, HttpStatus.BAD_REQUEST);

            List<ProductMeasurement> pmList = pmRepository.getProductMeasurements(productId);
            if (pmList.isEmpty())
                return ResHandler.error(Error.PRODUCT_MEASUREMENTS_NOT_FOUND, HttpStatus.NOT_FOUND);

            MeasurementsWeight measurementsWeights = srRepository.getMeasurementWeights(productId);
            if (measurementsWeights == null)
                return ResHandler.error(Error.MEASUREMENT_WEIGHT_NOT_FOUND, HttpStatus.NOT_FOUND);

            int userId = userRepository.valUsername(username);
            if (userId == Error.USERNAME_NOT_FOUND.getValue())
                return ResHandler.error(Error.USERNAME_NOT_FOUND, HttpStatus.NOT_FOUND);

            // calling user body measurement model
            UserMeasurement userMeasures = umClient.getUserMeasurement(usrImage, usrHeight);

            int resCode = umRepository.addNewUserMeasurements(userId, userMeasures);
            if (resCode < 1)
                return ResHandler.error(Error.ADD_NEW_USER_MEASUREMENT_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

            // mapping user measurements and product measurements for size recommendation module
            SizeRecommendation srRequestBody = new SizeRecommendation();
            ProductSize productSize = new ProductSize();
            ProductMeasurement productMeasurement = new ProductMeasurement();

            productMeasurement.setChest(0);
            productMeasurement.setWaist(0);
            productMeasurement.setShoulderWidth(0);
            productMeasurement.setWaist(0);
            productMeasurement.setSleeveLength(0);
            productMeasurement.setBottomCircumference(0);
            productMeasurement.setFrontLength(0);
            productMeasurement.setSleeve(0);

            productMeasurement.setProductSize("XS");
            productSize.setXs(productMeasurement);

            productMeasurement.setProductSize("S");
            productSize.setS(productMeasurement);

            productMeasurement.setProductSize("M");
            productSize.setM(productMeasurement);

            productMeasurement.setProductSize("L");
            productSize.setL(productMeasurement);

            productMeasurement.setProductSize("XL");
            productSize.setXl(productMeasurement);

            srRequestBody.setUserMeasurement(userMeasures);
            for (ProductMeasurement pm : pmList) {
                switch (pm.getProductSize()) {
                    case "XS":
                        productSize.setXs(pm);
                        srRequestBody.setProductMeasurement(productSize);
                        break;
                    case "S":
                        productSize.setS(pm);
                        srRequestBody.setProductMeasurement(productSize);
                        break;
                    case "M":
                        productSize.setM(pm);
                        srRequestBody.setProductMeasurement(productSize);
                        break;
                    case "L":
                        productSize.setL(pm);
                        srRequestBody.setProductMeasurement(productSize);
                        break;
                    case "XL":
                        productSize.setXl(pm);
                        srRequestBody.setProductMeasurement(productSize);
                        break;
                }
            }
            srRequestBody.setMeasurementsWeight(measurementsWeights);
            System.out.println(srRequestBody);

            // calling size recommendation module
            RecommendSizeDTO responseFromSRClient = srClient.getSizeRecommendation(srRequestBody);

            return ResHandler.success("Get size recommendation by measure me success", responseFromSRClient, HttpStatus.OK);

        } catch (IOException e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.UM_CLIENT_CONNECTION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getSizeRecommendationByOldMeasurements(String username, String productId) {
        try {
            List<ProductMeasurement> pmList = pmRepository.getProductMeasurements(productId);
            if (pmList.isEmpty())
                return ResHandler.error(Error.PRODUCT_MEASUREMENTS_NOT_FOUND, HttpStatus.NOT_FOUND);

            System.out.println("product list : " + pmList);

            MeasurementsWeight measurementsWeights = srRepository.getMeasurementWeights(productId);
            if (measurementsWeights == null)
                return ResHandler.error(Error.MEASUREMENT_WEIGHT_NOT_FOUND, HttpStatus.NOT_FOUND);

            int userId = userRepository.valUsername(username);
            if (userId == Error.USERNAME_NOT_FOUND.getValue())
                return ResHandler.error(Error.USERNAME_NOT_FOUND, HttpStatus.NOT_FOUND);

            UserMeasurement userMeasures = umRepository.getUserMeasurements(userId);

            // mapping user measurements and product measurements for size recommendation module
            SizeRecommendation srRequestBody = new SizeRecommendation();
            ProductSize productSize = new ProductSize();
            ProductMeasurement productMeasurement = new ProductMeasurement();

            productMeasurement.setChest(0);
            productMeasurement.setWaist(0);
            productMeasurement.setShoulderWidth(0);
            productMeasurement.setWaist(0);
            productMeasurement.setSleeveLength(0);
            productMeasurement.setBottomCircumference(0);
            productMeasurement.setFrontLength(0);
            productMeasurement.setSleeve(0);

            productMeasurement.setProductSize("XS");
            productSize.setXs(productMeasurement);

            productMeasurement.setProductSize("S");
            productSize.setS(productMeasurement);

            productMeasurement.setProductSize("M");
            productSize.setM(productMeasurement);

            productMeasurement.setProductSize("L");
            productSize.setL(productMeasurement);

            productMeasurement.setProductSize("XL");
            productSize.setXl(productMeasurement);

            srRequestBody.setUserMeasurement(userMeasures);
            for (ProductMeasurement pm : pmList) {
                switch (pm.getProductSize()) {
                    case "XS":
                        productSize.setXs(pm);
                        srRequestBody.setProductMeasurement(productSize);
                        break;
                    case "S":
                        productSize.setS(pm);
                        srRequestBody.setProductMeasurement(productSize);
                        break;
                    case "M":
                        productSize.setM(pm);
                        srRequestBody.setProductMeasurement(productSize);
                        break;
                    case "L":
                        productSize.setL(pm);
                        srRequestBody.setProductMeasurement(productSize);
                        break;
                    case "XL":
                        productSize.setXl(pm);
                        srRequestBody.setProductMeasurement(productSize);
                        break;
                }
            }
            srRequestBody.setMeasurementsWeight(measurementsWeights);
            System.out.println("request body: " + srRequestBody);

            // calling size recommendation module
            RecommendSizeDTO responseFromSRClient = srClient.getSizeRecommendation(srRequestBody);

            return ResHandler.success("Get size recommendation by old measurements success", responseFromSRClient, HttpStatus.OK);

        } catch (IOException e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.UM_CLIENT_CONNECTION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
