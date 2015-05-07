package dz.esi.tdm.mainmodule;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cherifi on 04/05/2015.
 */
public class Vol implements Parcelable {
    private String numVol;
    private String destination;

    public static final Parcelable.Creator<Vol> CREATOR = new Parcelable.Creator<Vol>() {
        @Override
        public Vol createFromParcel(Parcel source) {
            return new Vol(source);
        }

        @Override
        public Vol[] newArray(int size) {
            return new Vol[size];
        }
    };

    public Vol(String numVol, String destination) {
        this.numVol = numVol;
        this.destination = destination;
    }

    public Vol(Parcel in) {
        this.numVol = in.readString();
        this.destination = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numVol);
        dest.writeString(destination);
    }

    public String getNumVol() {
        return numVol;
    }

    public void setNumVol(String numVol) {
        this.numVol = numVol;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

}
