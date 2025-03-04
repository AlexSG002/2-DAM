package com.pmdm.parcelables;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Materia implements Parcelable {
    public String nombreMateria;
    public float notaMateria; // Cambiado a float para admitir decimales

    public Materia(String nombreMateria, float notaMateria){
        this.nombreMateria = nombreMateria;
        this.notaMateria = notaMateria;
    }

    protected Materia(Parcel in) {
        nombreMateria = in.readString();
        notaMateria = in.readFloat(); // Cambiado a readFloat
    }

    public static final Creator<Materia> CREATOR = new Creator<Materia>() {
        @Override
        public Materia createFromParcel(Parcel in) {
            return new Materia(in);
        }

        @Override
        public Materia[] newArray(int size) {
            return new Materia[size];
        }
    };

    @Override
    public String toString() {
        return "Materia{" +
                "nombreMateria='" + nombreMateria + '\'' +
                ", notaMateria=" + notaMateria +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nombreMateria);
        parcel.writeFloat(notaMateria); // Cambiado a writeFloat
    }
}
