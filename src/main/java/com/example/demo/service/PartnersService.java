package com.example.demo.service;

import com.example.demo.domain.Attendees;
import com.example.demo.domain.PartnersDetail;
import com.example.demo.domain.Response;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PartnersService {

    public static List<PartnersDetail> details = new ArrayList<>();
    public static Map<String,List<PartnersDetail>> partnersByCountry = new HashMap<>();

//    static {
//        PartnersDetail partner1 = new PartnersDetail("Darin",
//                "Daignault", "ddaignault@mycompany.com",
//                "United States", Arrays.asList("2017-05-06", "2017-05-05"));
//
//        PartnersDetail partner2 = new PartnersDetail("Crystal"
//                , "Brenna", "cbrenna@mycompany.com",
//                "Ireland", Arrays.asList("2017-04-27", "2017-04-29", "2017-04-30"));
//
//        PartnersDetail partner3 = new PartnersDetail("Janyce", "Gustison",
//                "jgustison@mycompany.com",
//                "Spain", Arrays.asList("2017-04-29",
//                "2017-04-30",
//                "2017-05-01"));
//
//        PartnersDetail partner4 = new PartnersDetail("Tifany",
//                "Mozie", "tmozie@mycompany.com",
//                "Spain", Arrays.asList("2017-04-28",
//                "2017-04-29",
//                "2017-05-01",
//                "2017-05-04"));
//
//        PartnersDetail partner5 = new PartnersDetail("Temple",
//                "Affelt", "taffelt@mycompany.com",
//                "Spain",
//                Arrays.asList("2017-04-28",
//                        "2017-04-29",
//                        "2017-05-02",
//                        "2017-05-04"));
//
//        PartnersDetail partner6 = new PartnersDetail("Robyn",
//                "Yarwood", "ryarwood@mycompany.com",
//                "Spain", Arrays.asList("2017-04-29",
//                "2017-04-30",
//                "2017-05-02",
//                "2017-05-03"));
//
//        PartnersDetail partner7 = new PartnersDetail("Shirlene",
//                "Filipponi", "sfilipponi@mycompany.com",
//                "Spain", Arrays.asList("2017-04-30",
//                "2017-05-01"));
//
//        PartnersDetail partner8 = new PartnersDetail("Oliver", "Majica",
//                "omajica@mycompany.com",
//                "Spain",
//                Arrays.asList("2017-04-28",
//                        "2017-04-29",
//                        "2017-05-01",
//                        "2017-05-03"));
//
//        PartnersDetail partner9 = new PartnersDetail("Wilber",
//                "Zartman", "wzartman@mycompany.com",
//                "Spain", Arrays.asList("2017-04-29",
//                "2017-04-30",
//                "2017-05-02",
//                "2017-05-03"));
//
//        PartnersDetail partner10 = new PartnersDetail("Eugena",
//                "Auther", "eauther@mycompany.com",
//                "United States", Arrays.asList("2017-05-04",
//                "2017-05-09"));
//
//        details.add(partner1);
//        details.add(partner2);
//        details.add(partner3);
//        details.add(partner4);
//        details.add(partner5);
//        details.add(partner6);
//        details.add(partner7);
//        details.add(partner8);
//        details.add(partner9);
//        details.add(partner10);

//        for (PartnersDetail p : details) {
//            if (!partnersByCountry.containsKey(p.getCountry())) {
//                partnersByCountry.put(p.getCountry(), new ArrayList<>());
//            }
//            partnersByCountry.get(p.getCountry()).add(p);
//        }
//        partnersByCountry =  details.stream()
//                .collect(Collectors.groupingBy(PartnersDetail::getCountry));
        //System.out.println(partnersByCountry);

//   }
    public List<PartnersDetail> retrieveAllDetails() {
        return details;
    }

    public Response getEventAttendees(){
        String startDate = null;
        List<Attendees> attendees = new ArrayList<>();
        List<String> consecutiveDateList = new ArrayList<>();
        List<String> emails = new ArrayList<>();

        //grouping by country
        partnersByCountry =  details.stream()
                .collect(Collectors.groupingBy(PartnersDetail::getCountry));

        for(Map.Entry<String, List<PartnersDetail>> entry : partnersByCountry.entrySet()){

            List<PartnersDetail> partnersDetailList = entry.getValue();
            for (PartnersDetail partnerDetail:partnersDetailList){
                LocalDate firstOfConsecutiveDate = checkConsecutiveDates(partnerDetail.getAvailableDates());
                String firstConsecutiveDate  = firstOfConsecutiveDate == null?null:firstOfConsecutiveDate.toString();
                if(firstOfConsecutiveDate!= null){
                    consecutiveDateList.add(firstConsecutiveDate);
                }
            }
            if(! consecutiveDateList.isEmpty()){
                Collections.sort( consecutiveDateList);
                startDate =  consecutiveDateList.get(0);
            }
            if(startDate != null) {
                emails = calculateAttendeeCount(partnersDetailList, startDate);
            }
                Attendees attendee = new Attendees(emails.size(), emails, entry.getKey(), startDate);
                attendees.add(attendee);
                consecutiveDateList.clear();
        }
        Response response = new Response(attendees);
        return response;
    }

    private List<String> calculateAttendeeCount(List<PartnersDetail> partnersDetails, String startDate) {
        int count = 0;
        List<String> email = new ArrayList<>();
        for(PartnersDetail partner:partnersDetails){
            if(partner.getAvailableDates().contains(startDate)){
                //count++;
                email.add(partner.getEmail());
            }
        }
        return email;
    }


    private LocalDate checkConsecutiveDates(List<String> dates) {
//        availabledates.stream().map(date->{
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            LocalDate ld1 = LocalDate.parse(date);
//        });
        List<LocalDate> availableDates = dates.stream().map(date-> LocalDate.parse(date)).collect(Collectors.toList());

        Collections.sort(availableDates, new Comparator<LocalDate>() {
            @Override
            public int compare(LocalDate firstDate, LocalDate secondDate) {
                if(firstDate.isAfter(secondDate)) {
                   return 1;
                }
                else {
                    return -1;
                }
            }
        });

        LocalDate availDate = null;
        if(availableDates.size() == 1){
            return null;
        }

        for(int i=0;i<availableDates.size();i++) {
            LocalDate firstDate = availableDates.get(i);
            if (i < availableDates.size() - 1) {
                LocalDate secondDate = availableDates.get(i + 1);
                if (firstDate.plusDays(1).toString().equals(secondDate.toString())) {
                    availDate = firstDate;
                    break;
                }
            }
        }
        return availDate;
    }

    public Response addPartner(PartnersDetail partnersDetail) {

        //check whether partner which user is adding already exist or not ?
        Boolean alreadyExist = details.stream().filter(d -> d.getEmail().equals(partnersDetail.getEmail())).findAny().isPresent();
        if (!alreadyExist) {
            details.add(partnersDetail);
            partnersByCountry =  details.stream()
                    .collect(Collectors.groupingBy(PartnersDetail::getCountry));
            //return "partner added succesfully";
            return getEventAttendees();
        }
        //return "already exist";
        return null;
    }

//    public static void main(String[] args) {
//        List<String> list = Arrays.asList("12-10-2021","06-09-2022","09-08-2022","05-08-2022");
//        Collections.sort(list);
//        System.out.println(list);
//    }

    public Response getAttendeeList(List<PartnersDetail> partnersList){
        partnersList.stream().forEach(a->{
            Boolean alreadyExist = details.stream().filter(d -> d.getEmail().equals(a.getEmail())).findAny().isPresent();
            if (!alreadyExist) {
                details.add(a);
                partnersByCountry =  details.stream()
                        .collect(Collectors.groupingBy(PartnersDetail::getCountry));
            }
        });
        return getEventAttendees();
    }
}