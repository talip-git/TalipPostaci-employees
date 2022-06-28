package com.example.demo.Filehandler.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.demo.Filehandler.models.Calculated;
import com.example.demo.Filehandler.models.Row;

public class FileUtils {
    public static Row parseLine(String line){
        String [] parts = line.split(",");
        DateTimeFormatter datefrom = DateTimeFormatter.ofPattern("dd/MM/yyyy",Locale.ENGLISH);
        if(parts[3] == "NULL"){
          return new Row(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),
          LocalDate.parse(parts[2],datefrom),LocalDate.now());
        }
        return new Row(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),
                    LocalDate.parse(parts[2],datefrom),LocalDate.parse(parts[3],datefrom));
    }

    public static List<Calculated> calculate(List<Row> list){
        List<Calculated> calculated = new ArrayList<>();
        for(int i = 0;i<list.size()-1;i++){
            Row emp = list.get(i);
            for(int j = i+1;j<list.size();j++){
              
              Row other = list.get(j); 
              if(emp.getEmployeeId() == other.getEmployeeId() || emp.getProjectId()!= other.getProjectId()){
                continue;
              }
              LocalDate first_dateFrom = emp.getDateFrom();
              LocalDate first_dateTo = emp.getDateTo();
              LocalDate other_dateFrom = other.getDateFrom();
              LocalDate other_dateTo = other.getDateTo();
              if(first_dateFrom.getYear() > other_dateTo.getYear() || 
                  other_dateTo.getYear()> first_dateFrom.getYear()){
                 
                    calculated.add(new Calculated(emp.getEmployeeId(),other.getEmployeeId(), emp.getProjectId(), 0));
              }
              else{
                int no_of_days_first = Period.between(first_dateTo, first_dateFrom).getDays();
                int no_of_days_second = Period.between(other_dateTo, other_dateFrom).getDays();
                int daysworked = (int)Math.abs(no_of_days_first-no_of_days_second);
      
                calculated.add(new Calculated(emp.getEmployeeId(),other.getEmployeeId(), emp.getProjectId(), daysworked));
      
              }
            }
        }
        return calculated;
    }
}
