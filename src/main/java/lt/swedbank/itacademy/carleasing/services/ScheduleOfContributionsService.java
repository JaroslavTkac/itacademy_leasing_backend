package lt.swedbank.itacademy.carleasing.services;


import lt.swedbank.itacademy.carleasing.beans.documents.ScheduleOfContributions;
import lt.swedbank.itacademy.carleasing.beans.documents.ScheduleOfContributionsPaymentData;
import lt.swedbank.itacademy.carleasing.beans.responses.ScheduleOfContributionsResponse;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleOfContributionsService {

    public ScheduleOfContributionsResponse calculateScheduleOfContributions(@Valid ScheduleOfContributions schedule) {
        List<ScheduleOfContributionsPaymentData> paymentData = new ArrayList<>();

        paymentData.add(new ScheduleOfContributionsPaymentData(
                schedule.getAssetPrice()
                        .setScale(2, RoundingMode.HALF_UP),
                schedule.getAdvancePaymentAmount()
                        .setScale(2, RoundingMode.HALF_UP),
                BigDecimal.ZERO,
                (schedule.getAdvancePaymentAmount().add(schedule.getContractFee()))
                        .setScale(2, RoundingMode.HALF_UP)
        ));

        BigDecimal notRedeemedAssetValue = schedule.getAssetPrice().subtract(schedule.getAdvancePaymentAmount());
        BigDecimal r = new BigDecimal((schedule.getMargin() / 100) / 12)
                .round(new MathContext(16, RoundingMode.HALF_UP));

                                 // 1 - (1 / (1 + r)^m)
        BigDecimal denominator = (BigDecimal.ONE.subtract
                // (1 / (1 + r)^m)
                (BigDecimal.ONE
                        .divide(
                                // (1 + r)^m
                                (
                                    ((BigDecimal.ONE
                                            .add(r)
                                    )
                                            .pow(schedule.getLeasePeriod())
                                    )
                                            .round(new MathContext(16, RoundingMode.HALF_UP))
                                ),
                        8, RoundingMode.HALF_UP)
                        .round(new MathContext(16, RoundingMode.HALF_UP))
                ))
                // ( 1 - (1 / (1 + r)^m) ) / r
                .divide(r, 16, RoundingMode.HALF_UP);


        BigDecimal totalMonthlyPaymentValue = notRedeemedAssetValue.divide(denominator, 16, RoundingMode.HALF_UP);
        BigDecimal interest = BigDecimal.ONE.add
                (
                        new BigDecimal((schedule.getMargin() / 100) / 12)
                )
                .round(new MathContext(16, RoundingMode.HALF_UP));


        BigDecimal paymentWithInterest = notRedeemedAssetValue.multiply(interest)
                .round(new MathContext(16, RoundingMode.HALF_UP));

        BigDecimal interestPayments = paymentWithInterest.subtract(notRedeemedAssetValue);
        BigDecimal assetRedemptionFees = totalMonthlyPaymentValue.subtract(interestPayments);
        BigDecimal newNotRedeemedAssetValue = notRedeemedAssetValue.subtract(assetRedemptionFees);

        paymentData.add(new ScheduleOfContributionsPaymentData(
                notRedeemedAssetValue
                        .setScale(2, RoundingMode.HALF_UP),
                assetRedemptionFees
                        .setScale(2, RoundingMode.HALF_UP),
                interestPayments
                        .setScale(2, RoundingMode.HALF_UP),
                totalMonthlyPaymentValue
                        .setScale(2, RoundingMode.HALF_UP)
                ));

        for (int i = 0; i < schedule.getLeasePeriod() - 1; i++){
            notRedeemedAssetValue = newNotRedeemedAssetValue;

            interest = BigDecimal.ONE.add
                    (
                            new BigDecimal((schedule.getMargin() / 100) / 12)
                    )
                    .round(new MathContext(16, RoundingMode.HALF_UP));

            paymentWithInterest = notRedeemedAssetValue.multiply(interest)
                    .round(new MathContext(16, RoundingMode.HALF_UP));

            interestPayments = paymentWithInterest.subtract(notRedeemedAssetValue)
                    .round(new MathContext(16, RoundingMode.HALF_UP));

            assetRedemptionFees = totalMonthlyPaymentValue.subtract(interestPayments)
                    .round(new MathContext(16, RoundingMode.HALF_UP));

            newNotRedeemedAssetValue = notRedeemedAssetValue.subtract(assetRedemptionFees)
                    .round(new MathContext(16, RoundingMode.HALF_UP));

            paymentData.add(new ScheduleOfContributionsPaymentData(
                    notRedeemedAssetValue
                            .setScale(2, RoundingMode.HALF_UP),
                    assetRedemptionFees
                            .setScale(2, RoundingMode.HALF_UP),
                    interestPayments
                            .setScale(2, RoundingMode.HALF_UP),
                    totalMonthlyPaymentValue
                            .setScale(2, RoundingMode.HALF_UP)
            ));
        }

        setIndexesForSchedulePayments(paymentData);

        return new ScheduleOfContributionsResponse(paymentData);
    }


    public void setIndexesForSchedulePayments(List<ScheduleOfContributionsPaymentData> paymentData){
        int i = 1;
        for (ScheduleOfContributionsPaymentData payment: paymentData) {
            payment.setIndex(i);
            i++;
        }
    }

}
