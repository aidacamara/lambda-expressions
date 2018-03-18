package proiect5TP;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Analizator {

	private List<MonitoredData> activitati;

	public Analizator(){
		activitati=new ArrayList<MonitoredData>();
	}
	public Analizator(List<MonitoredData> activitati) {
		this.activitati = activitati;
	}

	public List<MonitoredData> getActivitati() {
		return activitati;
	}

	public void setActivitati(List<MonitoredData> activitati) {
		this.activitati = activitati;
	}
	
	public int distinctDays(){
		int count=(int) activitati.stream().map(data->data.getStartTime().get(Calendar.DATE)).distinct().count();
		
		return count;
	}
	
	public Map<String,Long> countActivitati(){
		List<String> activ=activitati.stream().map(data-> data.getActivityLabel()).collect(Collectors.toList());
		
		Map<String,Long> result=activ.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return result;
	}
	
	public Map<Integer, Map<String,Long> > countActivitatiZile(){
	
		Map<Integer, Map<String,Long> > result=activitati.stream().collect(Collectors.groupingBy(MonitoredData::getStartDay,Collectors.groupingBy(p->p.getActivityLabel(),Collectors.counting())));
		
		return result;
	}
	
	public Map<String,Double> activitatiZeceOre(){
		
	//	Map<String,Integer> result=activitati.stream().collect(Collectors.toMap(p-> p.getActivityLabel(), p-> p.getDuration()));
		
		List<String> activ=activitati.stream().map(data-> data.getActivityLabel()).collect(Collectors.toList());
		

		Map<String,Double> result=new HashMap<String,Double>();
		
		activ.forEach((s)->result.put(s, activitati.stream().filter(p-> p.getActivityLabel().equals(s) 
																	&& activitati.stream().filter(o-> o.getActivityLabel().equals(s) )
																	.collect(Collectors.summingDouble(o->o.getDuration()))>=10 )
													.collect(Collectors.summingDouble(p->p.getDuration()))));
		
		result.entrySet().removeIf(entry -> entry.getValue()==0);
		
		
		return result;
	}
	
	public  List<String> getActivitatiCinciMinute(){
		
		
		Map<String, Long> activ = countActivitati();
				
		Map<String, Long> activ5Min = activitati.stream().filter(m->m.getDurationMinutes() <= 5).map(m->m.getActivityLabel())
														 .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		//activ5Min.entrySet().removeIf(entry->entry.getKey()==null);
		
		activ5Min.entrySet().removeIf(entry->entry.getValue()==0);
		
		//activ.entrySet().removeIf(entry->entry.getKey()==null);
		
		
		List<String> result= activitati.stream().filter(p->{
			if (activ5Min.get(p.getActivityLabel()) == null)
				return false;
			if(activ5Min.get(p.getActivityLabel()) >= 0.9 * activ.get(p.getActivityLabel()) )
				return true;
			
			return false;
		})
		
				.map(m->m.getActivityLabel()).distinct().collect(Collectors.toList());
		
		
		return result;
	}

	
}
