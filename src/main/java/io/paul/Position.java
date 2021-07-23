package io.paul;

public class Position {
    private double altitude;
    private double latitude;
    private double longitude;

    public Position(double altitude, double latitude, double longitude) {
        setAltitude(altitude);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public double distance(Position position){
        double raduis = 6371;
        double radLat1 = Math.toRadians(this.latitude) ;
        double radLat2 = Math.toRadians(position.latitude) ;
        double dlong = Math.toRadians(this.longitude - position.longitude);
        double dlat = Math.toRadians(this.latitude - position.latitude);

        double a =  Math.sin(dlat/2) * Math.sin(dlat/2) +
                Math.cos(radLat1) * Math.cos(radLat2) *
                        Math.sin(dlong/2) * Math.sin(dlong/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return raduis * c;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        if(altitude < 0){
            this.altitude = 0;
        }else if(altitude > 40000){
            this.altitude = 40000;
        }else {
            this.altitude = altitude;
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        if(latitude < -90){
            this.latitude = -90;
        }else if(latitude > 90){
            this.latitude = 90;
        }else {
            this.latitude = latitude;
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        if(longitude < -180){
            this.longitude = -180;
        }else if(longitude > 180){
            this.longitude = 180;
        }else {
            this.longitude = longitude;
        }
    }
}
