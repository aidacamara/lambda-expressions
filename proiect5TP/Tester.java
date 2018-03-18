package proiect5TP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
	
		
		Analizator test=new Analizator();
		Scanner input;
		try {
			input = new Scanner(new File("Activities.txt"));
			while(input.hasNext()){
				String line=input.nextLine();
				
				String startTimeString=line.substring(0, 19);
				String endTimeString=line.substring(21,40);
				String activity=line.substring(42);
				
				
				DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				Date date=df.parse(startTimeString);
				Calendar startTime=new GregorianCalendar();
				startTime.setTime(date);
				
				date=df.parse(endTimeString);
				Calendar endTime=new GregorianCalendar();
				endTime.setTime(date);
				MonitoredData data=new MonitoredData(activity,startTime,endTime);
				
				test.getActivitati().add(data);
				//System.out.println(data);
				
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Zile distincte "+test.distinctDays());
		
		 Map<String,Long> exDoi=test.countActivitati();
		 try {
			PrintWriter writer = new PrintWriter("Exercitiu2.txt");
			 exDoi.forEach((k,v)->writer.println("Ziua: "+k+"   "+"nr: "+v));
			 writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 Map<Integer, Map<String,Long> > exTrei=test.countActivitatiZile();
		 try {
				PrintWriter writer = new PrintWriter("Exercitiu3.txt");
				 exTrei.forEach((k,v)->{
					 writer.println("start time: "+k);
					 v.forEach((key,value)-> writer.println("Activitate: "+key+"  nr aparitii "+value));
					 writer.println("\n");
				 });
				 writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 Map<String,Double> exPatru=test.activitatiZeceOre();
		 
		 try {
				PrintWriter writer = new PrintWriter("Exercitiu4.txt");
				 exPatru.forEach((k,v)->writer.println("Activitate: "+k+"   "+"durata: "+v));
				 writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		// System.out.println(test.getActivitati().get(0).getDuration()+"  "+test.getActivitati().get(0).getActivityLabel());
		 
		 List<String> exCinci=test.getActivitatiCinciMinute();
		 try {
				PrintWriter writer = new PrintWriter("Exercitiu5.txt");
				 exCinci.forEach(k->writer.println("Activitate: "+k));
				 writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	}

}
