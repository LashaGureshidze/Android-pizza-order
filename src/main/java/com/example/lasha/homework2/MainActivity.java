package com.example.lasha.homework2;

import android.content.res.Resources;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private double price;
    private EditText nameField, phoneField;
    private TextView priceField;
    private RadioButton cheese, cheese2x, none, square, round;
    private CheckBox pepperoni, mushrooms, veggies, anchovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews(savedInstanceState);

        on_change_ingredient(none);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("cheese", cheese.isChecked());
        outState.putBoolean("cheese2x", cheese2x.isChecked());
        outState.putBoolean("none", none.isChecked());
        outState.putBoolean("square", square.isChecked());
        outState.putBoolean("round", round.isChecked());
        outState.putBoolean("pepperoni", pepperoni.isChecked());
        outState.putBoolean("mushrooms", mushrooms.isChecked());
        outState.putBoolean("veggies", veggies.isChecked());
        outState.putBoolean("anchovies", anchovies.isChecked());
    }

    /**
     * on order button clicked
     */
    public void on_order_click(View view) {
        boolean valid = validateFields();

        if (valid) {
            createOrder();
        }
    }

    private void createOrder() {
        Toast.makeText(this,
                getResources().getText(R.string.order_success) + "\n" + getResources().getText(R.string.price) + price,
                Toast.LENGTH_LONG)
                .show();
    }

    /**
     * validates personal information. also shows error message
     */
    private boolean validateFields() {
        boolean valid = true;
        if (nameField.getText().toString().isEmpty()) {
            nameField.setError(getResources().getString(R.string.name_error_msg));
            valid = false;
        }
        if (phoneField.getText().toString().isEmpty()) {
            phoneField.setError(getResources().getString(R.string.phone_error_msg));
            valid = false;
        }

        return valid;
    }

    /*
     * on change ingredient.
     * i.e. checked pepperoni, unchecked cheese and so on..
     */
    public void on_change_ingredient(View view) {
        price = determinePrice();
        priceField.setText(getResources().getString(R.string.price) + " " + price);
    }

    /*
     * find all wanted vies, and save them in instance variables
     * it also restores activity state.
     */
    private void findViews(Bundle savedInstanceState) {
        nameField = (EditText) findViewById(R.id.name_field);
        phoneField = (EditText) findViewById(R.id.phone_field);
        priceField = (TextView) findViewById(R.id.price_field);

        cheese = (RadioButton) findViewById(R.id.cheese);
        cheese2x = (RadioButton) findViewById(R.id.cheese_2x);
        none = (RadioButton) findViewById(R.id.none);
        square = (RadioButton) findViewById(R.id.square);
        round = (RadioButton) findViewById(R.id.round_pizza);

        pepperoni = (CheckBox) findViewById(R.id.pepperoni);
        mushrooms = (CheckBox) findViewById(R.id.mushrooms);
        veggies = (CheckBox) findViewById(R.id.veggies);
        anchovies = (CheckBox) findViewById(R.id.anchovies);

        //restore state
        if (savedInstanceState == null) return;
        cheese.setChecked(savedInstanceState.getBoolean("cheese"));
        cheese2x.setChecked(savedInstanceState.getBoolean("cheese2x"));
        none.setChecked(savedInstanceState.getBoolean("none"));
        square.setChecked(savedInstanceState.getBoolean("square"));
        round.setChecked(savedInstanceState.getBoolean("round"));
        pepperoni.setChecked(savedInstanceState.getBoolean("pepperoni"));
        mushrooms.setChecked(savedInstanceState.getBoolean("mushrooms"));
        veggies.setChecked(savedInstanceState.getBoolean("veggies"));
        anchovies.setChecked(savedInstanceState.getBoolean("anchovies"));
    }

    private double determinePrice() {
        Resources res = getResources();
        price = 0.0;
        if (cheese.isChecked()) {
            price += Double.parseDouble(res.getString(R.string.cheese_p));
            Log.d("debug", "cheese");
        }
        if (cheese2x.isChecked()) {
            price += Double.parseDouble(res.getString(R.string.cheese_2x_p));
            Log.d("debug", "cheese2x");
        }
        if (none.isChecked()) {
            price += Double.parseDouble(res.getString(R.string.none_p));
            Log.d("debug", "none");
        }
        if (square.isChecked()) {
            price += Double.parseDouble(res.getString(R.string.square_p));
            Log.d("debug", "square");
        }
        if (round.isChecked()) {
            price += Double.parseDouble(res.getString(R.string.round_pizza_p));
            Log.d("debug", "round");
        }
        if (pepperoni.isChecked()) {
            price += Double.parseDouble(res.getString(R.string.pepperoni_p));
            Log.d("debug", "pepperoni");
        }
        if (mushrooms.isChecked()) {
            price += Double.parseDouble(res.getString(R.string.mushrooms_p));
            Log.d("debug", "mushrooms");
        }
        if (veggies.isChecked()) {
            price += Double.parseDouble(res.getString(R.string.veggies_p));
            Log.d("debug", "veggies");
        }
        if (anchovies.isChecked()) {
            price += Double.parseDouble(res.getString(R.string.anchovies_p));
            Log.d("debug", "anchovies");
        }

        return price;
    }

}
