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

        System.out.println("IMOKA 1");
        System.out.println("----");

        BigDecimal notRedeemedAssetValue = schedule.getAssetPrice().subtract(schedule.getAdvancePaymentAmount());

        System.out.println("Not Redeemed Asset Value = " + notRedeemedAssetValue);

        BigDecimal r = new BigDecimal((schedule.getMargin() / 100) / 12)
                .round(new MathContext(16, RoundingMode.HALF_UP));

        //System.out.println("r: " + r);

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

        //System.out.println("denominator = " + denominator);

        BigDecimal totalMonthlyPaymentValue = notRedeemedAssetValue.divide(denominator, 16, RoundingMode.HALF_UP);

        System.out.println("menesine Imoka = " + totalMonthlyPaymentValue);

        BigDecimal interest = BigDecimal.ONE.add
                (
                        new BigDecimal((schedule.getMargin() / 100) / 12)
                )
                .round(new MathContext(16, RoundingMode.HALF_UP));

        //System.out.println("Interest = " + interest);

        BigDecimal paymentWithInterest = notRedeemedAssetValue.multiply(interest)
                .round(new MathContext(16, RoundingMode.HALF_UP));
        System.out.println("Su palukanomis = " + paymentWithInterest);



        BigDecimal interestPayments = paymentWithInterest.subtract(notRedeemedAssetValue);
        System.out.println("Menesine palukanu imoka = " + interestPayments);

        BigDecimal assetRedemptionFees = totalMonthlyPaymentValue.subtract(interestPayments);
        System.out.println("Turto ispirkimo imoka = " + assetRedemptionFees);

        BigDecimal newNotRedeemedAssetValue = notRedeemedAssetValue.subtract(assetRedemptionFees);
        System.out.println("Neispirkta turto verte = " + newNotRedeemedAssetValue);

        paymentData.add(new ScheduleOfContributionsPaymentData(
                notRedeemedAssetValue,
                assetRedemptionFees,
                interestPayments,
                totalMonthlyPaymentValue
                ));
        System.out.println("----");
        for (int i = 0; i < schedule.getLeasePeriod() - 1; i++){
            System.out.println("IMOKA " + (i + 2));
            System.out.println("----");

            notRedeemedAssetValue = newNotRedeemedAssetValue;
            System.out.println("Not Redeemed Asset Value = " + notRedeemedAssetValue);

            //totalMonthlyPaymentValue = notRedeemedAssetValue.divide(denominator, 8, RoundingMode.HALF_UP);

            System.out.println("menesine Imoka = " + totalMonthlyPaymentValue);

            interest = BigDecimal.ONE.add
                    (
                            new BigDecimal((schedule.getMargin() / 100) / 12)
                    )
                    .round(new MathContext(16, RoundingMode.HALF_UP));

            //System.out.println("Interest = " + interest);

            paymentWithInterest = notRedeemedAssetValue.multiply(interest)
                    .round(new MathContext(16, RoundingMode.HALF_UP));
            System.out.println("Su palukanomis = " + paymentWithInterest);


            interestPayments = paymentWithInterest.subtract(notRedeemedAssetValue)
                    .round(new MathContext(16, RoundingMode.HALF_UP));
            System.out.println("Menesine palukanu imoka = " + interestPayments);

            assetRedemptionFees = totalMonthlyPaymentValue.subtract(interestPayments)
                    .round(new MathContext(16, RoundingMode.HALF_UP));
            System.out.println("Turto ispirkimo imoka = " + assetRedemptionFees);

            newNotRedeemedAssetValue = notRedeemedAssetValue.subtract(assetRedemptionFees)
                    .round(new MathContext(16, RoundingMode.HALF_UP));
            System.out.println("Neispirkta turto verte = " + newNotRedeemedAssetValue);

            System.out.println("----");

            paymentData.add(new ScheduleOfContributionsPaymentData(
                    notRedeemedAssetValue,
                    assetRedemptionFees,
                    interestPayments,
                    totalMonthlyPaymentValue
            ));
        }

        System.out.println("Payment data size: " + paymentData.size());

        return new ScheduleOfContributionsResponse(paymentData);
    }


}
