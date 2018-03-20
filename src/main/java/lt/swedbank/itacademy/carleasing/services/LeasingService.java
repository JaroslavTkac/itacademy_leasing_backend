package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.Leasing;
import lt.swedbank.itacademy.carleasing.beans.responses.LeasingResponse;
import lt.swedbank.itacademy.carleasing.repositories.LeasingRepository;
import lt.swedbank.itacademy.carleasing.validations.LeasingValidations;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeasingService {

    @Autowired
    private LeasingRepository repository;

    private List<Integer> errorCodes;

    private LeasingValidations validations;

    public List<LeasingResponse> getAllLeasings() {
        return repository.findAll().stream().map(LeasingResponse::new).collect(Collectors.toList());
    }

    public Leasing addNewLeasing(Leasing leasing) {
        validations = new LeasingValidations();
        errorCodes = new ArrayList<>();


        //TODO validatorius
        //errorCodes.add(validations.validateAssetType(leasing.getAssetType()));

        List<Integer> actualErrors = checkForActualError(errorCodes);
        if(actualErrors.size() == 0) {
            return repository.save(initNewLeasing(leasing, actualErrors));
        }
        else{
            return initNewLeasing(leasing, actualErrors);
        }
    }

    private List<Integer> checkForActualError(List<Integer> errorCodes){
        List<Integer> errors = new ArrayList<>();

        for (Integer error: errorCodes) {
            if(error != 0){
                errors.add(error);
            }
        }

        return errors;
    }

    private Leasing initNewLeasing(Leasing leasing, List<Integer> errorCodes){
        Leasing newLeasing = new Leasing();

        newLeasing.setErrorCodes(errorCodes);
        newLeasing.setId(new ObjectId());
        if(newLeasing.getErrorCodes().size() == 0) {
            newLeasing.setAssetType(leasing.getAssetType());
            newLeasing.setCarBrand(leasing.getCarBrand());
            newLeasing.setCarModel(leasing.getCarModel());
            newLeasing.setYears(leasing.getYears());
            newLeasing.setEnginePower(leasing.getEnginePower());
            newLeasing.setAssetPrice(leasing.getAssetPrice());
            newLeasing.setAdvancePaymentPercentage(leasing.getAdvancePaymentPercentage());
            newLeasing.setAdvancePaymentAmount(leasing.getAdvancePaymentAmount());
            newLeasing.setLeasePeriod(leasing.getLeasePeriod());
            newLeasing.setMargin(leasing.getMargin());
            newLeasing.setContractFee(leasing.getContractFee());
            newLeasing.setPaymentDate(leasing.getPaymentDate());
        }
        else{
            newLeasing.setAssetType("");
            newLeasing.setCarBrand("");
            newLeasing.setCarModel("");
            newLeasing.setYears("");
            newLeasing.setEnginePower(0);
            newLeasing.setAssetPrice(BigDecimal.ZERO);
            newLeasing.setAdvancePaymentPercentage(0);
            newLeasing.setAdvancePaymentAmount(BigDecimal.ZERO);
            newLeasing.setLeasePeriod(0);
            newLeasing.setMargin(0);
            newLeasing.setContractFee(BigDecimal.ZERO);
            newLeasing.setPaymentDate(0);
        }

        return newLeasing;
    }


}
