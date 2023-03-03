package com.example.robinjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView OutputTxt;
    EditText InputTxt;
    Button BtnToCreate;
    Button BtnCreate;
    Button BtnGetAll;
    Button BtnGetById;
    Button BtnToMain;
    RecyclerView GetAllRecyclerView;
    EditText nameInput;
    EditText addressInput;
    Spinner NationalitySpinner;
    CheckBox favCheck;
    EditText tlfInput;
    EditText interestInput1;
    EditText interestInput2;
    EditText interestInput3;



    private static final String TAG = "MainActivity";
    private Connection connection = DatabaseConnection.getConnection();
    // Connection



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String[] NationalityArray = {"Andalusien", "Belgien", "Congo", "Djibouti", "Equador", "Guyana"};  //Til test

        InputTxt = (EditText) findViewById(R.id.TxtInput);

        OutputTxt = (TextView) findViewById(R.id.TxtOutput);
        BtnToCreate = (Button) findViewById(R.id.BtnToCreate);
        BtnCreate = (Button) findViewById(R.id.BtnCreate);
        BtnGetAll = (Button) findViewById(R.id.BtnGetAll);
        BtnGetById = (Button) findViewById(R.id.BtnGetById);
        BtnToMain = (Button)findViewById(R.id.BtnToMain);
        NationalitySpinner = (Spinner) findViewById(R.id.nationalitySpinner);
        GetAllRecyclerView = (RecyclerView) findViewById(R.id.getAllRecyclerView);
        GetAllRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        nameInput = (EditText)findViewById(R.id.nameInput);
        addressInput = (EditText)findViewById(R.id.addressInput);
        //favCheck(CheckBox)findViewById(R.id.favCheck); //Skal lige fikses
        tlfInput = (EditText)findViewById(R.id.tlfInput);
        interestInput1 = (EditText)findViewById(R.id.interestInput1);
        interestInput2 = (EditText)findViewById(R.id.interestInput2);
        interestInput3 = (EditText)findViewById(R.id.interestInput3);


        BtnToCreate.setOnClickListener(new View.OnClickListener() { //Bruges til at navigere fra main til create-vinduet
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_create);
            }
        });

        BtnToMain.setOnClickListener(new View.OnClickListener() { //Bruges til at navigere fra create-vinduet til main
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
            }
        });
        BtnCreate.setOnClickListener(new View.OnClickListener() { //Skal bruges til at oprette, og derefter sende brugeren tilbage til main
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_create);
            }
        });

        // Create an instance of MyDatabaseHelper and get the spinner options from the database
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        ArrayList<String> options = dbHelper.getSpinnerOptions();

        // Create an ArrayAdapter using the options from the database and set it on the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NationalitySpinner.setAdapter(adapter);


        new ConnectToDatabaseTask().execute();
    }

    private class ConnectToDatabaseTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            // Attempt to establish the database connection
            connection = DatabaseConnection.getConnection();
            return connection != null;
        }


        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                // Connection successful, perform database operations here
                Log.d(TAG, "Connection successful");

                // CREATE
                try {
                    String sql = "INSERT INTO Personer (Navn, Adresse, Nationalitet, Favourite, Tlf, Interesse1, Interesse2, Interesse3) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, nameInput.getText().toString());
                    statement.setString(2, addressInput.getText().toString());
                    String nationalitet = NationalitySpinner.getSelectedItem().toString();
                    statement.setString(3, nationalitet);
                    statement.setBoolean(4, favCheck.isChecked());
                    statement.setString(5, tlfInput.getText().toString());
                    statement.setString(6, interestInput1.getText().toString());
                    statement.setString(7, interestInput2.getText().toString());
                    statement.setString(8, interestInput3.getText().toString());
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        Log.d(TAG, "En ny person er oprettet!");
                    } else {
                        Log.d(TAG, "Ingen person oprettet!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // READ - GET ALL
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM Personer");
                    while (resultSet.next()) {
                        // Do something with each row
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // READ - GET BY ID


                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM Personer where PersonID = 1");

                    if (resultSet.next()) {
                        String value = resultSet.getString("Navn");
                        Log.d(TAG, "Navn: " + value);
                        OutputTxt.setText(value);
                    }
                    //while (resultSet.next())
                    //{
                      //  OutputTxt.setText(resultSet.getString(columnIndex ));
                    //}
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // UPDATE
                try {
                    String sql = "UPDATE Personer SET column1 = ? WHERE column2 = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, "newvalue1");
                    statement.setString(2, "value2");
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        Log.d(TAG, "Personen er opdateret.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // DELETE
                try {
                    String sql = "DELETE FROM Personer WHERE PersonID = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, "value1");
                    int rowsDeleted = statement.executeUpdate();
                    if (rowsDeleted > 0) {
                        Log.d(TAG, "Personen er slettet.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Til at lukke, når vi er færdige
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                Log.d(TAG, "Connection failed. Fy for en skefuld!");
            }
        }
    }
}