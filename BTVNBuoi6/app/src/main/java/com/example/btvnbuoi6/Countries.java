package com.example.btvnbuoi6;

public class Countries {

    String Country_Region;
    long Confirmed,Deaths,Recovered;


    public Countries() {
    }

    public Countries(String country_Region, long Confirmed, long deaths, long recovered) {
        Country_Region = country_Region;
        Confirmed = Confirmed;
        Deaths = deaths;
        Recovered = recovered;
    }

    public String getCountry_Region() {
        return Country_Region;
    }

    public void setCountry_Region(String country_Region) {
        Country_Region = country_Region;
    }

    public long getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(long Confirmed) {
        Confirmed = Confirmed;
    }

    public long getDeaths() {
        return Deaths;
    }

    public void setDeaths(long deaths) {
        Deaths = deaths;
    }

    public long getRecovered() {
        return Recovered;
    }

    public void setRecovered(long recovered) {
        Recovered = recovered;
    }

    @Override


    public String toString() {
        return "Countries{" +
                "Country_Region='" + Country_Region + '\'' +
                ", Confirmed=" + Confirmed +
                ", Deaths=" + Deaths +
                ", Recovered=" + Recovered +
                '}';
    }
}
