package com.govst.zacharyexp.caregiverbuddy;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private String theYear;
    private String theMonth;
    private String theDay;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Creating a default calendar with the current date
        final Calendar calendar = Calendar.getInstance();
        this.setStyle(R.style.myAppTheme, R.style.myAppTheme);

        //Initialize variables with current date
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //Create an instance of a DatePickerDialog with the current date (initialized in variables ;)
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    //Return the date set by the user
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //importing buttons
        Button startDate = getActivity().findViewById(R.id.button_start_date);
        Button endDate = getActivity().findViewById(R.id.button_end_date);


        //Setting the date's values with two numbers for each values at least
        if (dayOfMonth < 10){
            theDay = new StringBuilder("0").append(dayOfMonth).toString();
        }else{
            theDay = Integer.toString(dayOfMonth);
        }

        if (month+1 < 10){
            theMonth = new StringBuilder("0").append(month + 1).toString();
        }else{
            theMonth = Integer.toString(month + 1);
        }

        theYear = Integer.toString(year);


        //setting button text
        String date = dateGetter();

        //detecting which text button has to be changed
        if (startDate.getText().equals(""))
            startDate.setText(date);
        else
            endDate.setText(date);
    }



    //Date variables toString
    public String dateGetter(){
        //Putting all the integer variables into one string
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(theDay +"/" + theMonth + "/"+theYear);

        return stringBuilder.toString();
    }


}

