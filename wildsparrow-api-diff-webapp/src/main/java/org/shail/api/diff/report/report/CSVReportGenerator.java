package org.shail.api.diff.report.report;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.shail.api.diff.report.report.dto.APIDifferenceReportDetailsDTO;

public class CSVReportGenerator {
	
	private class Sortbyroll implements Comparator<Method>
	{
	    // Used for sorting in ascending order of
	    // roll number
	    public int compare(Method a, Method b)
	    {
	        return a.getName().compareTo(b.getName());
	    }
	}

	public static void createReport(String reportPath, List<APIDifferenceReportDetailsDTO> reportDTOs) {
		try {
			FileWriter writer = new FileWriter(reportPath);

			// for header
			List<String> headers = new ArrayList<String>();
			headers.add("Sr. No.");
			List<Method> methods = new ArrayList<Method>();
			methods.addAll(Arrays.asList(APIDifferenceReportDetailsDTO.class.getDeclaredMethods()));
			Collections.sort(methods,new Comparator(){
			       public int compare(Object o1, Object o2) {
			    	  Method a=(Method)o1;
			    	  Method b=(Method)o2;
			    	   return a.getName().compareTo(b.getName());
			        }
			    });
			for (Method method : methods) {
				String name = method.getName();
				if (name.startsWith("get") && !name.equalsIgnoreCase("getClass")) {

					headers.add((name.replace("get", "").replace("_", " ")).toUpperCase());
				}
			}
			CSVReportUtils.writeLine(writer, headers);

			int srno = 1;
			for (APIDifferenceReportDetailsDTO report : reportDTOs) {

				// try custom separator and quote.
				// CSVUtils.writeLine(writer, list, '|', '\"');

				List<String> content = new ArrayList<String>();
				content.add(String.valueOf(srno++));
				for (Method method : methods) {
					String name = method.getName();
					if (name.startsWith("get") && !name.equalsIgnoreCase("getClass")) {

						content.add((String) method.invoke(report, new Object[0]));
					}
				}
				CSVReportUtils.writeLine(writer, content);
			}

			writer.flush();
			writer.close();

		} catch (IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
