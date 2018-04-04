package lt.swedbank.itacademy.carleasing.beans.responses;

import lt.swedbank.itacademy.carleasing.beans.documents.ScheduleOfContributionsPaymentData;

import java.util.List;

public class ScheduleOfContributionsResponse extends Response {


    private List<ScheduleOfContributionsPaymentData> paymentData;


    public ScheduleOfContributionsResponse(List<ScheduleOfContributionsPaymentData> paymentData) {
        setPaymentData(paymentData);
    }

    public List<ScheduleOfContributionsPaymentData> getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(List<ScheduleOfContributionsPaymentData> paymentData) {
        this.paymentData = paymentData;
    }
}
