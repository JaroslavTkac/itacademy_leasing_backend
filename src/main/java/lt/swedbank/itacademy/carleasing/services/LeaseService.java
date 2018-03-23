package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.Lease;
import lt.swedbank.itacademy.carleasing.beans.responses.LeaseResponse;
import lt.swedbank.itacademy.carleasing.repositories.LeaseRepository;
import lt.swedbank.itacademy.carleasing.validations.LeaseValidations;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaseService extends lt.swedbank.itacademy.carleasing.services.Service{

    @Autowired
    private LeaseRepository repository;

    public List<LeaseResponse> getAllLeasings() {
        return repository.findAll().stream().map(LeaseResponse::new).collect(Collectors.toList());
    }

    public Lease addNewLeasing(Lease lease) {
        LeaseValidations validations = new LeaseValidations();
        errorCodes = new ArrayList<>();


        //errorCodes.add(validations.validateAssetType(lease.getAssetType()));
        //errorCodes.add(validations.validateAdvancePaymentAmount(lease.getAdvancePaymentAmount(), lease.getAdvancePaymentPercentage(),
        //                                                        lease.getAssetPrice()));
        //errorCodes.add(validations.validateContractFee(lease.getContractFee(), lease.getAssetPrice()));

        //Checking is there any errors
        List<Integer> actualErrors = validations.checkForActualError(errorCodes);

        if(actualErrors.size() == 0) {
            //if no errors
            //saving to DB
            return repository.save(initNewLeasing(lease, actualErrors));
        }
        else{
            //if have error, returning empty object with error codes
            return initNewLeasing(lease, actualErrors);
        }
    }


    private Lease initNewLeasing(Lease lease, List<Integer> errorCodes){
        Lease newLease = new Lease();

        newLease.setErrorCodes(errorCodes);
        newLease.setId(new ObjectId());
        if(newLease.getErrorCodes().size() == 0) {
            newLease.setAssetType(lease.getAssetType());
            newLease.setCarBrand(lease.getCarBrand());
            newLease.setCarModel(lease.getCarModel());
            newLease.setYears(lease.getYears());
            newLease.setEnginePower(lease.getEnginePower());
            newLease.setAssetPrice(lease.getAssetPrice());
            newLease.setAdvancePaymentPercentage(lease.getAdvancePaymentPercentage());
            newLease.setAdvancePaymentAmount(lease.getAdvancePaymentAmount());
            newLease.setLeasePeriod(lease.getLeasePeriod());
            newLease.setMargin(lease.getMargin());
            newLease.setContractFee(lease.getContractFee());
            newLease.setPaymentDate(lease.getPaymentDate());
        }
        else{
            newLease.setAssetType("");
            newLease.setCarBrand("");
            newLease.setCarModel("");
            newLease.setYears("");
            newLease.setEnginePower(0);
            newLease.setAssetPrice(BigDecimal.ZERO);
            newLease.setAdvancePaymentPercentage(0);
            newLease.setAdvancePaymentAmount(BigDecimal.ZERO);
            newLease.setLeasePeriod(0);
            newLease.setMargin(0);
            newLease.setContractFee(BigDecimal.ZERO);
            newLease.setPaymentDate(0);
        }

        return newLease;
    }

}
