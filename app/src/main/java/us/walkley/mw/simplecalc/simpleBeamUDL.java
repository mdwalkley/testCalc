package us.walkley.mw.simplecalc;

import android.app.Activity;
import android.content.Intent;
import android.nfc.FormatException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.util.ArrayList;

public class simpleBeamUDL extends AppCompatActivity {

    ArrayList<AnswerItem> answerItemArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_beam_udl); //getActionBar().setDisplayHomeAsUpEnabled(true);

        final Button calculate = (Button) findViewById(R.id.calculate_button);

        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                calculateAll(view);
            }
        });

        /*
        Spinner spinner = (Spinner) findViewById(R.id.spinner_e);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.e_names, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        */
    }

    private void calculateAll(View view){
        double w=0, l=0, x=0, e=0, i=0;

        try{
            w = Double.valueOf(((EditText)findViewById(R.id.input_w)).getText().toString());
            l = Double.valueOf(((EditText)findViewById(R.id.input_l)).getText().toString());
            x = Double.valueOf(((EditText)findViewById(R.id.input_x)).getText().toString());

            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation01, equation1(w,l)));
            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation02, equation2(w,l,x)));
            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation03, equation3(w,l,x)));
            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation04, equation4(w,l,x)));

        }catch (IllegalStateException | NumberFormatException exc) {
            Toast.makeText(this, "Invalid input.", Toast.LENGTH_LONG).show();
        }

        try{
            i = Double.valueOf(((EditText)findViewById(R.id.input_i)).getText().toString());
            e = Double.valueOf(((EditText)findViewById(R.id.input_e)).getText().toString());

            double ei= e*i;

            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation05, equation5(w,l,x,ei)));
            answerItemArrayList.add(new AnswerItem(R.drawable.simplebeam_udl_equation06, equation6(w,l,x,ei)));

        }catch (IllegalStateException | NumberFormatException exc) {
            Toast.makeText(this, "Invalid inputs for E and I.", Toast.LENGTH_LONG).show();
        }

        //TODO: Add Fragment launch here

        hideKeyboard(this);

    }

    /*
    private void calculateAll(View view){
        double w=0, l=0, x=0, e=0, i=0;

        try{
            w = Double.valueOf(((EditText)findViewById(R.id.input_w)).getText().toString());
            l = Double.valueOf(((EditText)findViewById(R.id.input_l)).getText().toString());
            x = Double.valueOf(((EditText)findViewById(R.id.input_x)).getText().toString());

            ((TextView)findViewById(R.id.answer1)).setText(Double.toString(equation1(w,l)));
            ((TextView)findViewById(R.id.answer2)).setText(Double.toString(equation2(w,l,x)));
            ((TextView)findViewById(R.id.answer3)).setText(Double.toString(equation3(w,l,x)));
            ((TextView)findViewById(R.id.answer4)).setText(Double.toString(equation4(w,l,x)));
        }catch (IllegalStateException | NumberFormatException exc) {
            Toast.makeText(this, "Invalid input.", Toast.LENGTH_LONG).show();
        }

        try{
            i = Double.valueOf(((EditText)findViewById(R.id.input_i)).getText().toString());
            e = Double.valueOf(((EditText)findViewById(R.id.input_e)).getText().toString());
            double ei= e*i;

            ((TextView)findViewById(R.id.answer5)).setText(Double.toString(equation5(w,l,x,ei)));
            ((TextView)findViewById(R.id.answer6)).setText(Double.toString(equation6(w,l,x,ei)));
        }catch (IllegalStateException | NumberFormatException exc) {
            Toast.makeText(this, "Invalid inputs for E and I.", Toast.LENGTH_LONG).show();
        }

        hideKeyboard(this);

    }
*/
    private double equation1(double w, double l){
        return (w*l)/2;
    }

    private double equation2(double w, double l, double x){
        return w*(l/2-x);
    }

    private double equation3(double w, double l, double x){
        return (w*l*l)/8; //(w*l^2)/8
    }

    private double equation4(double w, double l, double x){
        return (w*x/2)*(l-x);
    }

    private double equation5(double w, double l, double x, double EI){
        return (5*w*(Math.pow(l,4)))/(384*EI);  //(5*w*l^4)/(384*ei)
    }

    private double equation6(double w, double l, double x, double EI){
        return (w*x)/(24*EI)*(Math.pow(l,3)-2*l*x*x+Math.pow(x,3));  //((w*x)/(24*ei))*(l^3 - 2*l*x^2 + x^3);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
    }

    public static void hideKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null){
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }


}
