package com.pmdm.parcelables;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Estudiante implements Parcelable {
    public String nomEstudiante;
    public int edad;
    public float notaMedia; // Cambiado a float para admitir decimales
    public ArrayList<Materia> listaMaterias;

    // Constructor de 3 parámetros
    public Estudiante(String nomEstudiante, int edad, float notaMedia){
        this.nomEstudiante = nomEstudiante;
        this.edad = edad;
        this.notaMedia = notaMedia;
        this.listaMaterias = new ArrayList<>(); // Inicializa la lista
    }

    // Constructor de 4 parámetros (opcional, si lo necesitas)
    public Estudiante(String nomEstudiante, int edad, float notaMedia, ArrayList<Materia> listaMaterias){
        this.nomEstudiante = nomEstudiante;
        this.edad = edad;
        this.notaMedia = notaMedia;
        this.listaMaterias = listaMaterias != null ? listaMaterias : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nomEstudiante='" + nomEstudiante + '\'' +
                ", edad=" + edad +
                ", notaMedia=" + notaMedia +
                ", listaMaterias=" + listaMaterias +
                '}';
    }

    protected Estudiante(Parcel in) {
        nomEstudiante = in.readString();
        edad = in.readInt();
        notaMedia = in.readFloat(); // Cambiado a readFloat
        listaMaterias = in.createTypedArrayList(Materia.CREATOR); // Usar createTypedArrayList
    }

    public static final Creator<Estudiante> CREATOR = new Creator<Estudiante>() {
        @Override
        public Estudiante createFromParcel(Parcel in) {
            return new Estudiante(in);
        }

        @Override
        public Estudiante[] newArray(int size) {
            return new Estudiante[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nomEstudiante);
        parcel.writeInt(edad);
        parcel.writeFloat(notaMedia); // Cambiado a writeFloat
        parcel.writeTypedList(listaMaterias);
    }
}
