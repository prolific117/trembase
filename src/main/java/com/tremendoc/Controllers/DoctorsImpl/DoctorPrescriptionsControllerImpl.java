/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.DoctorsImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Doctors.DoctorPrescriptionsController;
import com.tremendoc.Entity.Consultation;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.Prescriptions;
import com.tremendoc.Entity.Repository.ConsultationsRepository;
import com.tremendoc.Entity.Repository.CustomerLanguagesRepository;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.DoctorRepository;
import com.tremendoc.Entity.Repository.PrescriptionsRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.model.ApplicationUser;
import com.tremendoc.response.CustomerPrescriptionsResponse;
import com.tremendoc.response.PrescriptionData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class DoctorPrescriptionsControllerImpl implements DoctorPrescriptionsController {

    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired 
    private DoctorRepository doctorRepository;
    
    @Autowired 
    private PrescriptionsRepository prescriptionsRepository;
    
    @Autowired 
    private ConsultationsRepository consultationsRepository;
   
    @Override
    public ServiceResponse addPrescription(String medication, String dosage, String startDate, String endDate, String specialInstruction, String doctorReason, Long patientId, Long consultationId, SessionDetail sessionDetail) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        Customer customer = customerRepository.getById(patientId);
        
        if(customer == null){
            response.setDescription("User does not exist, Cannot create Prescription");
            return response;
        }
        
        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
        Doctor doctor = doctorRepository.findByDoctorId(user.getId());
        
        if(doctor == null){
            response.setDescription("Doctor does not exist, Cannot create Prescription");
            return response;
        }
        
        Consultation consultation = consultationsRepository.getById(consultationId);
        
        if(consultation == null){
            response.setDescription("Consultation does not exist, Cannot create Prescription");
            return response;
        }
        
        Prescriptions prescription = new Prescriptions();
        prescription.setDoctor(doctor);
        prescription.setPatient(customer);
        prescription.setDoctorReason(doctorReason);
        prescription.setDosage(dosage);
        prescription.setStartDate(startDate);
        prescription.setEndDate(endDate);
        prescription.setMedication(medication);
        prescription.setConsultation(consultation);
        prescription.setSpecialInstruction(specialInstruction);
        
        prescriptionsRepository.save(prescription);
        
        response.setCode(ServiceResponse.SUCCESS);
        return response;
    }

    @Override
    public CustomerPrescriptionsResponse getPrescriptions(Long patientId) {
        CustomerPrescriptionsResponse response = new CustomerPrescriptionsResponse(ServiceResponse.SUCCESS);
        
        Customer customer = customerRepository.getById(patientId);
        
        if(customer == null){
            response.setDescription("User does not exist, Cannot create Prescription");
            return response;
        }
        
        List<Prescriptions> prescriptions = prescriptionsRepository.findByPatient(customer);
        List<PrescriptionData> prescriptionData = new ArrayList<>();
        
        if(!prescriptions.isEmpty()){
            for(Prescriptions prescription : prescriptions){
                PrescriptionData data = new PrescriptionData();
                //data.setDoctorImage(null);
                data.setDoctorName(prescription.getDoctor().getFirstname()+" "+prescription.getDoctor().getLastName());
                data.setDoctorReason(prescription.getDoctorReason());
                data.setDosage(prescription.getDosage());
                data.setEndDate(prescription.getEndDate());
                data.setMedication(prescription.getMedication());
                data.setSpecialInstruction(prescription.getSpecialInstruction());
                data.setStartDate(prescription.getStartDate());
                
                prescriptionData.add(data);
            }
        }
        
        response.setCode(ServiceResponse.SUCCESS);
        response.setPrescriptionData(prescriptionData);
        
        return response;
    }
    
}
