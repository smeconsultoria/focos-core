package br.com.sme.teste.mock;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Teste {

	static HashMap<Integer, List<Date>> getMapDateWeeks() {
		List<Date> dates = new ArrayList<Date>();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -2);

		long interval = 24 * 1000 * 60 * 60;
		long endTime = Calendar.getInstance().getTime().getTime();
		long curTime = calendar.getTime().getTime();

		while (curTime <= endTime) {
			dates.add(new Date(curTime));
			curTime += interval;
		}
		
		
		HashMap<Integer, List<Date>> hashDates = new HashMap<Integer, List<Date>>();
		
		
		Calendar now = Calendar.getInstance();
		boolean first = true;
		int currentWeek = 0;

		List<Date> listDates = new ArrayList<Date>();
		for (Date date : dates) {
			now.setTime(date);
			int weekNumber = now.get(Calendar.WEEK_OF_YEAR);

			if (first) {
				currentWeek = weekNumber;
				listDates.add(date);

				first = false;
			} else if (weekNumber != currentWeek) {
				hashDates.put(currentWeek, listDates);

				currentWeek = weekNumber;

				listDates = new ArrayList<Date>();
				listDates.add(date);
			} else {
				listDates.add(date);
			}

		}

		hashDates.put(currentWeek, listDates);
		
		return hashDates;
	}

	public static void main(String[] args) throws ParseException {
		
		HashMap<Integer, List<Date>> hashDates = getMapDateWeeks();
		
		for (Entry<Integer, List<Date>> pair : hashDates.entrySet()) {
		    System.out.println(pair.getKey());
		    System.out.println(pair.getValue());
		}
		

	}

}
