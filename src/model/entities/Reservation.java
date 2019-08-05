package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.excepetions.DomainException;

public class Reservation {
	private Integer roonNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
	
	public Reservation() {
		
	}

	public Reservation(Integer roonNumber, Date checkIn, Date checkOut) throws DomainException {
		if (!checkOut.after(checkIn)) {
			throw new DomainException( "Check-in date must be after Check-Out date");
		}
		this.roonNumber = roonNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoonNumber() {
		return roonNumber;
	}

	public void setRoonNumber(Integer roonNumber) {
		this.roonNumber = roonNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public void updateDates(Date checkIn, Date checkOut) throws DomainException {
		
		Date now = new Date();
		if(checkIn.before(now) || checkOut.before(now) ) {
			throw new DomainException("Check-in date must be future dates");
		}
		
		if (!checkOut.after(checkIn)) {
			throw new DomainException( "Check-in date must be after Check-Out date");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room "
				+ roonNumber 
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights.";
	}
	
}
