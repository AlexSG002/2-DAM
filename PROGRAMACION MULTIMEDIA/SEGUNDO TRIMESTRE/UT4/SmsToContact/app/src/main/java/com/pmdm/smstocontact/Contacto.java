package com.pmdm.smstocontact;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacto implements Parcelable {
    private String id;
    private String nombre;
    private String apellido;
    private String numero;
    private String UriFoto;

    public Contacto(String id, String nombre, String apellido, String numero, String UriFoto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero = numero;
        this.UriFoto = UriFoto;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumero() {
        return numero;
    }

    public String getUriFoto() {
        return UriFoto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setUriFoto(String uriFoto) {
        this.UriFoto = uriFoto;
    }

    protected Contacto(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        apellido = in.readString();
        numero = in.readString();
        UriFoto = in.readString();
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(id);
        parcel.writeString(nombre);
        parcel.writeString(apellido);
        parcel.writeString(numero);
        parcel.writeString(UriFoto);
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numero='" + numero + '\'' +
                ", photoUri='" + UriFoto + '\'' +
                '}';
    }
}
