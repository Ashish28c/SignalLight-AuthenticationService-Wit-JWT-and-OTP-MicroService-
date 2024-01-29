package com.SignalLight.AuthenticationService.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.SignalLight.AuthenticationService.Repository.OtpDao;
import com.SignalLight.AuthenticationService.entity.OtpMaster;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;


@Component
public class OtpService   {
	
	@Autowired
	private OtpDao otpDao;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	
	
	public Optional<OtpMaster> getbyid(int id) {
		
		return otpDao.findById(id);
		
	}
	
	public OtpMaster getIdByotp(int otp) {
		
		return otpDao.findByOtp(otp);
	}
	
	
	public OtpMaster getByMobileNumber(String mobilenumber) {
		
		return otpDao.findByMobileNumber(mobilenumber);
		
	}
	
	public int generateOTP(){
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return otp;
	} 
	
	
	
		
	public void generateUserOtp (String mobilenumber) {
		
		OtpMaster otp = new OtpMaster();
		otp.setMobileNumber(mobilenumber);
		otp.setOtp(generateOTP());
		otp.setCreateDateTime(new Date());
		otp.setStatus(true);
		otpDao.save(otp);
	}

	public Optional<OtpMaster> getOtpID(int id) {
		return otpDao.findById(id); 
	}
	
	@Transactional
	public void deletbyMobileNumber(String mobileNumber) {
		 otpDao.deleteByMobileNumber(mobileNumber);
	}
	
	public int validateMobilenumber(String p_mobilenumber) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("validate_mobilenumber");
        query.registerStoredProcedureParameter("p_mobilenumber", String.class, ParameterMode.IN);
        query.setParameter("p_mobilenumber", p_mobilenumber);
        query.registerStoredProcedureParameter("p_outval", Integer.class, ParameterMode.OUT);
        query.execute();
        Integer p_outval = (Integer) query.getOutputParameterValue("p_outval");
        return p_outval;
    }
	
	public int validatePremiseMobilenumber(String p_mobilenumber) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("CheckMobileNumberExists");
        query.registerStoredProcedureParameter("mobile_number", String.class, ParameterMode.IN);
        query.setParameter("mobile_number", p_mobilenumber);
        query.registerStoredProcedureParameter("result", Integer.class, ParameterMode.OUT);
        query.execute();
        Integer p_outval = (Integer) query.getOutputParameterValue("result");
        return p_outval;
    }
	
    public int validateOtp(int p_Otp, String p_mobilenumber) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("otpValidate");        
        query.registerStoredProcedureParameter("p_Otp", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_mobilenumber", String.class, ParameterMode.IN);
        query.setParameter("p_Otp", p_Otp);
        query.setParameter("p_mobilenumber", p_mobilenumber);
        query.registerStoredProcedureParameter("p_outval", Integer.class, ParameterMode.OUT);
        query.execute();
        Integer p_outval = (Integer) query.getOutputParameterValue("p_outval");
        return p_outval;
    }
	
}
