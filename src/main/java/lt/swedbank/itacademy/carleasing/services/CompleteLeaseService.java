package lt.swedbank.itacademy.carleasing.services;

import lt.swedbank.itacademy.carleasing.beans.documents.CompleteLease;
import lt.swedbank.itacademy.carleasing.beans.responses.CompleteLeaseResponse;
import lt.swedbank.itacademy.carleasing.mail.EmailService;
import lt.swedbank.itacademy.carleasing.mail.Mail;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CompleteLeaseService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private CorporateCustomerService corporateCustomerService;

    @Autowired
    private PrivateCustomerService privateCustomerService;

    public CompleteLeaseResponse addNewCompleteLease(CompleteLease lease) {
        ObjectId leaseId = new ObjectId();

        lease.getLease().setId(leaseId);
        leaseService.addNewLease(lease.getLease());

        if(lease.getLease().getLeaseType().equals("Private")){
            lease.getPrivateCustomer().setLeaseId(leaseId);
            lease.getPrivateCustomer().setId(new ObjectId());
            privateCustomerService.addNewPrivateCustomer(lease.getPrivateCustomer());
            sendEmail(lease.getPrivateCustomer().getEmail(), String.valueOf(leaseId));
        }
        if(lease.getLease().getLeaseType().equals("Corporate")){
            lease.getCorporateCustomer().setLeaseId(leaseId);
            lease.getCorporateCustomer().setId(new ObjectId());
            corporateCustomerService.addNewCorporateCustomer(lease.getCorporateCustomer());
            sendEmail(lease.getCorporateCustomer().getEmail(), String.valueOf(leaseId));
        }

        return new CompleteLeaseResponse(lease);
    }

    private void sendEmail(String userEmail, String leaseId){
        Mail mail = new Mail();
        mail.setFrom("majestic.car.leasing@gmail.com");
        mail.setTo(userEmail);
        mail.setSubject("Majestic car lease ID");
        mail.setContent("Your lease ID: " + leaseId + "\nWith this ID you can information and status of your lease.\n\nSincerely, Majestic Leasing");

        emailService.sendSimpleMessage(mail);
    }



}
