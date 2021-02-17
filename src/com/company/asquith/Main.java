package com.company.asquith;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> cityList = collectCities(); //Collects city names
        List<Double[]> tempList = collectTemps(cityList); //Collects five temperatures for each city

        Map<List<String>, List<Double[]>> cityTemps = new HashMap<>(); //HashMap to store cityList and tempList
        cityTemps.put(cityList, tempList); //Adds cityList and tempList to one data structure(HashMap)

        displayResults(cityTemps, cityList); //Displays each city with the five-day average temperature(uses the getAvgTemp method)
    }

    static List<String> collectCities() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a list of cities, type \"END\" when you're finished.");
        List<String> cityList = new ArrayList<>();
        String cityInput = "";
        while (!cityInput.equals("END")) { //Stops looping once "END" has been entered
            cityInput = input.nextLine();
            cityList.add(cityInput);
        }
        cityList.remove("END"); //Removes "END" from end of arraylist
        return cityList;
    }

    static List<Double[]> collectTemps(List<String> cityList) {
        Scanner input = new Scanner(System.in);
        List<Double[]> tempList = new ArrayList<>();
        for (String city : cityList) { //Goes through each string in cityList
            System.out.println("Please enter the average temperature in fahrenheit for the next five days for " + city);
            Double[] tempArray = new Double[5]; //Array that stores 5 doubles
            for (int i = 0; i < 5; i++) { //User inputs 5 doubles
                tempArray[i] = input.nextDouble();
            }
            tempList.add(tempArray); //Adds double array to tempList for each city
        }
        return tempList;
    }

    static String getCity(Map<List<String>, List<Double[]>> cityTemps, int i) {
        String city = "";
        for (List<String> cities : cityTemps.keySet()) {
            city = cities.get(i);
        }
        return city;
    }

    static List<Double> getAvgTemp(Map<List<String>, List<Double[]>> cityTemps, int i) {
        Double[] temp = {};
        List<Double> tempList = new ArrayList<>(Arrays.asList(temp)); //Converts temp(array) to arrayList (changes made to temp happen to tempList??)
        for (List<Double[]> temps : cityTemps.values()) {
            temp = temps.get(i);
            Double sum = 0.0;
            for (Double tem : temp) {
                sum += tem;
            }
            tempList.add(sum / 5);
        }
        return tempList;
    }

    static void displayResults(Map<List<String>, List<Double[]>> cityTemps, List<String> cityList) {
        for (int i = 0; i < cityList.size(); i++) {
            System.out.print("The five-day average temperature of " + getCity(cityTemps, i) + " is ");
            System.out.println((getAvgTemp(cityTemps, i)).toString().replace("[", "").replace("]", "") + "° Fahrenheit");
        }
    }
}