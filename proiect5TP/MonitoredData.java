package proiect5TP;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MonitoredData {

	private String activityLabel;
	private Calendar startTime;
	private Calendar endTime;
	
	public MonitoredData(String activityLabel, Calendar startTime, Calendar endTime) {
		this.activityLabel = activityLabel;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getActivityLabel() {
		return activityLabel;
	}

	public void setActivityLabel(String activityLabel) {
		this.activityLabel = activityLabel;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	
	public int getStartDay(){
		return startTime.get(Calendar.DATE);
	}
	
	public double getDuration(){
		long end=endTime.getTimeInMillis();
		long start=startTime.getTimeInMillis();
		double result=TimeUnit.MILLISECONDS.toMinutes(Math.abs(end-start));
		result/=60;
		//System.out.println(this.activityLabel +"  " +result);
		return result;
	}
	
	public double getDurationMinutes(){
		long end=endTime.getTimeInMillis();
		long start=startTime.getTimeInMillis();
		double result=TimeUnit.MILLISECONDS.toMinutes(Math.abs(end-start));
		return result;
	}
	
	public String toString(){
		return startTime.get(Calendar.YEAR)+"  "+Integer.toString(startTime.get(Calendar.MONTH)+1) +"    "+startTime.get(Calendar.DATE)+"   "
	+endTime.get(Calendar.YEAR)+"  "+Integer.toString(endTime.get(Calendar.MONTH)+1)+"    "+endTime.get(Calendar.DATE)+"    "+activityLabel;
	}
	
}
