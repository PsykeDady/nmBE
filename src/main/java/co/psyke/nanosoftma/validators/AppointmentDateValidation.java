package co.psyke.nanosoftma.validators;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.entities.Appointment;

@Service
public class AppointmentDateValidation {
	
	public boolean validDate(LocalDateTime ldt,List<Appointment> doctorsAppointment){
		DayOfWeek dw= ldt.getDayOfWeek(); 
		int dd=ldt.getDayOfMonth();
		Month mo=ldt.getMonth();
		int hh=ldt.getHour();

		if(dw==DayOfWeek.SUNDAY){
			return false;
		}

		if(mo==Month.DECEMBER){
			if(dd==31 || dd==25){
				return false;
			}
		}

		// TODO check others holidays

		if(hh<9 || hh>18){
			return false; 
		}

		LocalDateTime ldt1= ldt.plusHours(1);

		for(Appointment v: doctorsAppointment){
			LocalDateTime vdt=v.getAppointmentDate();
			LocalDateTime vdt1=vdt.plusHours(1); 
			if(ldt.compareTo(vdt)<0){
				if(ldt1.compareTo(vdt)>0 && ldt1.compareTo(vdt1)<0){
					return false; 
				}
			} else {
				if(vdt1.compareTo(ldt)>0 && vdt1.compareTo(ldt1)<0){
					return false;
				}
			}
		}

		return true;
	}
}
