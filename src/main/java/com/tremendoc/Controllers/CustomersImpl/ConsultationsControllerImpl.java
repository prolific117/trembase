/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tremendoc.Controllers.CustomersImpl;

import com.proxy.leanstack.commons.client.vo.ServiceResponse;
import com.proxy.leanstack.commons.util.RequestUtils;
import com.tremendoc.Controllers.Customers.ConsultationsController;
import com.tremendoc.Entity.Consultation;
import com.tremendoc.Entity.Customer;
import com.tremendoc.Entity.Doctor;
import com.tremendoc.Entity.Repository.ConsultationsRepository;
import com.tremendoc.Entity.Repository.CustomerRepository;
import com.tremendoc.Entity.Repository.DoctorRepository;
import com.tremendoc.Entity.Repository.SpecialtyRepository;
import com.tremendoc.Entity.Specialty;
import com.tremendoc.model.ApplicationUser;
import com.tremendoc.response.ConsultationsData;
import com.tremendoc.response.ConsultationsResponse;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author olatunji.oduro
 */
@Service
public class ConsultationsControllerImpl implements ConsultationsController{
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired 
    private DoctorRepository doctorRepository;
    
    @Autowired
    ConsultationsRepository consultationRepository;
    
    @Autowired
    SpecialtyRepository specialtyRepository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);

    @Override
    public ServiceResponse createConsultation(Long patientId, Long doctorId, Long specialtyId, String consultationType, String paymentType) {
        ServiceResponse response = new ServiceResponse(ServiceResponse.ERROR);
        
        Customer customer = customerRepository.getById(patientId);
        
        if(customer == null){
            response.setDescription("User does not exist, Cannot create Consultation");
            return response;
        }
        
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        
        if(doctor == null){
            response.setDescription("Doctor does not exist, Cannot create Consultation");
            return response;
        }
        
        Specialty specialty = specialtyRepository.getById(specialtyId);
        
        if(specialty == null){
            response.setDescription("Specialty does not exist, Cannot create Consultation");
            return response;
        }
        
        Consultation consultation = new Consultation();
        consultation.setConsultationType(consultationType);
        consultation.setCustomer(customer);
        consultation.setDoctor(doctor);
        consultation.setSpecialty(specialty);
        consultation.setStatus("INITIATED");
        consultation.setPaymentMode(paymentType);
        
        consultationRepository.save(consultation);
        
        response.setCode(ServiceResponse.SUCCESS);
        return response;
    }

    @Override
    public ConsultationsResponse getConsultations(Long patientId, Long doctorId, Long specialtyId,
            String consultationType, String paymentMode, 
            String consultationStatus, Date beginning, Calendar ending, int start) {
       ConsultationsResponse response = new ConsultationsResponse(ServiceResponse.SUCCESS);
  
       String query = "";
       String search = "select c from Consultation c WHERE c.isActive = true";
       String count = "select count(*) from Consultation c WHERE c.isActive = true";
       
       if(patientId != null){
           Customer customer = customerRepository.getById(patientId);
           query += " and c.customer = "+customer.getId();
       }
       
       if(doctorId != null){
           Doctor doctor = doctorRepository.findByDoctorId(doctorId);
           query += " and c.doctor = "+doctor.getId();
       }
       
       if(specialtyId != null){
           Specialty specialty = specialtyRepository.getById(specialtyId);
           query += " and c.specialty = "+specialty.getId();
       }
       
       if(consultationType != null){
           query += " and c.consultationType = '"+consultationType+"'";
       }
       
       if(paymentMode != null){
           query += " and c.paymentMode = '"+paymentMode+"'";
       }
       
       if(consultationStatus != null){
           query += " and c.status = '"+consultationStatus+"'";
       }
       
       //do date filtering
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      
       if(beginning != null && ending != null){
           query += " and c.createDate >= '"+df.format(beginning)+"' and c.createDate < '"+df.format(ending.getTime())+"'";
       }
       
       start = (start - 1) * 20;
       
       search = search + query + " order by c.createDate desc";
       count = count + query;
       
       LOGGER.info("Query : "+search);
       LOGGER.info("Count : "+count);
       
       Query q = em.createQuery(search, Consultation.class);
       q.setFirstResult(start);
       q.setMaxResults(20);
       
       List<Consultation> consultations = q.getResultList();
       
       Query c = em.createQuery(count);
       Long countRes = (Long) c.getSingleResult();
       response.setTotalCount(countRes);
        
       response.setSizeOfCurrentList(consultations.size());
       
       try{
           Double value = Math.ceil(response.getTotalCount().doubleValue() / 20.0);
           response.setNoOfTotalPages(value.intValue());
       }catch(Exception ex){}
       
       List<ConsultationsData> refinedData= new ArrayList<>();
       
       for(Consultation consultation : consultations){
           ConsultationsData data = new ConsultationsData();
           
           data.setCustomerName(consultation.getCustomer().getFirstname()+" "+consultation.getCustomer().getLastName());
           data.setCustomerId(consultation.getCustomer().getId());
           data.setDoctorName(consultation.getDoctor().getFirstname()+" "+consultation.getDoctor().getLastName());
           data.setDoctorId(consultation.getDoctor().getId());
           data.setStartTime(consultation.getStartTime());
           data.setEndTime(consultation.getEndTime());
           data.setDoctorSpecialty(consultation.getSpecialty().getName());
           data.setConsultationStatus(consultation.getStatus());
           //data.setInvoiceId(consultation.getInvoice().getId());
           data.setInvoiceStatus(consultation.getStatus());
           data.setRating(consultation.getRating());
           data.setPaymentMode(consultation.getPaymentMode());
           data.setRemark(consultation.getRemark());
           data.setConsultationType(consultation.getConsultationType());
           data.setTimeInitiated(consultation.getCreateDate());
           data.setConsultationId(consultation.getId());
           
           refinedData.add(data);
       }
       
       response.setConsultations(refinedData);
       
       return response; 
    }
    

}
