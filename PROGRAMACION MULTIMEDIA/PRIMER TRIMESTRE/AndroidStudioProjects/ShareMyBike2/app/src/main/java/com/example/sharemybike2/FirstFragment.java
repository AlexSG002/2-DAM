package com.example.sharemybike2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FirstFragment extends Fragment {

    private CalendarView calendarView;
    private TextView selectedDateTextView;
    Button bConfirmar;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        bConfirmar = view.findViewById(R.id.buttonConfirmar);
        calendarView = view.findViewById(R.id.calendarView);
        selectedDateTextView = view.findViewById(R.id.selectedDateTextView);

        setMinDateForCalendar();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                selectedDateTextView.setText("Fecha seleccionada: " + fechaSeleccionada);

                BikesContent.selectedDate = fechaSeleccionada;


            }
        });

        String fechaHoy = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        selectedDateTextView.setText("Fecha seleccionada: " + fechaHoy);
        BikesContent.selectedDate = fechaHoy;


        bConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_FirstFragment_to_ItemFragment);
            }
        });

        return view;
    }

    private void setMinDateForCalendar() {
        // Obtener la fecha de hoy en milisegundos desde la época (1 de enero de 1970)
        long today = System.currentTimeMillis();
        long maxDate = today + (365L * 24 * 60 * 60 * 1000);
        calendarView.setMinDate(today);
        calendarView.setMaxDate(maxDate);
    }
}


