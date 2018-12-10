/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.DoctorsImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.tremendoc.Controllers.Doctors.DoctorNotesController;
import com.tremendoc.Entity.Consultation;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.DoctorNotes;
import com.tremendoc.Entity.Prescriptions;
import com.tremendoc.Entity.Repository.ConsultationsRepository;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.DoctorNotesRepository;
import com.tremendoc.Entity.Repository.DoctorRepository;
import com.tremendoc.Entity.Repository.PrescriptionsRepository;
import com.tremendoc.Request.SessionDetail;
import com.tremendoc.model.ApplicationUser;
import com.tremendoc.response.CustomerNotesResponse;
import com.tremendoc.response.CustomerPrescriptionsResponse;
import com.tremendoc.response.DoctorNotesData;
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
public class DoctorNotesControllerImpl implements DoctorNotesController{
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired 
    private DoctorRepository doctorRepository;
    
    @Autowired 
    private DoctorNotesRepository doctorNotesRepository;
    
    @Autowired 
    private ConsultationsRepository consultationsRepository;

    @Override
    public ServiceResponse addNote(String symptoms, String diagnosis, String treatment, Long patientId, Long consultationId, SessionDetail sessionDetail) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        Customer customer = customerRepository.getById(patientId);
        
        if(customer == null){
            response.setDescription("User does not exist, Cannot create Note");
            return response;
        }
        
        ApplicationUser user = (ApplicationUser) sessionDetail.getPrincipal();
        Doctor doctor = doctorRepository.findByDoctorId(user.getId());
        
        if(doctor == null){
            response.setDescription("Doctor does not exist, Cannot create Note");
            return response;
        }
        
        Consultation consultation = consultationsRepository.getById(consultationId);
        
        if(consultation == null){
            response.setDescription("Consultation does not exist, Cannot create Note");
            return response;
        }
        
        DoctorNotes note = new DoctorNotes();
        note.setDoctor(doctor);
        note.setPatient(customer);
        note.setConsultation(consultation);
        note.setDiagnosis(diagnosis);
        note.setSymptoms(symptoms);
        note.setTreatment(treatment);
        
        doctorNotesRepository.save(note);
        
        response.setCode(ServiceResponse.SUCCESS);
        return response;}

    @Override
    public CustomerNotesResponse getNotes(Long patientId) {
            CustomerNotesResponse response = new CustomerNotesResponse(ServiceResponse.SUCCESS);

            Customer customer = customerRepository.getById(patientId);

            if(customer == null){
                response.setDescription("User does not exist, Cannot create Prescription");
                return response;
            }

            List<DoctorNotes> notes = doctorNotesRepository.findByPatient(customer);
            List<DoctorNotesData> notesData = new ArrayList<>();

            if(!notes.isEmpty()){
                for(DoctorNotes note : notes){
                    DoctorNotesData data = new DoctorNotesData();
                    //data.setDoctorImage(null);
                    data.setDoctorName(note.getDoctor().getFirstname()+" "+note.getDoctor().getLastName());
                    data.setDiagnosis(note.getDiagnosis());
                    data.setSymptoms(note.getSymptoms());
                    data.setTreatment(note.getTreatment());

                    notesData.add(data);
                }
            }

            response.setCode(ServiceResponse.SUCCESS);
            response.setNotes(notesData);

            return response;
    }
    
}
